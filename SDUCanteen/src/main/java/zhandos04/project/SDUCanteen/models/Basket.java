package zhandos04.project.SDUCanteen.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "basket")
@Getter
@Setter
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nameoffood")
    private String nameoffood;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private int price;
}
