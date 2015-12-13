package com.cnleyao.extension;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * <p>Title:<p>
 * <p>Description:重写下MySQL5InnoDBDialect在配置文件中指定该方言，
 * 处理MySQL数据库默认编码的问题，并为hibernate创建表时指定编码方式<p>
 * <p>Extends:<p>
 * 
 * @author wanghao
 * @link 
 * @Date 2015年11月14日
 * */
public class CustomMysqlDialect extends MySQL5InnoDBDialect{

	@Override
	public String getTableTypeString() {
		
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}
	
	
}
