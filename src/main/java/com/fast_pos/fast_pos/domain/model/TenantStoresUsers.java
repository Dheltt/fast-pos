package com.fast_pos.fast_pos.domain.model;

import com.fast_pos.fast_pos.domain.enums.UserRole;

import java.util.UUID;

public class TenantStoresUsers {
    private UUID id;
    private String schemaName;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;

    public TenantStoresUsers(UUID id,String schemaName, String name,String email,String password,UserRole userRole){
        this.id = id;
        this.schemaName=schemaName;
        this.name = name;
        this.email=email;
        this.password=password;
        this.userRole=userRole;
    }
    public UUID getId(){return id;}
    public String getSchemaName(){return schemaName;}
    public String getEmail(){return  email;}
    public String getName(){return name;}
    public UserRole getUserRole(){return  userRole;}
    public String getPassword(){return password;}

}
