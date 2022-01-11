package com.example.socialdanceserver.model;

import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.model.enums.TypeEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dancers")
public class Dancer extends AbstractBaseEntity{

    private String surname;
    private String sex;
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_password_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LoginPassword loginPassword;


    public Dancer() {
        this.setTypeEntity(TypeEntity.DANCER);
    }

    public Dancer(String image, String name, String description, EntityInfo entityInfo,
                  String surname, String sex, LocalDate birthday, Role role) {
        super(image, name, description, entityInfo);
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.role = role;
        this.setTypeEntity(TypeEntity.DANCER);
    }

    public Dancer(Integer id, String image, String name, String description, String surname,
                  String sex, LocalDate birthday, Role role) {
        super(id, image, name, description);
        this.surname = surname;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LoginPassword getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(LoginPassword loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "Dancer{" +
                "surname='" + surname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                "} " + super.toString();
    }
}
