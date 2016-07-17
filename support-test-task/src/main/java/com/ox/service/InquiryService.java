package com.ox.service;

import com.ox.domain.Inquiry;

import java.util.List;

public interface InquiryService {

    List<Inquiry> listCustomerName(String customerName);

    Inquiry listCustomerNameAndId(String customerName, Long id);

    Inquiry create(String customerName, Inquiry inquiry);

    Inquiry update(String customerName, Long id, Inquiry inquiry);

    void delete(String customerName, Long id);
}
