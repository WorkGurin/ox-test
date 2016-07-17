package com.ox.controller;

import com.ox.domain.Topic;
import com.ox.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    TopicRepository topicRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @RequestMapping(value = "/topics", method = RequestMethod.GET, produces = "application/json")
    public List<Topic> listAllTopics() {
        return (List) topicRepository.findAll();
    }

}
