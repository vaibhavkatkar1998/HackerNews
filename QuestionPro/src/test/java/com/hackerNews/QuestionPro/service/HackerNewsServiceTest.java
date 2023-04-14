package com.hackerNews.QuestionPro.service;

import com.hackerNews.QuestionPro.models.Items;
import com.hackerNews.QuestionPro.repository.HackerNewsRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HackerNewsServiceTest {

    @Autowired
    HackerNewsService hackerNewsService;

    @Mock
    private RestTemplate restTemplate;

    @MockBean
    HackerNewsRepo mockHackerNewsRepo;

    @Test
    public void retrieveTopStoriesTest() {
        Items items = new Items(334,"Story1",null,8,0,"user1","comment1");
        when(mockHackerNewsRepo.save(items)).thenReturn(items);
        List<Items> responseList = hackerNewsService.retrieveTopStories();
        Assert.assertEquals(10,responseList.size());
    }

    @Test
    public void retrievePastStoriesTest() {
        Items items = new Items(334,"Story1",null,8,0,"user1","comment1");
        List<Items> mockItemList = new ArrayList<>();
        mockItemList.add(items);
        when(mockHackerNewsRepo.findAll()).thenReturn(mockItemList);
        List<Items> responseList = hackerNewsService.retrievePastStories();
        Assert.assertEquals(true,responseList.size() > 0);
    }

}
