package com.ox.service;

import com.ox.domain.Inquiry;
import com.ox.repository.TopicRepository;
import com.ox.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    private InquiryRepository inquiryRepository;
    private TopicRepository topicRepository;

    @Autowired
    public InquiryServiceImpl(InquiryRepository inquiryRepository, TopicRepository topicRepository) {
        this.inquiryRepository = inquiryRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Inquiry> listCustomerName(String customerName) {
        return inquiryRepository.findByCustomerName(customerName);
    }

    @Override
    public Inquiry listCustomerNameAndId(String customerName, Long id) {
        return inquiryRepository.findByCustomerNameAndId(customerName, id);
    }

    @Override
    @Transactional
    public Inquiry create(String customerName, Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    @Override
    public void delete(String customerName, Long id) {
        inquiryRepository.delete(id);
    }

    @Override
    public Inquiry update(String customerName, Long id, Inquiry update) {
        Inquiry inquiry = inquiryRepository.findOne(id);
        if (update.getCustomerName() != null) {
            inquiry.setCustomerName(update.getCustomerName());
            inquiry.setDescription(update.getDescription());
            inquiry.setCreateDate(update.getCreateDate());
            inquiry.setTopic(update.getTopic());
            inquiry.setAttributes(update.getAttributes());
        }
        return inquiryRepository.save(inquiry);
    }

}
