package com.erik.animon2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erik.animon2.models.Gold;

@Repository
public interface GoldRepo extends CrudRepository<Gold, Long> {
	List<Gold> findAll();

}
