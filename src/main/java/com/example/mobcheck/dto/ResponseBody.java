package com.example.mobcheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody {
    private int status;
    private String message;
    private Object data;

}

