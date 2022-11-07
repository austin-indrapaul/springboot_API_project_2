package demo.springboot_API_project_2.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(value = { "", "/", "home" })
	public String home() {
		return "Welcome - springboot_API_Project2";
	}

	@GetMapping("/dashboard")
	public Map<String, String> getDashboard() {
		Map<String, String> urls = new LinkedHashMap();

		urls.put("Homepage", "http://localhost:8080/home");
		urls.put("Dashboard", "http://localhost:8080/dashboard");
		urls.put("List of all customers", "http://localhost:8080/customers");
		urls.put("Read a paticular customers", "http://localhost:8080/customer/1");
		urls.put("Create new customer", "http://localhost:8080/customer");
		urls.put("Update a customer", "http://localhost:8080/customer/1");
		urls.put("Delete a customer", "http://localhost:8080/customer/1");

		return urls;
	}

}
