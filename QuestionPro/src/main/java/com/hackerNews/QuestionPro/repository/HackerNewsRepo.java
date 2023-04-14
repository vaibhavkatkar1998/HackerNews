package com.hackerNews.QuestionPro.repository;

import com.hackerNews.QuestionPro.models.Items;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HackerNewsRepo extends MongoRepository<Items, Integer> {

}
