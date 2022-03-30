package com.erik.animon2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erik.animon2.models.Item;
import com.erik.animon2.repositories.ItemRepo;

@Service
public class ItemService {
	@Autowired
	private ItemRepo itemRepo;
	
	public List<Item> allItems() {
		return itemRepo.findAll();
	}
	
	public Item createItem(Item item) {
		return itemRepo.save(item);
	}
	
	
	public Item findItem(Long id) {
		Optional<Item> optionalItem = itemRepo.findById(id);
		if (optionalItem.isPresent()) {
			return optionalItem.get();
		}else {
			return null;
		}
	}
	
	public Item updateItem(Item item) {
		return itemRepo.save(item);
	}
	
	public void deleteItem(Long id) {
		itemRepo.deleteById(id);
	}
	
	
	
	
}

