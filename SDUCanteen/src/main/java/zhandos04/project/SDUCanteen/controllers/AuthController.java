package zhandos04.project.SDUCanteen.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import zhandos04.project.SDUCanteen.config.CustomAuthenticationProvider;
import zhandos04.project.SDUCanteen.dto.AuthDTO;
import zhandos04.project.SDUCanteen.dto.UserDTO;
import zhandos04.project.SDUCanteen.jwt.JwtService;
import zhandos04.project.SDUCanteen.models.User;
import zhandos04.project.SDUCanteen.services.UserService;
import zhandos04.project.SDUCanteen.util.IncorrectJSONException;
import zhandos04.project.SDUCanteen.util.UserAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final CustomAuthenticationProvider authenticationProvider;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, JwtService jwtService, CustomAuthenticationProvider authenticationProvider, ModelMapper modelMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
        this.modelMapper = modelMapper;
    }

    @PostMapping( "/signup")
    public HttpStatus register(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) throws UserAlreadyExistsException, IncorrectJSONException {
        if (bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            StringBuilder str = new StringBuilder();
            for (FieldError error : errors){
                str.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(";\n");
            }
            throw new IncorrectJSONException(str.toString());
        }
        Optional<User> userOptional = userService.getUser(userDTO.getUniID());
        if (userOptional.isPresent()){
            throw new UserAlreadyExistsException("a user with that username already exists");
        }
        userService.save(convertToUser(userDTO));
        return HttpStatus.ACCEPTED;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTO> login(@RequestBody @Valid UserDTO userDTO) throws BadCredentialsException {
        Optional<User> userOptional = userService.getUser(userDTO.getUniID());
        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException("there is no user with that username");
        }
        authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUniID(), userDTO.getPassword()));
        AuthDTO authDTO = modelMapper.map(userOptional.get(), AuthDTO.class);
        authDTO.setToken(jwtService.generateToken(authDTO.getUniID()));
        return new ResponseEntity<>(authDTO, HttpStatus.OK);

    }
    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}