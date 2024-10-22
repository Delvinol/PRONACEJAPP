package com.pronacej.Pronacej.Model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class RegisterRequest {

    @SerializedName("typeUserId")
    Integer typeUserId;
    @SerializedName("name")
    String name;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("email")
    String email;

    @SerializedName("entity")
    String entity;

    @SerializedName("expirationdate")
    LocalDate expirationDate;

    @SerializedName("state")
    Short state;

    @SerializedName("dni")
    String dni;

    public RegisterRequest() {
    }

    public RegisterRequest(Integer typeUserId, String name, String lastName, String email, String entity, LocalDate expirationDate, short state, String dni) {
        this.typeUserId = typeUserId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.entity = entity;
        this.expirationDate = expirationDate;
        this.state = state;
        this.dni = dni;
    }



    public Integer getTypeUserId() {
        return typeUserId;
    }

    public void setTypeUserId(Integer typeUserId) {
        this.typeUserId = typeUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEntity(){
        return entity;
    }

    public void setEntity(String entity){
        this.entity = entity;
    }

    public LocalDate getExpirationDate(){
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate){
        this.expirationDate= expirationDate;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getDni(){
        return dni;
    }

    public void setDni(String dni){
        this.dni= dni;
    }
}
