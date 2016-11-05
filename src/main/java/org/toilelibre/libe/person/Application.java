package org.toilelibre.libe.person;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

@SpringBootApplication
public class Application {

    @Bean MongoClient mongo () {
        return new Fongo ("testServer").getMongo ();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}