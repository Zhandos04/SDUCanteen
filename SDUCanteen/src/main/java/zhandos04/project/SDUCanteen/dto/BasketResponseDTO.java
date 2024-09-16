package zhandos04.project.SDUCanteen.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketResponseDTO {
    private String nameoffood;
    private String photo;
    private Integer quantity;
    private Integer price;
}
