package com.ikon.alexx.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikon.alexx.model.RoleDTO;
import com.ikon.alexx.model.SpecialityDTO;
import com.ikon.alexx.model.UserDTO;
import com.ikon.alexx.service.SpecialityService;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private SpecialityService specService;

	@RequestMapping(value = "/all")
	public String findAll(Model model) {

		UserDTO user = new UserDTO();
		// user.setId((long)1);
		user.setUserName("tibi");
		user.setEmail("ceva mail");
		user.setIsEnable(0);
		user.setPassword("ha ha ha");
		RoleDTO r = new RoleDTO();
		// r=roleRepo.findOne((long) 1);
		r.setRole("javas");
		// r.setId(2);
		// roleRepo.save(r);
		// user.setRoleId(r);
		System.out.println(user.toString());

		model.addAttribute("user", user);
		// userRepo.save(user);
		return "index";
	}

	@RequestMapping(value = "/specs")
	public String findAllMesterSpecs(@RequestParam("id") String id, Model model) throws SQLException {
		List<SpecialityDTO> specs = specService.getAllMesterSpeciality(id);
		model.addAttribute("specs", specs);
		return "mspec";
	}

	@RequestMapping(value = "/spec")
	public String findAllSpecs(Model model) throws SQLException {
		List<SpecialityDTO> specs = specService.getAllSpeciality();
		model.addAttribute("specs", specs);
		return "mspec";
	}
}
