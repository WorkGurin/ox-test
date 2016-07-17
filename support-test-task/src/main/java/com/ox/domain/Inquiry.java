package com.ox.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ox.json.JsonDateDeserializer;
import com.ox.json.JsonDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Inquiry {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Column(columnDefinition = "TEXT")
    private String customerName;

    @Size(min = 1, max = 5)
    @ElementCollection
    private List<String> attributes;

    @ManyToOne
    private Topic topic;

    public Inquiry() {
    }

    public Inquiry(String customerName) {
        this.setCustomerName(customerName);
    }

    public Inquiry(String customerName, Date createDate) {
        this.setCustomerName(customerName);
        this.setCreateDate(createDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Inquiry [customerName=" + customerName + "]";
    }

}