package controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entities.*;

import java.util.*;

public class AjaxAction extends ActionSupport {

	private int a;
	private int b;	
	private Map<String, Object> jsonData = new HashMap<String, Object>();

	public Map<String, Object> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, Object> jsonData) {
		this.jsonData = jsonData;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	//getPlayerTurn

	public void hello() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("Hello Ajax");
			out.flush();
		} catch (Exception e) {
		}
	}

	public void sum() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(this.a + this.b);
			out.flush();
		} catch (Exception e) {
		}
	}

	public String find() {
		jsonData.put("sp", new Product("p1", "Name 1", 1000));		
		return SUCCESS;
	}
	
	public String findAll() {
		List<Product> listProduct = new ArrayList<Product>();
		listProduct.add(new Product("p1", "Name 1", 1000));
		listProduct.add(new Product("p2", "Name 2", 2000));
		listProduct.add(new Product("p3", "Name 3", 3000));
		jsonData.put("lp", listProduct);
		return SUCCESS;
	}

}