package com.github.miyatano.expiration_date_manager.dao;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
@Cacheable
public class Food extends PanacheEntity {

    @Column
    public String name;

    @Column
    public String expireDate;

}
