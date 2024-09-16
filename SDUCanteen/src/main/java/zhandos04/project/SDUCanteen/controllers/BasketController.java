package zhandos04.project.SDUCanteen.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import zhandos04.project.SDUCanteen.dto.BasketAnswerDTO;
import zhandos04.project.SDUCanteen.dto.BasketDTO;
import zhandos04.project.SDUCanteen.dto.BasketResponseDTO;
import zhandos04.project.SDUCanteen.jwt.JwtFilter;
import zhandos04.project.SDUCanteen.jwt.JwtService;
import zhandos04.project.SDUCanteen.models.Basket;
import zhandos04.project.SDUCanteen.models.User;
import zhandos04.project.SDUCanteen.services.BasketService;
import zhandos04.project.SDUCanteen.services.FoodService;
import zhandos04.project.SDUCanteen.services.UserService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "*")
public class BasketController {
    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/addToBasket")
    public HttpStatus addToBasket(@RequestBody BasketDTO basketDTO) {
        basketService.save(basketDTO);
        return HttpStatus.ACCEPTED;
    }
    @GetMapping("/allFoodsInBasket")
    public BasketAnswerDTO allFoods() {
        return basketService.getAll();
    }
}
