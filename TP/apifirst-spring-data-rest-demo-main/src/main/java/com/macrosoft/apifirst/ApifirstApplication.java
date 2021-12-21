package com.macrosoft.apifirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApifirstApplication  implements CommandLineRunner{



    public static void main(String args[]) {
        SpringApplication.run(ApifirstApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

		System.out.println("<--API  REST in Action --");

    }

 
}
