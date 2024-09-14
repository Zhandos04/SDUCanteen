package zhandos04.project.SDUCanteen.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "food")
@Getter
@Setter
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nameoffood")
    private String nameOfFood;
    @Column(name = "price")
    private String price;
    @Column(name = "photo")
    private String photo;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
}
