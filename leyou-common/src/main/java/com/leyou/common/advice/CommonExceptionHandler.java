package com.leyou.common.advice;


import com.leyou.common.exception.LeyouException;
import com.leyou.common.expojo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//默认通知所有Controller
public class CommonExceptionHandler {

    @ExceptionHandler(LeyouException.class)
    public ResponseEntity<ExceptionResult> handleException(LeyouException e){


        //400新增失败
        return ResponseEntity.status(e.getExceptionEnums().getStatus())
                .body(new ExceptionResult(e.getExceptionEnums()));
    }
}
