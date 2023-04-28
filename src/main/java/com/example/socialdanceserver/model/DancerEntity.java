package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.model.enums.TypeEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dancers")
public class DancerEntity extends AbstractBaseEntity{

    private String surname;
    private String gender;
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_password_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LoginPasswordEntity loginPasswordEntity;


    public DancerEntity() {
        this.setTypeEntity(TypeEntity.DANCER);
    }

    public DancerEntity(String image, String name, String description, EntityInfo entityInfo,
                        String surname, String gender, LocalDate birthday, Role role) {
        super(image, name, description, entityInfo);
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
        this.setTypeEntity(TypeEntity.DANCER);
    }

    public DancerEntity(Integer id, String image, String name, String description, String surname,
                        String gender, LocalDate birthday, Role role) {
        super(id, image, name, description);
        this.surname = surname;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
        this.setTypeEntity(TypeEntity.DANCER);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthDay) {
        this.birthday = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
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

    @Override
    public String toString() {
        return "Dancer{" +
                "surname='" + surname + '\'' +
                ", sex='" + gender + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                "} " + super.toString();
    }
}
