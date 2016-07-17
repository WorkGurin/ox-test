package com.ox.service;

import com.ox.domain.Topic;
import com.ox.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Iterable<Topic> list() {
        return topicRepository.findAll();
    }

}
