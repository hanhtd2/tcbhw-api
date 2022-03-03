package com.hanhtd26.tcbhwapi.restservice.utils;

import com.hanhtd26.tcbhwapi.restservice.entity.SavingPushEntity;
import com.hanhtd26.tcbhwapi.restservice.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapIO {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private static final String PERSISTENCE_FILENAME = "persistence.bit";

    /**
     * This function tries to write data (ConcurrentHashMap) object to specific file
     * @param map
     * @throws IOException
     */
    public void writeMapToFile(ConcurrentHashMap<Long, SavingPushEntity> map) throws IOException {
        //write to file : "persistence.bit"
        try {
            File fileOne = new File(PERSISTENCE_FILENAME);
            if (fileOne.exists()) {
                fileOne.delete();
            }
            FileOutputStream fos = new FileOutputStream(fileOne);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }

    }

    /**
     * This function reads data from persistence file, which named $PERSISTENCE_FILENAME
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ConcurrentHashMap<Long, SavingPushEntity> readFromStorageFile() throws IOException, ClassNotFoundException {
        //read from file
        try {
            File toRead = new File(PERSISTENCE_FILENAME);
            if (toRead.exists()) {
                FileInputStream fis = new FileInputStream(toRead);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ConcurrentHashMap<Long, SavingPushEntity> mapInFile = (ConcurrentHashMap<Long, SavingPushEntity>) ois.readObject();
                ois.close();
                fis.close();
                return mapInFile;
            } else {
                return null;
            }
        } catch(Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }
}
