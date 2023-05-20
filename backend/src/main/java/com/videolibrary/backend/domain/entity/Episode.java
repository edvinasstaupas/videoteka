package com.videolibrary.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Video video;

    @ManyToOne
    private Season season;

    private String name;

    private String description;

    private LocalDate releaseDate;

}
