package com.qs.mvc.entity.user;

import com.qs.mvc.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "t_mm_user")
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
