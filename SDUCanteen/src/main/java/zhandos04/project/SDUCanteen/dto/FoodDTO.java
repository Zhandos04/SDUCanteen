package zhandos04.project.SDUCanteen.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    private String nameOfFood;
    private String price;
    private String photo;
    private String description;
    private String category;
}
