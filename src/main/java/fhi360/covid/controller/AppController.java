package fhi360.covid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {

	@RequestMapping("/api/v1/")
	@ResponseBody
	public String index() {
		return "Welcome to Contrapp Service!";
	}
}