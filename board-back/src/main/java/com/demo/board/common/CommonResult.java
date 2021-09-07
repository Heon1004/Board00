package com.demo.board.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {
	
	private boolean success;
	private int code;
	private String msg;
	
	// 실패 결과만 처리
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        setFailResult(result, code, msg);
        return result;
    }
    
    // API 요청 실패 시 응답 모델을 실패 데이터로 세팅
    private void setFailResult(CommonResult result, int code, String msg) {
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
    }
}
