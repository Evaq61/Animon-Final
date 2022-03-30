package com.erik.animon2.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erik.animon2.models.Item;

@Repository
public interface ItemRepo extends CrudRepository<Item, Long>{
	List<Item> findAll();
}
