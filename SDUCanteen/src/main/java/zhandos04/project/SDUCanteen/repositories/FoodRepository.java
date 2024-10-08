package zhandos04.project.SDUCanteen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhandos04.project.SDUCanteen.models.Food;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    Optional<Food> getFoodByNameOfFood(String foodname);
}
