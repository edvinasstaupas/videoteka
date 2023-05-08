package com.videolibrary.backend.infrastructure.sql.initializer;

import com.videolibrary.backend.domain.entity.*;
import com.videolibrary.backend.infrastructure.sql.repository.MovieRepository;
import com.videolibrary.backend.infrastructure.sql.repository.SeriesRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ConditionalOnProperty(value = "videolibrary.database.init", havingValue = "true")
@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;

    private static Genre getMovieGenre() {
        Genre movieGenre = new Genre();
        movieGenre.setName("movieGenre");
        return movieGenre;
    }

    @PostConstruct
    public void initDatabase() {
        movieRepository.save(getMovie());
        seriesRepository.save(getSeries());
    }

    private Series getSeries() {
        Series series = new Series();
        series.setDescription("seriesDescription");
        series.setTitle("seriesTitle");
        series.setSeasons(List.of(getSeason()));
        series.setGenres(Set.of(getSeriesGenre()));
        series.setLastEpisodeReleaseDate(LocalDate.parse("2023-05-07"));
        return series;
    }

    private Movie getMovie() {
        Movie movie = new Movie();
        movie.setTitle("movieTitle");
        movie.setReleaseDate(LocalDate.parse("2023-05-06"));
        movie.setDescription("movieDescription");
        movie.setVideo(getMovieVideo());
        movie.setGenres(Set.of(getMovieGenre()));
        return movie;
    }

    private Season getSeason() {
        Season season = new Season();
        season.setDescription("seasonDescription");
        season.setTitle("seasonTitle");
        season.setEpisodes(List.of(getEpisode()));
        return season;
    }

    private Episode getEpisode() {
        Episode episode = new Episode();
        episode.setDescription("episodeDescription");
        episode.setName("episodeName");
        episode.setReleaseDate(LocalDate.parse("2023-05-07"));
        episode.setNumberInSeason(1);
        episode.setVideo(getEpisodeVideo());
        return episode;
    }

    private Genre getSeriesGenre() {
        Genre seriesGenre = new Genre();
        seriesGenre.setName("seriesGenre");
        return seriesGenre;
    }

    private Video getEpisodeVideo() {
        Video episodeVideo = new Video();
        episodeVideo.setThumbnailUrl("episodeVideoThumbnailUrl");
        episodeVideo.setUrl("episodeVideoUrl");
        return episodeVideo;
    }

    private Video getMovieVideo() {
        Video movieVideo = new Video();
        movieVideo.setUrl("movieVideourl");
        movieVideo.setThumbnailUrl("movieVideoThumbnailUrl");
        return movieVideo;
    }
}
