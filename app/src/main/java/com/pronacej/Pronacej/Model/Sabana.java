package com.pronacej.Pronacej.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sabana {

    //@SerializedName("id")
    //@Expose
    //private Integer id;

    @SerializedName("adminId")
    @Expose
    private Integer adminId;

    @SerializedName("tableTablesId")
    @Expose
    private Integer tableTablesId;

    @SerializedName("processHeaderId")
    @Expose
    private Integer processHeaderId;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("state")
    @Expose
    private Integer state;

    public Sabana() {
    }

    public Sabana(Integer adminId, Integer tableTablesId, Integer processHeaderId, String value, Integer state) {
        //this.id = id;
        this.adminId = adminId;
        this.tableTablesId = tableTablesId;
        this.processHeaderId = processHeaderId;
        this.value = value;
        this.state = state;
    }

   /* public Integer getId() {
        return id;
    }*/

    /*public void setId(Integer id) {
        this.id = id;
    }*/

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getTableTablesId() {
        return tableTablesId;
    }

    public void setTableTablesId(Integer tableTablesId) {
        this.tableTablesId = tableTablesId;
    }

    public Integer getProcessHeaderId() {
        return processHeaderId;
    }

    public void setProcessHeaderId(Integer processHeaderId) {
        this.processHeaderId = processHeaderId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
