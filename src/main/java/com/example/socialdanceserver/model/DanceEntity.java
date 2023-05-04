package com.example.socialdanceserver.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dance")
@Getter
@Setter
@NoArgsConstructor
public class DanceEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;

    String name;

}
