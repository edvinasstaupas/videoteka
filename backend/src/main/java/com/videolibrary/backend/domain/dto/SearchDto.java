package com.videolibrary.backend.domain.dto;

import lombok.Getter;

import java.util.Set;

@Getter
public class SearchDto {

    private String title;
    private Set<Integer> genreIds;

    public SearchDto(String title, Set<Integer> genreIds) {
        if (title == null)
            title = "";
        this.title = title;
        this.genreIds = genreIds;
    }
}
