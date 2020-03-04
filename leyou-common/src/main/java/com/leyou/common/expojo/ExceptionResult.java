package com.leyou.common.expojo;

import com.leyou.common.enums.ExceptionEnums;
import lombok.Data;

@Data
public class ExceptionResult {
    private int status;
    private String msg;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums em) {
        this.status = em.getStatus();
        this.msg = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}
