package com.videolibrary.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_genre",
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    @OneToOne(cascade = CascadeType.ALL)
    private Video video;

    private String title;

    private String description;

    private LocalDate releaseDate;

}