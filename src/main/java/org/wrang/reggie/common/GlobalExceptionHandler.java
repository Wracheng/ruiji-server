package org.wrang.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Slf4j
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    private R<String> exceptionSQL(SQLIntegrityConstraintViolationException ex){
        // 获取报错信息
        log.info(ex.getMessage());
        // 如果报错信息中包含"Duplicate entry"关键字
        if (ex.getMessage().contains("Duplicate entry")){
            String[] s = ex.getMessage().split(" ");
            String filed = s[2];
            return R.error(filed + "重复了");
        }
        if (ex.getMessage().contains("Column") && ex.getMessage().contains("cannot be null")){
            String[] s = ex.getMessage().split(" ");
            String filed = s[1];
            return R.error(filed + "不能为空");
        }
        return R.error("未知错误");
    }
}
