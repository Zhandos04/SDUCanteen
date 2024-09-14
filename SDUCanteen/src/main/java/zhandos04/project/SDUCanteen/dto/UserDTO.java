package zhandos04.project.SDUCanteen.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "fullname")
    private String fullName;

    @Pattern(regexp = "^(1\\d|2[0-4])0(10[0-9]|11[0-9]|120)\\d{3}$",
            message = "Something wrong with ID")
    private String uniID;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_#])(?=.*[a-z])[A-Za-z\\d@$!%*?&_#]{8,}$",
            message = "password")
    private String password;

    @Pattern(regexp = "^((\\+7|8)\\d{10})$",
            message = "phoneNumber")
    private String phoneNumber;
}