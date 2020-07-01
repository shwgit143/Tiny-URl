package com.example25.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example25.demo.document.*;

public interface convertRepository extends MongoRepository<Urls,Integer>{

}
