package com.start.drones.Medication;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.start.drones.Medication.DTOs.MedicationDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
public class MedicationConfiguration {
    @Bean
    CommandLineRunner MedicationCommandRunner(MedicationRepository repository) {
        return args -> {
            List<Medication> list = new ArrayList<>();
            // generate 10 medications
            for (int i = 0; i < 10; i++) {
                MedicationDTO medicationDTO = getRandomMedication();
                list.add(new Medication(medicationDTO));
            }
            repository.saveAll(list);
        };
    }

    private MedicationDTO getRandomMedication() {
        Faker faker = new Faker(new Locale("en-GB"));
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return new MedicationDTO(
                faker.name().firstName(),
                faker.name().firstName().toUpperCase(),
                faker.number().numberBetween(1, 300)
        );
    }
}
