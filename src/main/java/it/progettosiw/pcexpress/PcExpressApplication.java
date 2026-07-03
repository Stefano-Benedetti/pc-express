package it.progettosiw.pcexpress;

import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class PcExpressApplication{



    public static void main(String[] args) {
        SpringApplication.run(PcExpressApplication.class, args);

    }

}
