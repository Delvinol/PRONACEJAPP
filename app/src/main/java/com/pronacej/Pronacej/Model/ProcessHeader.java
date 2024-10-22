package com.pronacej.Pronacej.Model;

import java.sql.Timestamp;

public class ProcessHeader {

    private Integer id;

    private Integer typeProcessHeaderId;

    private Timestamp startTime;

    private Timestamp endTime;

    private Short state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeProcessHeaderId() {
        return typeProcessHeaderId;
    }

    public void setTypeProcessHeaderId(Integer typeProcessHeaderId) {
        this.typeProcessHeaderId = typeProcessHeaderId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}
