package com.example.community.dto;

import com.example.community.exception.CustomErrorCode;
import lombok.Data;

@Data
public class ResultDTO {
    private String message;
    private Integer code;

  public static ResultDTO Errorof(String message, Integer code) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage(message);
        resultDTO.setCode(code);
        return resultDTO;
    }

    public static ResultDTO Errorof(CustomErrorCode errorCode) {
      return  Errorof(errorCode.getMessage(), errorCode.getcode());
    }

    public static ResultDTO ofok() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMessage("The comment is success");
        resultDTO.setCode(200);
        return resultDTO;
    }

    ;
}

