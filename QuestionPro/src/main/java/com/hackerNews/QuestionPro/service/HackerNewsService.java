package com.hackerNews.QuestionPro.service;

import com.hackerNews.QuestionPro.models.Items;
import com.hackerNews.QuestionPro.repository.HackerNewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 * A service class for all the business logic of HackerNews data.
 */
@Service
public class HackerNewsService {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public CacheManager cacheManager;

    @Autowired
    public HackerNewsRepo hackerNewsRepo;

    /**
     * This service will fetch data from hackerNews API and at the same time
     * saving it to DB.
     *
     * @return
     */
    @Cacheable(cacheNames = {"hackerNewsCache"})
    public List<Items> retrieveTopStories() {
      List<Items> topStoriesList = new ArrayList<>();
      int storyId  =  restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/maxitem.json", Integer.class);
      for (int i = storyId; i>storyId-10;i--) {
          topStoriesList.add(restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+i+".json", Items.class));
          // Saving the serve data to DB
          hackerNewsRepo.save(restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+i+".json", Items.class));
      }
      return topStoriesList;
    }

    /**
     * This service retrieve the serve stories which is stored in DB
     * @return
     */
    public List<Items> retrievePastStories() {
      return hackerNewsRepo.findAll();
    }

    /**
     * This service will retrieve the comments from hackerNews API
     * @param id
     * @return
     */
    public String retrieveComments(long id) {
        Items item =restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+id+".json", Items.class);
        return ("Comments: "+item.getText());
    }

    /**
     * This service will run in every 15 min to clear the hackerNewsCache
     * so that new data will be able to serve from API.
     *
     */
    @Scheduled(fixedDelay = 900000) // run every 15 minutes
    public void evictCache() {
        cacheManager.getCache("hackerNewsCache").clear();
    }
}
