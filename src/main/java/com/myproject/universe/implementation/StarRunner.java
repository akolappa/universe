package com.myproject.universe.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StarRunner implements CommandLineRunner {

    @Autowired
    private StarRouteSolution starRouteSolution;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running the Application");
        System.out.println();
        starRouteSolution.findSolutions();
    }
}
