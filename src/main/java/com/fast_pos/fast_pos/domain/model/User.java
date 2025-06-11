package com.fast_pos.fast_pos.domain.model;

import com.fast_pos.fast_pos.domain.enums.UserRole;

import java.util.UUID;

public class User {
    private UUID  id;
    private String storeName;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public User(UUID id,String storeName,String name, String email, String password,UserRole role){
        this.id=id;
        this.storeName=storeName;
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
    }



    public void changePassword(String encryptedPassword){
        this.password = encryptedPassword;
    }
    public UUID  getId(){return id;}
    public String getTenantStoreName(){return storeName;}
    public String getName(){return name;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public UserRole getRole(){return role;}
}
