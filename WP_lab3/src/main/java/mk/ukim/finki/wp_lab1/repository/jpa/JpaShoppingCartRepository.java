package mk.ukim.finki.wp_lab1.repository.jpa;

import mk.ukim.finki.wp_lab1.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
