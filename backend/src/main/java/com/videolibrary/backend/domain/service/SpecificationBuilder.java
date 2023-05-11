package com.videolibrary.backend.domain.service;

import com.videolibrary.backend.domain.entity.Genre_;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SpecificationBuilder {
    private SpecificationBuilder() {
    }

    public static <T> Specification<T> searchSpecification(String title, Set<Integer> genreIds) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isBlank(title)) {
                predicates.add(builder.like(root.get("title"), "%" + title + "%"));
            }
            if (!CollectionUtils.isEmpty(genreIds)) {
                predicates.add(root.join("genres").get(Genre_.ID).in(genreIds));
            }
            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
