package org.acme.hibernate.orm;

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
