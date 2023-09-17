package com.gec.system.exception;


import com.gec.system.util.Result;
import com.gec.system.util.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.AccessDeniedException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCustomerException extends RuntimeException{
    private Integer code;
    private String msg;



}
