package com.ox.service;

import com.ox.domain.Inquiry;
import com.ox.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {

    private InquiryRepository inquiryRepository;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    public Iterable<Inquiry> list() {
        return inquiryRepository.findAll();
    }

    public Iterable<Inquiry> listCustomerName(String customerName) {
        return inquiryRepository.findByCustomerName(customerName);
    }

    public Inquiry read(String customerName, Long id) {
        return inquiryRepository.findByCustomerNameAndId(customerName, id);
    }

    public Inquiry save(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }

    public void delete(String customerName, Long id) {
        inquiryRepository.delete(id);
    }
}
