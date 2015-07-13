package com.avol.springboot.jersey.service.impl;

import com.avol.springboot.jersey.domain.PostalCodeDomain;
import com.avol.springboot.jersey.repository.PostalCodeRepository;
import com.avol.springboot.jersey.service.PostalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Durga on 7/10/2015.
 */

@Service
public class PostalCodeServiceImpl implements PostalCodeService {

    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long save(PostalCodeDomain postalCodeDomain) {
        postalCodeDomain = postalCodeRepository.save(postalCodeDomain);
        System.out.println("PostalCodeServiceImpl.save.. " + postalCodeDomain.getId());
        return postalCodeDomain.getId();
    }

    @Override
    public List<PostalCodeDomain> findAll() {
        return postalCodeRepository.findAll();
    }

    @Override
    public PostalCodeDomain findById(Long id) {
        return postalCodeRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        postalCodeRepository.delete(id);
    }
}
