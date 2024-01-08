package com.programheaven.demo.repository;

import com.programheaven.demo.model.Content;
import org.springframework.data.repository.ListCrudRepository;


public interface ContentRepository extends ListCrudRepository<Content,Integer> {

}