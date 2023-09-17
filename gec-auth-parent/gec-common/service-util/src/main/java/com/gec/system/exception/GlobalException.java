package com.gec.system.exception;


import com.gec.system.util.Result;
import com.gec.system.util.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
public class GlobalException {

//    抛出异常的对像，会在异常中生成指定类型的异常对象。寻找符合类型的操作处理

    //1.全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e)
    {
        System.out.println("全局执行....");;
        e.printStackTrace();//打印异常的堆栈信息
        return Result.fail().message("执行了全局异常处理...");
    }


    //2.特定异常处理---->exception的子类
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
//    算数运算异常处理
    public Result error2(ArithmeticException e)
    {
        System.out.println("特定执行.");
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理..");
    }


    //3.执行自定义异常
    @ExceptionHandler(MyCustomerException.class)
    @ResponseBody
    public Result  error3(MyCustomerException e)
    {
        e.printStackTrace();
//        封装code和message返回
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前操作权限");
    }
}