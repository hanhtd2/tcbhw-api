package com.hanhtd26.tcbhwapi.restservice.service;

import com.hanhtd26.tcbhwapi.constant.CommonConstants;
import com.hanhtd26.tcbhwapi.restservice.entity.PushEntity;
import com.hanhtd26.tcbhwapi.restservice.entity.QueryEntity;
import com.hanhtd26.tcbhwapi.restservice.entity.QueryReturnEntity;
import com.hanhtd26.tcbhwapi.restservice.entity.SavingPushEntity;
import com.hanhtd26.tcbhwapi.restservice.utils.MapIO;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ApiService {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    public ApiService() {}
    public static final ConcurrentHashMap<Long, SavingPushEntity> map = new ConcurrentHashMap<>();

    /**
     * This static block run on top of application
     * which can read the previous data which user has input
     * Then this block provides for application initial values
     */
    static {
        MapIO mapIO = new MapIO();
        try {
            ConcurrentHashMap<Long, SavingPushEntity> storedMap = mapIO.readFromStorageFile();
            if (null != storedMap) {
                map.putAll(storedMap);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * This method return status of the request, if poolId is in application then return appended
     * else it will create new element and return inserted status
     * @param pushEntity
     * @return
     */
    public String push(PushEntity pushEntity) {
        SavingPushEntity existedEntity = map.get(pushEntity.getPoolId());
        if (null == existedEntity) {
            SavingPushEntity savingPushEntity = new SavingPushEntity();
            savingPushEntity.setPoolId(pushEntity.getPoolId());
            savingPushEntity.setPoolValues(pushEntity.getPoolValues());
            savingPushEntity.setTotals(sumAllElement(pushEntity.getPoolValues()));
            map.put(savingPushEntity.getPoolId(), savingPushEntity);
            return CommonConstants.INSERTED;
        } else {
            Double[] newPools = ArrayUtils.addAll(existedEntity.getPoolValues(), pushEntity.getPoolValues());
            Double newTotals = existedEntity.getTotals() + sumAllElement(pushEntity.getPoolValues());
            SavingPushEntity savingPushEntity = new SavingPushEntity();
            savingPushEntity.setPoolId(existedEntity.getPoolId());
            savingPushEntity.setPoolValues(newPools);
            savingPushEntity.setTotals(newTotals);
            map.put(savingPushEntity.getPoolId(), savingPushEntity);
            return CommonConstants.APPENDED;
        }
    }

    /**
     * Get input from Controller and return percentage of sum of all elements in pool
     * And number of elements in pool
     * @param queryEntity
     * @return
     */
    public QueryReturnEntity query(QueryEntity queryEntity) {
        //Check if id pool is existed
        SavingPushEntity  savingPushEntity  = map.get(queryEntity.getPoolId());
        if (null != savingPushEntity) {
            //Calculate with provided quantile by api
            double calculatedQuantile = (queryEntity.getPercentile() * savingPushEntity.getTotals()) / 100;
            long elementCount = savingPushEntity.getPoolValues().length;
            return new QueryReturnEntity(calculatedQuantile, elementCount);
        }
        return null;
    }

    /**
     * This method calculates sum of all elements in one array
     * @param elements
     * @return
     */
    private Double sumAllElement(Double[] elements) {
        double retVal = 0;
        for (double element : elements) {
            retVal = retVal + element;
        }
        return retVal;
    }
}
