package com.rodrigoamora.testgreendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class Person {

    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @Generated(hash = 1543952807)
    public Person(Long id, @NotNull String name, @NotNull String phone,
            @NotNull String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Generated(hash = 1024547259)
    public Person() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
