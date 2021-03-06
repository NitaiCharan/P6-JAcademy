package br.unipe.jacademy.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

    @GetMapping("/")
    public String index() {
    	return "index";
	}
    @GetMapping("/home")
    public String home() {
    	return "home";
	}
    
    @GetMapping("/403")
    public String restrito() {
    	return "403";
    }
}