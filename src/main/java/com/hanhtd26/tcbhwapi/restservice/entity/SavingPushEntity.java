package com.hanhtd26.tcbhwapi.restservice.entity;

import java.io.Serializable;

/**
 * This entity extends from PushEntity and it holds info of Pushed entity but it separate to easy understand
 */
public class SavingPushEntity extends PushEntity implements Serializable {

    public SavingPushEntity() {
        super();
    }
    public SavingPushEntity(Long poolId, Long[] poolValues) {
        super(poolId, poolValues);
    }
}
