package com.cnleyao.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>Title:UUID工具类<p>
 * <p>Description:此工具类用于生成UUID 表示一个 128 位的值，用于数据库的ID使用<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年10月16日
 * */
public class CommonUtils {
	public static Random r = new Random(100000);
	private CommonUtils(){};
	/**
	 * 返回一个不重复的字符串
	 * @return
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	} 
	/**
	 * 返回时间字符串的hashcode值
	 * */
	public static int uniqMark(){
		
		return new Date().toString().hashCode();
	}
	/**
	 * 生成long类型的时间加随机数
	 * */
	public static Long ranTime(){
		return System.currentTimeMillis() * 100000 + r.nextInt(100000);

	}
	
	/**
	 * 生成12位的随机数字
	 * */
	
	public static Long calenTime(){
		synchronized (CommonUtils.class) {
			Calendar calendar = Calendar.getInstance();
			Long number = calendar.getTime().getTime();
			String ranStr = Long.toString(number);
			return Long.parseLong(ranStr.substring(1));
		}
	}
}
