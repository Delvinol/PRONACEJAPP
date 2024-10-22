package com.pronacej.Pronacej.Model;

public class SectionRecord {
    private Integer id;

    private Integer typeSectionRecordId;

    private String description;

    private Short state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeSectionRecordId() {
        return typeSectionRecordId;
    }

    public void setTypeSectionRecordId(Integer typeSectionRecordId) {
        this.typeSectionRecordId = typeSectionRecordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}
