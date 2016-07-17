package com.ox.repository;

import com.ox.domain.Inquiry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends CrudRepository<Inquiry, Long> {

    List<Inquiry> findByCustomerName(String customerName);

    Inquiry findByCustomerNameAndId(String customerName, Long id);

}
