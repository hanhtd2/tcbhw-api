package com.hanhtd26.tcbhwapi.restservice.entity;

/**
 * This entity holds info which response by application when user call method: .../query
 * Furthermore, it includes some pre-check condition of each property
 */
public class QueryReturnEntity {
    private long calculatedQuantile;
    private long elementCount;

    public QueryReturnEntity(long calculatedQuantileTotal, long elementCount) {
        this.calculatedQuantile = calculatedQuantileTotal;
        this.elementCount = elementCount;
    }

    public long getCalculatedQuantile() {
        return calculatedQuantile;
    }

    public void setCalculatedQuantile(long calculatedQuantile) {
        this.calculatedQuantile = calculatedQuantile;
    }

    public long getElementCount() {
        return elementCount;
    }

    public void setElementCount(long elementCount) {
        this.elementCount = elementCount;
    }
}
