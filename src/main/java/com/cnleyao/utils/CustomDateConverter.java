package com.cnleyao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * <p>Title:客户端日期转换器<p>
 * <p>Description:自定义日期转换器，将客户端的String类型的日期转换成Date类型去存储<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月16日
 * */
public class CustomDateConverter implements Converter<String, Date>{

	public Date convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
