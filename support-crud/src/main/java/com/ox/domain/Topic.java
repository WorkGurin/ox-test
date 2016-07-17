package com.ox.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topic {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @SuppressWarnings("unused")
    private Topic() {
    }

    public Topic(String name) {
        this.setName(name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Topic [name=" + name + "]";
    }


}