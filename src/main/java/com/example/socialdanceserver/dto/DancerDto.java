package com.example.socialdanceserver.dto;

import com.example.socialdanceserver.model.EntityInfo;
import com.example.socialdanceserver.model.LoginPasswordEntity;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.Role;

import java.util.Date;
import java.util.List;

public class DancerDto {

    private Integer id;
    private String image;
    private String name;
    private String surname;
    private String sex;

    private Date birthday;

    private EntityInfo entityInfo;
    private Role role;
    private List<Dance> dances;
    private LoginPasswordEntity loginPasswordEntity;
    private String description;

    public DancerDto() {
    }

    public DancerDto(Integer id, String image, String name, String surname, String sex,
                     Date birthday, EntityInfo entityInfo, Role role,
                     List<Dance> dances, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.entityInfo = entityInfo;
        this.role = role;
        this.dances = dances;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public  EntityInfo getEntityInfo() {
        return entityInfo;
    }

    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }

    public List<Dance> getDances() {
        return dances;
    }

    public void setDances(List<Dance> dances) {
        this.dances = dances;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LoginPasswordEntity getLoginPassword() {
        return loginPasswordEntity;
    }

    public void setLoginPassword(LoginPasswordEntity loginPasswordEntity) {
        this.loginPasswordEntity = loginPasswordEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DancerTo{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", entityInfo=" + entityInfo +
                ", role=" + role +
                ", dances=" + dances +
                ", loginPassword=" + loginPasswordEntity +
                ", description='" + description + '\'' +
                '}';
    }
}
