package protest;


import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cnleyao.dto.Shop;
import com.cnleyao.utils.ApacheHttpClient;
import com.cnleyao.utils.HttpResponse;

public class TestClient {
	@org.junit.Test
	public void test(){
		//[{"Id":144622264597054044,"Name":"小掌柜火锅","Contact":"李红","Tel":"13488888888","Area":1,"Address":"","Category":1,"CreateTime":"Oct 31, 2015 12:30:45 AM"},{"Id":144622269107968586,"Name":"星美影院","Contact":"张华","Tel":"13477777777","Area":1,"Address":"","Category":1,"CreateTime":"Oct 31, 2015 12:31:31 AM"}]
		String str2 = "[{'Id':'144622264597054044','Name':'小掌柜火锅','Contact':'李红','Tel':'13488888888','Area':'1','Address':'','Category':'1','CreateTime':'Oct 31, 2015 12:30:45 AM'},{'Id':'144622269107968586','Name':'星美影院','Contact':'张华','Tel':'13477777777','Area':'1','Address':'','Category':'1','CreateTime':'Oct 31, 2015 12:31:31 AM'}]";
		List<Shop> shops = JSON.parseArray(str2, Shop.class);
		for (Shop shop : shops){
			System.out.println(shop.getName()+"---"+shop.getId());
		}

	}
	/**
	 * [{"Id":144622272179535148,"ShopId":144622269107968586,"Name":"华泰店","Tel":"12345678","Address":"华泰路","CreateTime":"Oct 31, 2015 12:32:01 AM"}
	 * ,{"Id":144622274433494088,"ShopId":144622269107968586,"Name":"山河店","Tel":"123","Address":"山河路","CreateTime":"Oct 31, 2015 12:32:24 AM"}]-----
	 * */
	@Test
	public void test2(){
		//144622269107968586
		ApacheHttpClient httpClient  = new ApacheHttpClient(); 
		HttpResponse response = httpClient.get("http://114.215.138.165/api/144622264597054050/stores");
		String str = response.getResponse();
		System.out.println("你好"+str+"---------------");
		/*if("".equals(str)&&null==str)*/
			
	}
	
	@org.junit.Test
	public void test3(){
		//"Id":144622264597054044,"Name":"小掌柜火锅","Contact":"李红","Tel":"13488888888","Area":1,"Address":"","Category":1,"CreateTime":"Oct 31, 2015 12:30:45 AM"}
		ApacheHttpClient httpClient  = new ApacheHttpClient(); 
		HttpResponse response = httpClient.get("http://114.215.138.165/api/144622269107968586/stores");
		String str = response.getResponse();
		System.out.println(str);
	
	
		System.out.println("1111");
	}
	
	@Test
	public void test4(){
		//http://114.215.138.165/api/shops
		ApacheHttpClient httpClient  = new ApacheHttpClient(); 
		HttpResponse response = httpClient.get("http://114.215.138.165/api/shops");
		String str = response.getResponse();
		System.out.println(str);
	}
	@Test
	public void test5(){
		Integer in ;
		
	}


}
