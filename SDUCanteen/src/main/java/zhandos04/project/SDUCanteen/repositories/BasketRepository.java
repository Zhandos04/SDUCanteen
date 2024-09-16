package zhandos04.project.SDUCanteen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zhandos04.project.SDUCanteen.models.Basket;
import zhandos04.project.SDUCanteen.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

    @Query("SELECT SUM(b.price) FROM Basket b WHERE b.user.id = :userId")
    Integer findTotalPriceByUserId(Integer userId);

    @Query("SELECT b FROM Basket b WHERE b.nameoffood = :nameOfFood AND b.user.id = :userId")
    Optional<Basket> findByNameOfFoodAndUserId(String nameOfFood,Integer userId);

    @Query("SELECT b FROM Basket b WHERE b.user.id = :userId")
    List<Basket> findAllByUserID(int userId);
}
