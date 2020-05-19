package net.slipp.franchise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        net.slipp.franchise.apis.RecruitApi a;
        SpringApplication.run(App.class, args);
    }
}
