package zhandos04.project.SDUCanteen.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fullname")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "fullname")
    private String fullName;

    @Column(name = "uniid", unique = true)
    @Pattern(regexp = "^(1\\d|2[0-4])0(10[0-9]|11[0-9]|120)\\d{3}$",
            message = "uniID")
    private String uniID;

    @Column(name = "password")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_#])[A-Za-z\\d@$!%*?&_#]{8,}$",
            message = "password")
    private String password;

    @Column(name = "phonenumber", unique = true)
    @Pattern(regexp = "^((\\+7|8)\\d{10})$",
            message = "phoneNumber")
    private String phoneNumber;

    @Column(name = "creationdate")
    private Instant creationDate;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return uniID;
    }
}