package william.expensemanagerapi;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class ExpenseManagerApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerApiApplication.class, args);
		System.out.println("Application Running");
	}

	@PostConstruct
  public void init(){
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
}
