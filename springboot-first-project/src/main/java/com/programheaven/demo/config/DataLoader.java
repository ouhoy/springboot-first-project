package com.programheaven.demo.config;

import com.programheaven.demo.model.Content;
import com.programheaven.demo.model.Status;
import com.programheaven.demo.model.Type;
import com.programheaven.demo.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

    @Bean
    CommandLineRunner commandLineRunner(ContentRepository repository){
        return args-> {
                Content content = new Content(
                        null,
                        "Hello world",
                        "Hello world is a common thing said when creating a first software.",
                        Status.IDEA,
                        Type.ARTICLE,
                        LocalDateTime.now(),
                        null,
                        ""
                );
                repository.save(content);
        };
    }
}
