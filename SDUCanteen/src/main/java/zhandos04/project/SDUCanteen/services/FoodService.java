package zhandos04.project.SDUCanteen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhandos04.project.SDUCanteen.dto.FoodDTO;
import zhandos04.project.SDUCanteen.models.Food;
import zhandos04.project.SDUCanteen.repositories.FoodRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
    public Optional<Food> getByFoodName(String foodname) {
        return foodRepository.getFoodByNameOfFood(foodname);
    }
}
