package com.dcpear.customer.repo;

import com.dcpear.customer.domain.Customer;
import com.dcpear.customer.domain.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")//rename
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Collection<Customer> findAllByLastName(@Param("lastName") String lastName);

    Collection<Customer> findAllByLevel(@Param("level") Level level);

    Optional<Customer> findById(@Param("id") Integer id);

}
