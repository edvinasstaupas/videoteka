package com.videolibrary.backend.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

import static com.videolibrary.backend.domain.entity.HistoryAware.Type.MOVIE;

@Entity
@Getter
@Setter
public class Movie extends HistoryAware {

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "movie_genre",
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    private LocalDate releaseDate;

    @Override
    public HistoryAware.Type getType() {
        return MOVIE;
    }
}