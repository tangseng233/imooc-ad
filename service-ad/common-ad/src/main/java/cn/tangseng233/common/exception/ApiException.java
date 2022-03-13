package cn.tangseng233.common.exception;

/**
 * @author tangseng233
 * @Description
 * @Date 2022-03-13 21:45
 **/

public class ApiException extends Exception{

    public ApiException(String message){
        super(message);
    }
}
