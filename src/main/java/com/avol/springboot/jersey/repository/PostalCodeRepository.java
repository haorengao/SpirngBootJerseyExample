package com.avol.springboot.jersey.repository;

import com.avol.springboot.jersey.domain.PostalCodeDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Boot provided repository class,
 * basic DAO CRUD operations logic has been provided by JpaRepository.
 * Default implementation class is <code>SimpleJpaRepository</code>. It uses EntityManger as persistent context.
 * Created by Durga on 7/10/2015.
 */

public interface PostalCodeRepository extends JpaRepository<PostalCodeDomain, Long> {
}
