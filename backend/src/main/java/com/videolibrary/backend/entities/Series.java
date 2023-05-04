package com.videolibrary.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "series")
    private List<Episode> episodes;

    @ManyToMany
    private Set<Genre> genres;

    private String title;

    private String description;

}