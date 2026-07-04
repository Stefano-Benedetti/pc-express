package it.progettosiw.pcexpress;

import it.progettosiw.pcexpress.model.PC;
import it.progettosiw.pcexpress.repository.PCRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DataLoader implements CommandLineRunner {

    private final PCRepository pcRepository;

    public DataLoader(PCRepository pcRepository) {
        this.pcRepository = pcRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        PC p1 = new PC();
        p1.setCodice(100010L);
        p1.setNome("Drago PC");
        p1.setCpu("Intel Core i5-14600K");
        p1.setGpu("RTX 4060");
        p1.setRam("32GB DDR5");
        p1.setRom("1TB NVMe SSD");
        p1.setCasePc("NZXT H5 Flow");
        p1.setPrezzo(999.99F);
        p1.setDisponibilita(12);
        p1.setImmagine(Files.readAllBytes(Paths.get("src/main/resources/static/images/drago_pc.jpg")));
        System.out.println(p1.getImmagine().getClass());
        pcRepository.save(p1);


        PC p2 = new PC();
        p2.setCodice(100011L);
        p2.setNome("Tigre PC");
        p2.setCpu("AMD Ryzen 7 7800X3D");
        p2.setGpu("RX 7800 XT");
        p2.setRam("32GB DDR5");
        p2.setRom("2TB NVMe SSD");
        p2.setCasePc("Fractal Design Meshify 2 Compact");
        p2.setPrezzo(1599.90F);
        p2.setDisponibilita(7);
        p2.setImmagine(Files.readAllBytes(Paths.get("src/main/resources/static/images/tigre_pc.avif")));
        pcRepository.save(p2);

    }
}
