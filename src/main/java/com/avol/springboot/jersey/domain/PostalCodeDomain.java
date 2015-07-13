package com.avol.springboot.jersey.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Durga on 7/10/2015.
 */

@Data
@Entity(name = "POSTAL_CODE")
public class PostalCodeDomain {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "AREA_NAME", nullable = false)
    private String areaName;
}
