package com.pronacej.Pronacej.Model;

import java.time.LocalDate;

public class Person {

    private Integer id;

    private Integer typeUserId;

    private String name;

    private String lastName;
    private String entity;
    private LocalDate expirationDate;

    private String email;
    private String dni;

    private String password;

    private Short state;

    public String getDni(){
        return dni;
    }

    public void setDni(String dni){
        this.dni= dni;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }
}
