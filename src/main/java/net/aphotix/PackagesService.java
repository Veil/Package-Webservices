package net.aphotix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for this Packages web service
 */
@SpringBootApplication
public class PackagesService {

	/**
	 * Start the web service
	 *
	 * @see SpringApplication#run(Object, String...) For valid arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PackagesService.class, args);
	}
}
