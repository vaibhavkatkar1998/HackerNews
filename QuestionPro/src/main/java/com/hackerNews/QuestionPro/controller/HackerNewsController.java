package com.hackerNews.QuestionPro.controller;

import com.hackerNews.QuestionPro.models.Items;
import com.hackerNews.QuestionPro.service.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * A HackerNewsController class to expose endpoints dealing with HackerNews data.
 */
@RestController
public class HackerNewsController {

    @Autowired
    public HackerNewsService hackerNewsService;

    /**
     * An endpoint to get top 10 stories from hackerNews API
     *
     * @return
     */
    @GetMapping("/top-stories")
    public ResponseEntity<List<Items>> getTopStories() {
        List<Items> response = hackerNewsService.retrieveTopStories();
        // Handling bad request if response list goes beyond 10
        if(response.size() != 10) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * An endpoint to get all the stories served by /top-stories endpoint
     *
     * @return
     */
    @GetMapping("/past-stories")
    public ResponseEntity<List<Items>> getPastStories() {
        List<Items> response = hackerNewsService.retrievePastStories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * An endpoint to get the comments from particular stories
     *
     * @param id
     * @return
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<String> getComments(@PathVariable long id) {
        return new ResponseEntity<>(hackerNewsService.retrieveComments(id), HttpStatus.OK);
    }
}
