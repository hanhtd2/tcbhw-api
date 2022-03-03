package com.hanhtd26.tcbhwapi;

import com.hanhtd26.tcbhwapi.restservice.utils.ShutdownAppHook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TcbhwApiApplication {

	public static void main(String[] args) {
		/**
		 * This line: Register App shutdown hook
		 * This method can trigger when application shutdown (include ctrl-C)
		 * more information: https://www.tutorialspoint.com/java/lang/runtime_addshutdownhook.htm
		 */
		Runtime.getRuntime().addShutdownHook(new ShutdownAppHook());

		//Application run
		SpringApplication.run(TcbhwApiApplication.class, args);
	}

}
