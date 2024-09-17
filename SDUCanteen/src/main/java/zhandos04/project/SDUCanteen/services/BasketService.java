package zhandos04.project.SDUCanteen.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhandos04.project.SDUCanteen.dto.BasketAnswerDTO;
import zhandos04.project.SDUCanteen.dto.BasketDTO;
import zhandos04.project.SDUCanteen.dto.BasketResponseDTO;
import zhandos04.project.SDUCanteen.models.Basket;
import zhandos04.project.SDUCanteen.models.Food;
import zhandos04.project.SDUCanteen.models.User;
import zhandos04.project.SDUCanteen.repositories.BasketRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BasketService {
    private final BasketRepository basketRepository;
    private final ModelMapper modelMapper;
    private final FoodService foodService;
    private final UserService userService;

    @Autowired
    public BasketService(BasketRepository basketRepository, ModelMapper modelMapper, FoodService foodService, UserService userService) {
        this.basketRepository = basketRepository;
        this.modelMapper = modelMapper;
        this.foodService = foodService;
        this.userService = userService;
    }
    public Integer getSum(int id) {
        return basketRepository.findTotalPriceByUserId(id);
    }
    @Transactional
    public void save(BasketDTO basketDTO){
        String username = getUsername();
        User user = userService.getUserByID(username).get();
        Optional<Basket> existingBasket = basketRepository.findByNameOfFoodAndUserId(basketDTO.getNameoffood(), user.getId());
        Food food = foodService.getByFoodName(basketDTO.getNameoffood()).get();
        Basket basket;
        if(existingBasket.isPresent()) {
            basket = existingBasket.get();
            basket.setQuantity(basket.getQuantity() + basketDTO.getQuantity());
        } else {
            basket = convertToBasket(basketDTO);
            basket.setUser(user);
        }
        basket.setPrice(Integer.parseInt(food.getPrice()) * basket.getQuantity());
        basketRepository.save(basket);
    }
    public BasketAnswerDTO getAll() {
        String username = getUsername();
        User user = userService.getUserByID(username).get();
        List<BasketResponseDTO> list = basketRepository.findAllByUserID(user.getId()).stream().map(this::convertToBasketResponseDTO).
                collect(Collectors.toList());
        for (BasketResponseDTO basketResponseDTO : list) {
            basketResponseDTO.setPhoto(foodService.getByFoodName(basketResponseDTO.getNameoffood()).get().getPhoto());
        }
        return new BasketAnswerDTO(list, getSum(user.getId()));
    }
    @Transactional
    public void remove(String nameoffood) {
        String username = getUsername();
        User user = userService.getUserByID(username).get();
        basketRepository.deleteBasketByNameoffoodAndUserID(nameoffood, user.getId());
    }
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }
        return username;
    }

    public Basket convertToBasket(BasketDTO basketDTO) {
        return modelMapper.map(basketDTO, Basket.class);
    }
    public BasketResponseDTO convertToBasketResponseDTO(Basket basket) {
        return modelMapper.map(basket, BasketResponseDTO.class);
    }
}
