package com.ox.repository;

import com.ox.RunSupportTests;
import com.ox.domain.Inquiry;
import com.ox.service.InquiryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class InquiryServiceTest extends RunSupportTests {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private TopicRepository topicRepository;

    public Inquiry createInquiry() {

        List<String> attributes = new ArrayList<String>();
        attributes.add("Create address");

        Inquiry inquiry = new Inquiry();
        inquiry.setId(3L);
        inquiry.setDescription("Create description");
        inquiry.setCreateDate(new Date());
        inquiry.setCustomerName("Create customer name");
        inquiry.setAttributes(attributes);
        inquiry.setTopic(topicRepository.findOne(2L));

        return inquiry;
    }

    public void equalsInquiryAndInquiryTest(Inquiry inquiryTest) {
        assertNotNull(inquiryTest);
        assertEquals(createInquiry().getDescription(), inquiryTest.getDescription());
        assertEquals(createInquiry().getCustomerName(), inquiryTest.getCustomerName());
        assertEquals(createInquiry().getAttributes(), inquiryTest.getAttributes());
        assertEquals(createInquiry().getTopic().getId(), inquiryTest.getTopic().getId());
    }

    @Test
    public void listCustomerName() {
        List<Inquiry> listCustomerName = inquiryService.listCustomerName(createInquiry().getCustomerName());
        assertNotNull(listCustomerName);
        assertTrue(listCustomerName.size() > 0);
    }

    @Test
    public void listCustomerNameAndId() {
        Inquiry listCustomerNameAndId = inquiryService.listCustomerNameAndId(createInquiry().getCustomerName(), createInquiry().getId());
        assertNotNull(listCustomerNameAndId);
    }

    @Test
    public void create() {
        equalsInquiryAndInquiryTest(inquiryService.create(createInquiry().getCustomerName(), createInquiry()));
    }

    @Test
    public void update() {
        equalsInquiryAndInquiryTest(inquiryService.update(createInquiry().getCustomerName(), createInquiry().getId(), createInquiry()));
    }

    @Test
    public void delete() {
        inquiryService.delete(createInquiry().getCustomerName(), createInquiry().getId());
        assertNull(inquiryService.listCustomerNameAndId(createInquiry().getCustomerName(), createInquiry().getId()));
    }

}
