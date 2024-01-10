package com.programheaven.demo.controller;

import java.util.List;

import com.programheaven.demo.model.Content;
import com.programheaven.demo.repository.ContentCollectionRepository;
import com.programheaven.demo.repository.ContentJdbcTemplateRepository;
import com.programheaven.demo.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ContentController {

    //    private final ContentRepository repository;
    private final ContentJdbcTemplateRepository repository;

    public ContentController(ContentJdbcTemplateRepository contentCollectionRepository) {
        this.repository = contentCollectionRepository;

    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.getAllContent();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        Content content = repository.getContent(id);
        if (content == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return content;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        repository.createContent(content.title(), content.desc(), content.status(), content.contentType(), content.url());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        repository.updateContent(id, content.title(), content.desc(), content.status(), content.contentType(), content.url());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteContent(id);
    }
}
