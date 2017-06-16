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
import edu.xupt.dao.InventoryDao;
import edu.xupt.entity.Inventory;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	    @RequestMapping("/add.do")
		public void inventoryAdd(String id,String product,String sum,String user,HttpServletResponse response){
	    	InventoryDao inventory = new InventoryDao();
			int r =inventory.insert(id, product, sum, user);
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
		public void getInventoryAll(HttpServletResponse response){
	    	InventoryDao inventory = new InventoryDao();
			List<Inventory> inventorys = inventory.selectAll2();
			System.out.println("get Inventory info!");
			response.setCharacterEncoding("utf-8");
			Writer writer;
			try {
				writer = response.getWriter();
				String jsonString = JSON.toJSONString(inventorys);
				System.out.println(jsonString);
				writer.append(JSON.toJSONString(inventorys));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
		
	    
	    @RequestMapping("/updateinventory.do")
		public void updateinventory(String id,String product,String sum,String user,HttpServletResponse response){
	    	InventoryDao inventoryDao = new InventoryDao();
	    	Inventory i = new Inventory();
			i.setId(id);
			i.setProduct(product);
			i.setSum(sum);
			i.setUser(user);
			int r = inventoryDao.updateInventory(i);
			
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
		public void InventoryDelete2(String id,HttpServletResponse response){
			InventoryDao inventory = new InventoryDao();
			int r = inventory.deleteById(id);
			
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
