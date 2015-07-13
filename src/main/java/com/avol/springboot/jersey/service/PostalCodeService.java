package com.avol.springboot.jersey.service;

import com.avol.springboot.jersey.domain.PostalCodeDomain;

import java.util.List;

/**
 * Created by Durga on 7/10/2015.
 */
public interface PostalCodeService {

    Long save(PostalCodeDomain postalCodeDomain);

    List<PostalCodeDomain> findAll();

    PostalCodeDomain findById(Long id);

    void delete(Long id);
}
