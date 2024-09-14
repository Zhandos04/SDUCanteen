package zhandos04.project.SDUCanteen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhandos04.project.SDUCanteen.models.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    @Query("SELECT SUM(b.price * b.quantity) FROM Basket b")
    Integer findTotalPrice();

}
