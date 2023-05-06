package com.videolibrary.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Video video;

    @ManyToOne
    private Season season;

    private String name;

    private String description;

    private Integer numberInSeason;

    private Date releaseDate;

}
