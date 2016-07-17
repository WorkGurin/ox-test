package com.ox.controller;

import com.ox.RunSupportTests;
import com.ox.json.JsonObjectSerializer;
import com.ox.domain.Inquiry;
import com.ox.repository.TopicRepository;
import com.ox.service.InquiryService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InquiryControllerTest extends RunSupportTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    InquiryService inquiryService;

    @Autowired
    TopicRepository topicRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void listCustomerName() throws Exception {

        mockMvc.perform(get("/customers/{customerName}/inquiries", "Ivanov Ivan"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].customerName", is("Ivanov Ivan")));
    }

    @Test
    public void listCustomerNameAndId() throws Exception {

        mockMvc.perform(get("/customers/{customerName}/inquiries/{id}", "Ivanov Ivan", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.customerName", is("Ivanov Ivan")));
    }

    @Test
    public void create() throws Exception {
        List<String> attributes = new ArrayList<String>();
        attributes.add("Create address");
        attributes.add("Create model of phone");

        Inquiry createInquiry = new Inquiry();
        createInquiry.setDescription("Create description");
        createInquiry.setCreateDate(new Date());
        createInquiry.setCustomerName("Create customer name");
        createInquiry.setAttributes(attributes);
        createInquiry.setTopic(topicRepository.findOne(2L));


        mockMvc.perform(post("/customers/{customerName}/inquiries", "Create customer name")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonObjectSerializer.convertObjectToJsonBytes(createInquiry))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    public void update() throws Exception {

        List<String> attributes = new ArrayList<String>();
        attributes.add("Update address");
        attributes.add("Update model of phone");

        Inquiry updateInquiry = new Inquiry();
        updateInquiry.setDescription("Update description");
        updateInquiry.setCreateDate(new Date());
        updateInquiry.setCustomerName("Update customer name");
        updateInquiry.setAttributes(attributes);
        updateInquiry.setTopic(topicRepository.findOne(2L));

        List<Inquiry> searchInquiry = inquiryService.listCustomerName("Create customer name");

        mockMvc.perform(put("/customers/{customerName}/inquiries/{id}", searchInquiry.get(0).getCustomerName(), searchInquiry.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonObjectSerializer.convertObjectToJsonBytes(updateInquiry))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void delete() throws Exception {

        List<Inquiry> searchInquiry = inquiryService.listCustomerName("Update customer name");
        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/{customerName}/inquiries/{id}",
                searchInquiry.get(0).getCustomerName(), searchInquiry.get(0).getId()))
                .andExpect(status().isOk());
    }
}