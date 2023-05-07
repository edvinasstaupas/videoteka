package com.videolibrary.backend;

import com.videolibrary.backend.entities.Episode;
import com.videolibrary.backend.entities.Genre;
import com.videolibrary.backend.entities.Movie;
import com.videolibrary.backend.entities.Season;
import com.videolibrary.backend.entities.Series;
import com.videolibrary.backend.entities.Video;
import com.videolibrary.backend.repository.MovieRepository;
import com.videolibrary.backend.repository.SeriesRepository;
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
public class DatabaseInitializer {
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;

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

    private static Genre getMovieGenre() {
        Genre movieGenre = new Genre();
        movieGenre.setName("movieGenre");
        return movieGenre;
    }

    private Video getEpisodeVideo() {
        Video episodeVideo = new Video();
        episodeVideo.setThumbnailPathId("episodeVideoThumbnailPathId");
        episodeVideo.setPathId("episodeVideoPathId");
        return episodeVideo;
    }

    private Video getMovieVideo() {
        Video movieVideo = new Video();
        movieVideo.setPathId("2760d44c-da56-4c3b-84f5-3844049aa062");
        movieVideo.setThumbnailPathId("e1e5f5ec-c168-4f26-ab43-95d087704d22");
        return movieVideo;
    }
}
