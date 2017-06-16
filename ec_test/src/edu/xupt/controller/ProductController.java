package edu.xupt.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import edu.xupt.dao.ProductDao;
import edu.xupt.entity.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

	    @RequestMapping("/add.do")
		public void productAdd(String id,String name,String type,String discription,HttpServletResponse response){
			ProductDao product = new ProductDao();
			int r =product.insert(name, type, discription);
			System.out.println(name);
			
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
		public void getUserAll(HttpServletResponse response){
			ProductDao product = new ProductDao();
			List<Product> products = product.selectAll2();
			System.out.println("get product info!");
			response.setCharacterEncoding("utf-8");
			Writer writer;
			try {
				writer = response.getWriter();
				String jsonString = JSON.toJSONString(products);
				System.out.println(jsonString);
				writer.append(JSON.toJSONString(products));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
	    
	    @RequestMapping("/updateproduct.do")
		public void updateProduct(String id, String name,String type,String discription,HttpServletResponse response){
			ProductDao productDao = new ProductDao();
			Product p = new Product();
			p.setId(id);
			p.setName(name);
			p.setType(type);
			p.setDiscription(discription);
			int r = productDao.updateProduct(p);
			
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
		public void productDelete2(String id,HttpServletResponse response){
			ProductDao product = new ProductDao();
			int r = product.deleteById(id);
			
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
