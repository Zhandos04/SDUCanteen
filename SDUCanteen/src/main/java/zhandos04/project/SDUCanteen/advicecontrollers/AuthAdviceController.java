package zhandos04.project.SDUCanteen.advicecontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zhandos04.project.SDUCanteen.util.IncorrectJSONException;
import zhandos04.project.SDUCanteen.util.UserAlreadyExistsException;

@RestControllerAdvice
public class AuthAdviceController {

    @ExceptionHandler
    public ResponseEntity<String> handleUserNotFound(UsernameNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIncorrectJsonException(IncorrectJSONException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }

    @ExceptionHandler ResponseEntity<String> handleInvalidCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}