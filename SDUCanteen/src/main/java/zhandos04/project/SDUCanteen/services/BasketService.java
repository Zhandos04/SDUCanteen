package zhandos04.project.SDUCanteen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhandos04.project.SDUCanteen.models.Basket;
import zhandos04.project.SDUCanteen.repositories.BasketRepository;

@Service
@Transactional(readOnly = true)
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }
    public Integer getSum() {
        return basketRepository.findTotalPrice();
    }
    @Transactional
    public void save(Basket basket){
        basketRepository.save(basket);
    }
}
