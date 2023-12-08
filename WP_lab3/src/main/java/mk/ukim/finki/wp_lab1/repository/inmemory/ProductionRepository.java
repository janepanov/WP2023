package mk.ukim.finki.wp_lab1.repository.inmemory;

import mk.ukim.finki.wp_lab1.bootstrap.DataHolder;
import mk.ukim.finki.wp_lab1.model.Production;
import mk.ukim.finki.wp_lab1.service.ProductionService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductionRepository{
    public Optional<Production> findById(Long id) {
        return DataHolder.productions.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public List<Production> findAll(){
        return DataHolder.productions;
    }

    public Production findProductionById(Long id){
        return DataHolder.productions.stream().filter(r -> r.getId().equals(id)).findFirst().orElseThrow(null);
    }
}
