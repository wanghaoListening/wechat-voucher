package com.cnleyao.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cnleyao.exception.CustomException;
/**
 * <p>Title:全局异常处理器<p>
 * <p>Description:通过spring内部的调用实现全局的异常处理<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月20日
 * */
public class CusExceResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception ex) {
		
		CustomException customException = null;
		if(ex instanceof CustomException){
			//如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
			customException = (CustomException)ex;
		}else{
			//如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）
			customException = new CustomException("未知错误");
		}
		
		//错误信息
		String message = customException.getMessage();
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		
		//指向错误页面
		modelAndView.setViewName("pages/error/error");

		
		return modelAndView;
	}

}
