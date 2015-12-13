package com.cnleyao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * <p>Title: token 验证防止表单重复提交<p>
 * <p>Description:<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年11月1日
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	//创建token
	 boolean save() default false;
	 //验证表单
	 boolean remove() default false;
	 //即创建token又要验证表单
	 boolean all() default false;
	 
}
