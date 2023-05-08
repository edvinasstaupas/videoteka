package com.videolibrary.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class SearchDto {

    private String title;
    private List<Integer> genreIds;

    public SearchDto(String title, List<Integer> genreIds) {
        if (title == null)
            title = "";
        this.title = "%" + title.toLowerCase() + "%";
        this.genreIds = genreIds;
    }
}
