package com.erik.animon2.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.erik.animon2.models.Gold;
import com.erik.animon2.models.LoginUser;
import com.erik.animon2.models.User;
import com.erik.animon2.services.GoldService;
import com.erik.animon2.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userServ;
	@Autowired
	private GoldService goldServ;
	
	
	// Login Landing Page
	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId != null) {
			return "redirect:/home";
		} else {
			model.addAttribute("newUser", new User());
			model.addAttribute("newLogin", new LoginUser());
			return "index1.jsp";
		}

	}
	
	// Register New User
	@PostMapping ("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser,
							BindingResult result,
							Model model,
							HttpSession sesh) {
		userServ.register(newUser, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index1.jsp";
		} else {
			Gold gold = new Gold(newUser);
			goldServ.createGold(gold);
			sesh.setAttribute("user_id", newUser.getId());
			return "redirect:/new/pet";
		}
		
	}

	// LOGIN##############################################################
		@PostMapping("/login")
		public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
				HttpSession session) {
			User user = userServ.login(newLogin, result);

			if (result.hasErrors()) {
				model.addAttribute("newUser", new User());
				return "index.jsp";
			} else {
				session.setAttribute("user_id", user.getId());
				if(user.getPets().size() == 0) {
					return "redirect:/new/pet";
				}else {
					return "redirect:/home";
				}
				
			}

		}
		
		
		// Logout
		@GetMapping("/logout")
		public String logout(HttpSession sesh) {
			sesh.invalidate();
			return "redirect:/";
		}
		
		
		
		
		
		
		
		
		
		

}
