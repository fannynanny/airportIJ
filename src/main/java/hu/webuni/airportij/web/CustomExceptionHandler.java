package hu.webuni.airportij.web;

import hu.webuni.airportij.service.NonUniqueIataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice  // emiatt közös lesz minden controllerben
public class CustomExceptionHandler {

    //logoláshoz:
    private static final Logger log= LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(NonUniqueIataException.class)
    public ResponseEntity<MyError> handleNonUniqueIata(NonUniqueIataException e, WebRequest req){
        log.warn(e.getMessage(),e ); // stack trace? e
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MyError(e.getMessage(),1002));
    }
    // Exception típusonként mást-mást írhatnánk még ide ^

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyError> handleValidationError(MethodArgumentNotValidException e, WebRequest req){
        log.warn(e.getMessage(),e ); // stack trace? e

        MyError myError=new MyError(e.getMessage(),1002);
        myError.setFieldErrors(e.getBindingResult().getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(myError);
    }
}
