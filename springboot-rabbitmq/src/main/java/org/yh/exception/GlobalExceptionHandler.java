package org.yh.exception;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yh.result.Result;
import org.yh.result.ResultUtils;

@ControllerAdvice(basePackages = "org.yh.controller")
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Result runtimeResult(HttpServletRequest req, Exception e) throws JSONException {
		return ResultUtils.fail(e.getMessage());
	}

	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public Result errorResult(HttpServletRequest req, Exception e) throws JSONException {

		return ResultUtils.fail(e.getMessage());
	}
}
