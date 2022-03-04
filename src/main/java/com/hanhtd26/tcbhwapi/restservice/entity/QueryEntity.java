package com.hanhtd26.tcbhwapi.restservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * This entity holds info which are provided by user by using POST method: .../query
 * Furthermore, it includes some pre-check condition of each property
 */
public class QueryEntity {
    @NotNull(message = "poolId is mandatory")
    @JsonProperty("poolId")
    private Long poolId;

    @NotNull(message = "percentile is mandatory")
    @Range(min = 1, max = 99, message = "percentile must be in range 1-99")
    @JsonProperty("percentile")
    private Double percentile;

    public Long getPoolId() {
        return poolId;
    }

    public void setPoolId(Long poolId) {
        this.poolId = poolId;
    }

    public Double getPercentile() {
        return percentile;
    }

    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }
}
