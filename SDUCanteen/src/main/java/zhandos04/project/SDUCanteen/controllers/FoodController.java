package zhandos04.project.SDUCanteen.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhandos04.project.SDUCanteen.dto.FoodDTO;
import zhandos04.project.SDUCanteen.models.Food;
import zhandos04.project.SDUCanteen.services.FoodService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food")
@CrossOrigin(origins = "*")
public class FoodController {
    private final FoodService foodService;
    private final ModelMapper modelMapper;

    @Autowired
    public FoodController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<FoodDTO> getAll() {
        return foodService.getAllFoods().stream().map(this::convertToFoodDTO)
                .collect(Collectors.toList());
    }
    public FoodDTO convertToFoodDTO(Food food) {
        return modelMapper.map(food, FoodDTO.class);
    }
}
