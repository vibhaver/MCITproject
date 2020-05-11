package com.mcit.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping("/user")
public class DashboardController {
	
//	@GetMapping({"/", "/login"})
	public ModelAndView createForm()
	{
		return null;

	}
	
//	@PostMapping("/createBook")
//	public ModelAndView createBook(@ModelAttribute Book book) {
//		String myBook = createBook(book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice());
//		String message = creation.replace("{book}", myBook);
//		return new ModelAndView("bookCreated", "message", message);
//	}
	
	private static String createBook(String title, String author, Integer year, Float price)
	{
		final String uri = "http://localhost:8081/book/create/" + title + "/" + author + "/" +  year + "/" + price;
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    return result;
	}

}
