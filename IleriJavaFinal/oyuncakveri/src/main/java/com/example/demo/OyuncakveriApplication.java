package com.example.demo;

import com.example.demo.entity.Oyuncak;
import com.example.demo.repo.OyuncakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication

public class OyuncakveriApplication implements CommandLineRunner {


	@Autowired
	OyuncakRepository oyuncakRepository;
	public static void main(String[] args) {
		SpringApplication.run(OyuncakveriApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Oyuncak oyuncak = new Oyuncak();
		oyuncak.setAlisTarihi(LocalDate.now());
		oyuncak.setAlisFiyati(30.33);
		oyuncak.setAd("Yoda");
		oyuncak.setTur("Figür");
		oyuncak.setDesi(1);
		oyuncak.setNotlar("Star Wars");
		oyuncakRepository.save(oyuncak);
	}
}
