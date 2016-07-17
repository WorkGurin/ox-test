package com.ox.repository;

import com.ox.RunSupportTests;
import com.ox.domain.Topic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TopicRepositoryTest extends RunSupportTests {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void findAll() {
        Iterable<Topic> findAllTopics = topicRepository.findAll();
        assertNotNull(findAllTopics);
    }
}
