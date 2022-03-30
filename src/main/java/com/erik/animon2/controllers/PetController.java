package com.erik.animon2.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.erik.animon2.models.Item;
import com.erik.animon2.models.Pet;
import com.erik.animon2.models.User;
import com.erik.animon2.services.GoldService;
import com.erik.animon2.services.ItemService;
import com.erik.animon2.services.PetService;
import com.erik.animon2.services.UserService;

@Controller
public class PetController {

	@Autowired
	private UserService userServ;
	@Autowired
	private PetService petServ;
	@Autowired
	private GoldService goldServ;
	@Autowired
	private ItemService itemServ;

	// CREATE PET FORM ##############################################
	@GetMapping("/new/pet")
	public String newPet(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			model.addAttribute("thisUser", thisUser);
			model.addAttribute("newPet", new Pet());
			return "createpet.jsp";
		}
	}

	// CREATE HANDLER -----------------------------
	@PostMapping("/create/pet")
	public String processCreatePet(@Valid @ModelAttribute("newPet") Pet pet, BindingResult result, HttpSession session,
			Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (result.hasErrors()) {
			User thisUser = userServ.findOne(userId);
			model.addAttribute("thisUser", thisUser);
			return "createpet.jsp";
		} else {
			petServ.createPet(pet);
			return "redirect:/home";
		}
	}

	// HOME PAGE ###########################################################
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			List<Pet> myPet = thisUser.getPets();
			Pet pet = myPet.get(0);
			long lastFedminutes = petServ.diff(pet.getLastFed(), new Date());
			petServ.subractHunger(pet, (int) lastFedminutes);
			petServ.updatePet(pet);
			long contestDiff = petServ.diff(pet.getLastContest(), new Date());   
			System.out.println(contestDiff);
			model.addAttribute("contestDiff", contestDiff);
			model.addAttribute("myPet", myPet);
			model.addAttribute("thisUser", thisUser);
			return "home.jsp";
		}
	}

	// PLay Render JSP ##########################
	@GetMapping("/play")
	public String playWithMe(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
//    		List<Pet> allPets = petServ.allPets();
			model.addAttribute("thisUser", thisUser);
//    		model.addAttribute("allPets", allPets);
			return "play.jsp";
		}
	}

	// Play ###################################################
	@GetMapping("/tag/{id}")
	public String play(@PathVariable("id") Long id) {
		Pet pet = petServ.findPet(id);
		pet.tag();
		petServ.updatePet(pet);
		return "redirect:/play";
	}

	@GetMapping("/ball/{id}")
	public String ball(@PathVariable("id") Long id) {
		Pet pet = petServ.findPet(id);
		Boolean playedBall = pet.ball();
		if(playedBall == true) {
			List<Item> ballList = pet.getOwner().getItems();
			for (Item item : ballList) {
				if (item.getName().equals("Toy Ball")) {
					Long itemId = item.getId();
					itemServ.deleteItem(itemId);

					break;
				}
			}	
		}
		petServ.updatePet(pet);
		return "redirect:/play";
	}
	
	@GetMapping("/rope/{id}")
	public String rope(@PathVariable("id") Long id) {
		Pet pet = petServ.findPet(id);
		Boolean playedTugOfWar = pet.rope();
		if(playedTugOfWar == true) {
			List<Item> ropeList = pet.getOwner().getItems();
			for (Item item: ropeList) {
				if (item.getName().equals("Toy Rope")) {
					Long itemId = item.getId();
					itemServ.deleteItem(itemId);
					break;
				}
			}
		}
		petServ.updatePet(pet);
		return "redirect:/play";
	}
	

	// Contest JSP Render ################################
	@GetMapping("/contest/{id}")
	public String contest(@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User user = userServ.findOne(userId);
			int medal = petServ.contest(user);
			List <Pet> myPet = user.getPets();
			model.addAttribute("myPet", myPet);
			goldServ.updateGold(user.getGold());
			model.addAttribute("medal", medal);
			return "contest.jsp";
		}
	}

	// Sleep Me ###################################
	@GetMapping("/sleep/{id}")
	public String sleep(@PathVariable("id") Long id) {
		Pet pet = petServ.findPet(id);
		pet.sleep();
		petServ.updatePet(pet);
		return "redirect:/home";
	}

	// Feed Me ##################################
	@GetMapping("/feed/{id}")
	public String feed(@PathVariable("id") Long id) {
		try {
			Thread.sleep(1 * 1300);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		Pet pet = petServ.findPet(id);
		Boolean isFed = pet.feed();
		if (isFed == true) {
			petServ.updatePet(pet);
			List<Item> foodList = pet.getOwner().getItems();
			for (Item item : foodList) {
				if (item.getName().equals("Pet Food")) {
					Long itemId = item.getId();
					itemServ.deleteItem(itemId);
					break;
				}
			}
		}
		petServ.updatePet(pet);
		return "redirect:/home";
	}

	// Clean Me ############################
	@GetMapping("/clean/{id}")
	public String clean(@PathVariable("id") Long id) {
		Pet pet = petServ.findPet(id);
		Boolean isCleaned = pet.clean();
		if (isCleaned == true) {
			List<Item> soapList = pet.getOwner().getItems();
			for (Item item : soapList) {
				if (item.getName().equals("Pet Soap")) {
					Long itemId = item.getId();
					itemServ.deleteItem(itemId);

					break;
				}
			}
		}
		petServ.updatePet(pet);
		return "redirect:/home";
	}

}
