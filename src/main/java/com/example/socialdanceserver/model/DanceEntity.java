package com.example.socialdanceserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanceEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;

    String name;

}
