package com.ox.controller;

import com.ox.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopicController {

    private TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        super();
        this.topicService = topicService;
    }

    @RequestMapping("/topics")
    public String list(Model model) {
        model.addAttribute("topics", topicService.list());
        return "topic/list";
    }
}
