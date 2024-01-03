package com.programheaven.demo.repository;

import com.programheaven.demo.model.Content;
import com.programheaven.demo.model.Status;
import com.programheaven.demo.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c-> c.id().equals(id)).findFirst();
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c-> c.id().equals(id)).count() == 1;
    }

    public void save(Content content) {
        contentList.removeIf(c-> c.id().equals(content.id()));
        contentList.add(content);
    }

    public void delete(Integer id) {
        contentList.removeIf(c-> c.id().equals(id));

    }

    @PostConstruct
    private void init() {
        Content content = new Content(
                1,
                "My First Blog Post",
                "My first blog post content",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );

        contentList.add(content);

    }

}
