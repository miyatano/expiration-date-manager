package com.github.miyatano.edm.dao;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "food")
@NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f ORDER BY f.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Food {

    @Id
    @SequenceGenerator(name = "foodSequence", sequenceName = "food_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "foodSequence")
    private Integer id;

    @Column(length = 40)
    private String name;

    private String expireDate;

    public Food() {
    }

    public Food(String name, String expireDate) {
        this.name = name;
        this.expireDate = expireDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

}

