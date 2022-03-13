package cn.tangseng233.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tangseng233
 * @Description 统一返回
 * @Date 2022-03-13 21:07
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResultVO <T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
