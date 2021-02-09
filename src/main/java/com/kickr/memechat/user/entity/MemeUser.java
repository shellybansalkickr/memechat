package com.kickr.memechat.user.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "meme_user")
public class MemeUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserCategory userType;
    public enum UserCategory{
        MANAGER,
        CLIENT
    }

    @NotNull
    @Column(unique=true)
    private String emailId;


    @NotNull
    private String password;

    public MemeUser() {
    }

    public MemeUser(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserCategory getUserType() {
        return userType;
    }

    public void setUserType(UserCategory userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
