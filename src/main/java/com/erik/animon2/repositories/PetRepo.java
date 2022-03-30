package com.erik.animon2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erik.animon2.models.Pet;

@Repository
public interface PetRepo extends CrudRepository<Pet, Long> {
	List<Pet> findAll();
}
