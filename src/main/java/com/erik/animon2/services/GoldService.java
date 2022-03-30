package com.erik.animon2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erik.animon2.models.Gold;
import com.erik.animon2.repositories.GoldRepo;


@Service
public class GoldService {
	@Autowired
	private GoldRepo goldRepo;

	public List<Gold> allGold() {
		return goldRepo.findAll();
	}

	public Gold createGold(Gold g) {
		return goldRepo.save(g);
	}

	public Gold findGold(Long id) {
		Optional<Gold> optionalGold = goldRepo.findById(id);
		if(optionalGold.isPresent()) {
			return optionalGold.get();
		}else {
			return null;
		}
	}
		
		
	public Gold updateGold(Gold g) {
			return goldRepo.save(g);
	}
	public void deleteGold(Long id) {
		goldRepo.deleteById(id);
	}
		
	

	
		
	
}
