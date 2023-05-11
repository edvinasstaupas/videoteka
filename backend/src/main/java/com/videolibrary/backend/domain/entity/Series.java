package com.videolibrary.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Season> seasons;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "series_genre",
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    private String title;

    private String description;

    private String thumbnailPathId;

    private LocalDate lastEpisodeReleaseDate;

    public void setSeasons(List<Season> seasons) {
        seasons.forEach(season -> season.setSeries(this));
        this.seasons = seasons;
    }
}