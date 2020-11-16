package com.sunnyhith.reactiveclient.reactivemongoclient;

import com.sunnyhith.reactiveclient.reactivemongoclient.model.Employee;
import com.sunnyhith.reactiveclient.reactivemongoclient.model.EmployeeEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactiveMongoClientApplication {

	@Bean
	WebClient webClient() {
		return WebClient.create("http://localhost:8082/rest/employee");
	}

	@Bean
	CommandLineRunner commandLineRunner(WebClient webClient){
		return strings -> {
			webClient.get().uri("/all").retrieve().bodyToFlux(Employee.class)
					.filter(employee -> employee.getName().equals("Peter"))
					.flatMap(employee -> webClient.get()
							.uri("/{id}/events", employee.getId())
							.retrieve().bodyToFlux(EmployeeEvent.class)

					).subscribe(System.out::println);
			         //.subscribe(employeeEvent -> System.out.println(employeeEvent));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoClientApplication.class, args);
	}

}
