package mk.ukim.finki.wp_lab1.service;

import mk.ukim.finki.wp_lab1.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {
    Optional<Production> findById(Long id);

    List<Production> findAll();
}
