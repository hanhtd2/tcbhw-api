package com.hanhtd26.tcbhwapi.restservice.entity;

import java.io.Serializable;

/**
 * This entity extends from PushEntity and it holds info of Pushed entity and pre-calculates totals
 * by pre-calculating totals (sum) of all array elements, we can reduce compute cost when the array becomes bigger.
 */
public class SavingPushEntity extends PushEntity implements Serializable {

    private Double totals;
    public Double getTotals() {
        return totals;
    }

    public void setTotals(Double totals) {
        this.totals = totals;
    }
}
