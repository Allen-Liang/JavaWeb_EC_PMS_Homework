package edu.xupt.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import edu.xupt.dao.JiajianDao;
import edu.xupt.dao.OrderDao;
import edu.xupt.entity.Order;

@Controller
@RequestMapping("/order")
public class OrderController {

	    @RequestMapping("/add.do")
		public void OrderAdd(String product,String amount,String price,String user,HttpServletResponse response){
	    	//减去库存相应数量
			JiajianDao jiajian =new JiajianDao();
			jiajian.Jian(amount, product);
	    	
	    	//添加订单
	    	OrderDao order = new OrderDao();
			int r =order.insert(product, amount, price, user);
			System.out.println(product);
			
			response.setCharacterEncoding("utf-8");
			Writer writer = null;
			try {
				writer = response.getWriter();
				if(r>0){
					writer.append("OK");
				}else{
					writer.append("NO");
				}
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(writer != null){
					try {
						writer.close();
						writer = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	    
	    
	    @RequestMapping("/getAll.do")
		public void orderAll(HttpServletResponse response){
	    	OrderDao order = new OrderDao();
			List<Order> orders = order.selectAll2();
			System.out.println("get Order info!");
			response.setCharacterEncoding("utf-8");
			Writer writer;
			try {
				writer = response.getWriter();
				String jsonString = JSON.toJSONString(orders);
				System.out.println(jsonString);
				writer.append(JSON.toJSONString(orders));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
	    
	    @RequestMapping("/updateorder.do")
		public void updateorder(String id,String product,String amount,String price,String total,String user,String time,HttpServletResponse response){
	    	OrderDao orderDao = new OrderDao();
	    	
	    	float total_1 = Float.parseFloat(amount) * Float.parseFloat(price);
			String tota_2 = String.valueOf(total_1);
			
			Date date = new Date();
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_1 =sdf.format(date);
			//获取系统时间
	    	Order o = new Order();
	    	o.setId(id);
			o.setProduct(product);
			o.setAmount(amount);
			o.setPrice(price);
			o.setUser(user);
			o.setTotal(tota_2);
			o.setTime(date_1);
			int r = orderDao.updateOder(o);
			
			response.setCharacterEncoding("utf-8");
			Writer writer = null;
			try {
				writer = response.getWriter();
				if(r>0){
					writer.append("OK");
				}else{
					writer.append("NO");
				}
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(writer != null){
					try {
						writer.close();
						writer = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		
		@RequestMapping("/delete2.do")
		public void OrderDelete2(String id,HttpServletResponse response){
			OrderDao order = new OrderDao();
			int r = order.deleteById(id);
			
			response.setCharacterEncoding("utf-8");
			Writer writer = null;
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				writer = response.getWriter();
				if(r>0){
					System.out.println("delete success!");
					map.put("success", true);
				}else{
					map.put("success", false);
					System.out.println("delete fail!");
				}
				writer.append(JSON.toJSONString(map));
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				if(writer != null){
					try {
						writer.close();
						writer = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
		
}
