package zhandos04.project.SDUCanteen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zhandos04.project.SDUCanteen.dto.BasketAnswerDTO;
import zhandos04.project.SDUCanteen.dto.BasketDTO;
import zhandos04.project.SDUCanteen.dto.RemoveBasketDTO;
import zhandos04.project.SDUCanteen.services.BasketService;

import java.util.Map;


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
    @PostMapping("/removeFood")
    public BasketAnswerDTO removeAtBasket(@RequestBody RemoveBasketDTO removeBasketDTO) {
        basketService.remove(removeBasketDTO.getNameoffood());
        return basketService.getAll();
    }
}
