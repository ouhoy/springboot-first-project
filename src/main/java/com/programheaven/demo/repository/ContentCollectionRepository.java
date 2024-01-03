package com.programheaven.demo.repository;

import com.programheaven.demo.model.Content;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> content = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public Optional<Content> findById(Integer id) {
        return content.stream().filter(c-> c.id().equals(id)).findFirst();
    }
}
