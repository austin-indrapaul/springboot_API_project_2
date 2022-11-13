package demo.springboot_API_project_2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.springboot_API_project_2.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByUsername(String username);

}
