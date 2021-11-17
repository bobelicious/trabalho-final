package br.com.serratec.trabalhofinal.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiHandlerException {
    // criar aqui
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<?> handlerEmailException(EmailException ex) {
        ErroResposta error = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
                ex.getClass().getName(), new Date().getTime());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EnumException.class)
    public ResponseEntity<?> handlerEnumException(EnumException ex) {
        ErroResposta error = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
                ex.getClass().getName(), new Date().getTime());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PedidoException.class)
    public ResponseEntity<?> handlerPedidoException(PedidoException ex) {
        ErroResposta error = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
                ex.getClass().getName(), new Date().getTime());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoException.class)
    public ResponseEntity<?> ProdutoException(ProdutoException ex) {
        ErroResposta error = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
                ex.getClass().getName(), new Date().getTime());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemPedidoException.class)
    public ResponseEntity<?> handlerItemPedidoException(ItemPedidoException ex) {
        ErroResposta error = new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(),
                ex.getClass().getName(), new Date().getTime());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
