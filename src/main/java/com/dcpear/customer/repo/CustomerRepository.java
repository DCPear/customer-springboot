package com.dcpear.customer.repo;

import com.dcpear.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")//rename
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional <Customer> findByFirstName(@Param("firstName") String firstName);

    Collection<Customer> findAllByLastName(@Param("lastName") String lastName);

    Optional<Customer> findByLevel(@Param("level") String level);

    Optional<Customer> findById(@Param("id") String id);

}
