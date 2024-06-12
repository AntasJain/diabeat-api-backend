package com.antasjain.diabeat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "user"
)
public class User {
    @Id
    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") // Update mappedBy to "user"
    private List<Role> roles;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @JsonIgnoreProperties("user")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void add(Role temp){
        if(roles == null){
            roles=new ArrayList<>();
        }
        roles.add(temp);
        temp.setUser(this);
    }
}
