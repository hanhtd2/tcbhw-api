package com.hanhtd26.tcbhwapi.restservice.entity;

/**
 * This entity holds info which response by application when user call method: .../query
 * Furthermore, it includes some pre-check condition of each property
 */
public class QueryReturnEntity {
    private double calculatedQuantileTotal;
    private long elementCount;

    public QueryReturnEntity(double calculatedQuantileTotal, long elementCount) {
        this.calculatedQuantileTotal  = calculatedQuantileTotal;
        this.elementCount = elementCount;
    }

    public double getCalculatedQuantileTotal() {
        return calculatedQuantileTotal;
    }

    public void setCalculatedQuantileTotal(double calculatedQuantileTotal) {
        this.calculatedQuantileTotal = calculatedQuantileTotal;
    }

    public long getElementCount() {
        return elementCount;
    }

    public void setElementCount(long elementCount) {
        this.elementCount = elementCount;
    }
}
