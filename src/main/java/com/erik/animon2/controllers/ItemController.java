package com.erik.animon2.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.erik.animon2.models.Item;
import com.erik.animon2.models.User;
import com.erik.animon2.services.GoldService;
import com.erik.animon2.services.ItemService;
import com.erik.animon2.services.UserService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemServ;
	@Autowired
	private UserService userServ;
	@Autowired
	private GoldService goldServ;

	@GetMapping("/store")
	public String store(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			model.addAttribute("thisUser", thisUser);
			return "store.jsp";
		}
	}

	@GetMapping("/buypetfood")
	public String buyFood(HttpSession session, Model model) {
		try {
			Thread.sleep(1 * 1300);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			if (thisUser.getGold().getGold() >= 50) {
				Item petFood = new Item("Pet Food", "food", "feed this to your animon decrease hunger by 20", 50, 1,
						thisUser);
				itemServ.createItem(petFood);
				thisUser.getGold().setGold(thisUser.getGold().getGold() - 50);
				goldServ.updateGold(thisUser.getGold());
				return "redirect:/store";
			} else {
				return "redirect:/store";
			}
		}
	}

	@GetMapping("/buyrope")
	public String buyRope(HttpSession session, Model model) {
		try {
			Thread.sleep(1 * 1300);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			if (thisUser.getGold().getGold() >= 100) {
				Item rope = new Item("Toy Rope", "toy",
						"Play tug of war with your animon. Happiness increases by 5, decrease energy by 10", 100, 1,
						thisUser);
				itemServ.createItem(rope);
				thisUser.getGold().setGold(thisUser.getGold().getGold() - 100);
				goldServ.updateGold(thisUser.getGold());
				return "redirect:/store";
			} else {
				return "redirect:/store";
			}
		}
		
	}
	
	@GetMapping("/buyball")
	public String buyBall(HttpSession session, Model model) {
		try {
			Thread.sleep(1 * 1300);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			if (thisUser.getGold().getGold() >= 300) {
				Item ball = new Item("Toy Ball", "toy",
						"Play catch with your animon, happiness increases by 10 and decrease energy by 20", 300, 1, thisUser);
				itemServ.createItem(ball);
				thisUser.getGold().setGold(thisUser.getGold().getGold() - 300);
				goldServ.updateGold(thisUser.getGold());
				return "redirect:/store";
			}else {
				return "redirect:/store";
			}
		}
	}
	
	@GetMapping("/buysoap")
	public String buySoap(HttpSession session, Model model) {
		try {
			Thread.sleep(1 * 1300);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User thisUser = userServ.findOne(userId);
			if(thisUser.getGold().getGold() >= 400) {
				Item soap = new Item("Pet Soap", "toy", "Clean your pet. Cleanliness increases by 5", 400, 1, thisUser);
				itemServ.createItem(soap);
				thisUser.getGold().setGold(thisUser.getGold().getGold() - 400);
				goldServ.updateGold(thisUser.getGold());
				return "redirect:/store";
			}else {
				return "redirect:/store";
			}
		}
	}
	
	
	
	
	

}
