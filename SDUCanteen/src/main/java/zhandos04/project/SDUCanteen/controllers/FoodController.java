package zhandos04.project.SDUCanteen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Food", description="Взаймодействие с еды")
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
    @Operation(summary = "Get all food items", description = "Returns a list of all available food items in the canteen.")
    @ApiResponse(responseCode = "200", description = "List of food items", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FoodDTO.class)))
    public List<FoodDTO> getAll() {
        return foodService.getAllFoods().stream().map(this::convertToFoodDTO)
                .collect(Collectors.toList());
    }
    public FoodDTO convertToFoodDTO(Food food) {
        return modelMapper.map(food, FoodDTO.class);
    }
}
