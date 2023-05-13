package com.videolibrary.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String username;

    @OneToMany(mappedBy = "user")
    private List<History> history;

}
