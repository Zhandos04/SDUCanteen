package zhandos04.project.SDUCanteen.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketAnswerDTO {
    private List<BasketResponseDTO> basket;
    private Integer sum;
    public BasketAnswerDTO(List<BasketResponseDTO> basket, Integer sum) {
        this.basket = basket;
        this.sum = sum;
    }
}
