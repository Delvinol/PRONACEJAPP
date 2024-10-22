package com.pronacej.Pronacej.Model;

public class Indicators {

    private Integer id;
    private Integer sectionRecordId;
    private String name;

    private Short state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionRecordId() {
        return sectionRecordId;
    }

    public void setSectionRecordId(Integer sectionRecordId) {
        this.sectionRecordId = sectionRecordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}
