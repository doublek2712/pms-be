package com.douk.PMS.dto;

import java.time.YearMonth;

public class RequestDTO {
    private Long bodId;
    private String message;
    private YearMonth month;

    public RequestDTO(Long bodId, String message, YearMonth month) {
        this.bodId = bodId;
        this.message = message;
        this.month = month;
    }

    public Long getBodId() {
        return bodId;
    }

    public void setBodId(Long bodId) {
        this.bodId = bodId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }
}
