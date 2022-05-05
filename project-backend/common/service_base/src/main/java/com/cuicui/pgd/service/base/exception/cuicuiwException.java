package com.cuicui.pgd.service.base.exception;

import com.cuicui.pgd.common.base.result.ResultCodeEnum;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class cuicuiwException extends RuntimeException {

    private Integer code;

    public cuicuiwException(String message,Integer code){
        super(message);
        this.code=code;
    }

    public cuicuiwException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code=resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "cuicuiwException{" +
                "code=" + code +
                ",message"+this.getMessage()+
                '}';
    }
}
