package com.hanhtd26.tcbhwapi.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * This entity holds info which are provided by user by using POST method: .../push
 * Furthermore, it includes some pre-check condition of each property
 */
public class PushEntity implements Serializable {

    @NotNull(message = "poolId is mandatory")
    @JsonProperty("poolId")
    private Long poolId;

    @NotNull(message = "poolValues is mandatory")
    @JsonProperty("poolValues")
    private Long[] poolValues;

    public PushEntity() {
    }

    public PushEntity(Long poolId, Long[] poolValues) {
        this.poolId = poolId;
        this.poolValues = poolValues;
    }

    public long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public Long[] getPoolValues() {
        return poolValues;
    }

    public void setPoolValues(Long[] poolValues) {
        this.poolValues = poolValues;
    }
}
