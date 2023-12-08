package mk.ukim.finki.wp_lab1.repository.jpa;

import mk.ukim.finki.wp_lab1.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductionRepository extends JpaRepository<Production, Long> {
}
