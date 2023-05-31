package com.videolibrary.backend.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Series series;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes;

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(episode -> episode.setSeason(this));
        this.episodes = episodes;
    }
}
