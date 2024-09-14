package zhandos04.project.SDUCanteen.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhandos04.project.SDUCanteen.dto.BasketAnswerDTO;
import zhandos04.project.SDUCanteen.dto.BasketDTO;
import zhandos04.project.SDUCanteen.dto.FoodDTO;
import zhandos04.project.SDUCanteen.models.Basket;
import zhandos04.project.SDUCanteen.models.Food;
import zhandos04.project.SDUCanteen.services.BasketService;
import zhandos04.project.SDUCanteen.services.FoodService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "*")
public class BasketController {
    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final BasketService basketService;

    @Autowired
    public BasketController(FoodService foodService, ModelMapper modelMapper, BasketService basketService) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
        this.basketService = basketService;
    }

    @PostMapping("/addToBasket")
    public ResponseEntity<BasketAnswerDTO> addToBasket(@RequestBody BasketDTO basketDTO) {
        BasketAnswerDTO basketAnswerDTO = convertToFoodDTO(foodService.getByFoodName(basketDTO.getNameoffood()).get());
        Basket basket = convertToBasket(basketDTO);
        basket.setPrice(Integer.parseInt(basketAnswerDTO.getPrice()));
        basketAnswerDTO.setPrice(String.valueOf(Integer.parseInt(basketAnswerDTO.getPrice()) * basketDTO.getQuantity()));
        basketService.save(basket);
        basketAnswerDTO.setSumofprice(String.valueOf(basketService.getSum()));
        return new ResponseEntity<>(basketAnswerDTO, HttpStatus.OK);
    }

    public BasketAnswerDTO convertToFoodDTO(Food food) {
        return modelMapper.map(food, BasketAnswerDTO.class);
    }

    public Basket convertToBasket(BasketDTO basketDTO) {
        return modelMapper.map(basketDTO, Basket.class);
    }
}
