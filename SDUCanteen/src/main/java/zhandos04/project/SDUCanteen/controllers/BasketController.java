package zhandos04.project.SDUCanteen.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Basket", description="Позволяет добавить еды в корзину")
@CrossOrigin(origins = "*")
public class BasketController {
    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/addToBasket")
    @Operation(summary = "Добавление еды в корзину", description = "Добавляет еду в корзину пользователя.")
    @ApiResponse(responseCode = "202", description = "Еда добавлена в корзину")
    public HttpStatus addToBasket(@RequestBody BasketDTO basketDTO) {
        basketService.save(basketDTO);
        return HttpStatus.ACCEPTED;
    }
    @GetMapping("/allFoodsInBasket")
    @Operation(summary = "Получение всех единиц еды в корзине", description = "Возвращает все единицы еды, добавленные в корзину пользователя.")
    @ApiResponse(responseCode = "200", description = "Список еды в корзине", content = @Content(schema = @Schema(implementation = BasketAnswerDTO.class)))
    public BasketAnswerDTO allFoods() {
        return basketService.getAll();
    }
    @PostMapping("/removeFood")
    @Operation(summary = "Удаление еды из корзины", description = "Удаляет указанную единицу еды из корзины пользователя.")
    @ApiResponse(responseCode = "200", description = "Еда удалена, возвращает обновленный список еды в корзине", content = @Content(schema = @Schema(implementation = BasketAnswerDTO.class)))
    public BasketAnswerDTO removeAtBasket(@RequestBody RemoveBasketDTO removeBasketDTO) {
        basketService.remove(removeBasketDTO.getNameoffood());
        return basketService.getAll();
    }
}
