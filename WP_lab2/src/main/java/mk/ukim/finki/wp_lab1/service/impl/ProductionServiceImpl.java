package mk.ukim.finki.wp_lab1.service.impl;

import mk.ukim.finki.wp_lab1.model.Production;
import mk.ukim.finki.wp_lab1.repository.ProductionRepository;
import mk.ukim.finki.wp_lab1.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {
    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository){
        this.productionRepository = productionRepository;
    }

    @Override
    public Optional<Production> findById(Long id) {
        return this.productionRepository.findById(id);
    }
    @Override
    public List<Production> findAll() {
        return this.productionRepository.findAll();
    }
}
