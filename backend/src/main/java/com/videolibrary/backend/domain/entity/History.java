package com.videolibrary.backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Episode episode;

    private ZonedDateTime timestamp;

}
