package zhandos04.project.SDUCanteen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zhandos04.project.SDUCanteen.models.User;
import zhandos04.project.SDUCanteen.repositories.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByID(username).get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }


    public void save(User user){
        user.setCreationDate(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public Optional<User> getUserByID(String uniID) {
        return userRepository.findByUniID(uniID);
    }
    public Optional<User> getUserByPhoneNumber(String uniID) {
        return userRepository.findByPhoneNumber(uniID);
    }
}