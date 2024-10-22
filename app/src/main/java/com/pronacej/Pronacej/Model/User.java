package com.pronacej.Pronacej.Model;

public class User {
    private int id;
    private int typeUserId;
    private String name;
    private String lastName;
    private String entity;
    private String dni;
    private String expirationDate;
    private String email;
    private String password;
    private int state;
    private String token;

    // Constructor
    public User(int id, int typeUserId, String name, String lastName, String entity, String dni,
                String expirationDate, String email, String password, int state, String token) {
        this.id = id;
        this.typeUserId = typeUserId;
        this.name = name;
        this.lastName = lastName;
        this.entity = entity;
        this.dni = dni;
        this.expirationDate = expirationDate;
        this.email = email;
        this.password = password;
        this.state = state;
        this.token = token;
    }

    // Getters
    public int getId() { return id; }
    public int getTypeUserId() { return typeUserId; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getEntity() { return entity; }
    public String getDni() { return dni; }
    public String getExpirationDate() { return expirationDate; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getState() { return state; }
    public String getToken() { return token; }

    // Setters (opcional, dependiendo de si los necesitas)
    public void setId(int id) { this.id = id; }
    public void setTypeUserId(int typeUserId) { this.typeUserId = typeUserId; }
    public void setName(String name) { this.name = name; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEntity(String entity) { this.entity = entity; }
    public void setDni(String dni) { this.dni = dni; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setState(int state) { this.state = state; }
    public void setToken(String token) { this.token = token; }
}