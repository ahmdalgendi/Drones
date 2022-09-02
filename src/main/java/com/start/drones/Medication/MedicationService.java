package com.start.drones.Medication;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {
    final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> index() {
        return medicationRepository.findAll();
    }
}
