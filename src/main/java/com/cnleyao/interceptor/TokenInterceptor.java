package com.cnleyao.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.cnleyao.Token;

/**
 * <p>Title: token 验证防止表单重复提交<p>
 * <p>Description:<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年11月1日
 * */
public class TokenInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger.getLogger(TokenInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation =  method.getAnnotation(com.cnleyao.Token.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.save();
				HttpSession session = request.getSession(false);
				if(session == null)
					session = request.getSession(true);
				if (needSaveSession) {
					if(session.getAttribute("token")==null)
					session.setAttribute("token", UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.remove();
				boolean saveRemoveSession = annotation.all();
				//证明此方法是需要被表单重复提交验证的
				if (needRemoveSession||saveRemoveSession) {
					if (isRepeatSubmit(request)) {
						log.warn("please don't repeat submit url:"
								+ request.getServletPath() + "]");
						return false;
					}
					session.removeAttribute("token");
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (serverToken == null) {
			return true;
		}
		String clientToken = request.getParameter("token");
		if (clientToken == null) {
			return true;
		}
		if (!serverToken.equals(clientToken)) {
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if(handler instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation =  method.getAnnotation(com.cnleyao.Token.class);
			if(annotation!=null){
				boolean saveRemoveSession = annotation.all();
				if(saveRemoveSession){
					HttpSession session  = request.getSession(false);
					if(session.getAttribute("token")==null){
						String str = UUID.randomUUID().toString();
						session.setAttribute("token", str);
					}
				}
			}
		}
			super.postHandle(request, response, handler, modelAndView);
		
	}



}
