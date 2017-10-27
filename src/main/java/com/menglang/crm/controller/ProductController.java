package com.menglang.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.Product;
import com.menglang.crm.service.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@RequestMapping("/index")
	public String productIndex() {
		return "product_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, Product product) {
		return productService.findAll(page, rows, product);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(Product product) {
		return productService.addProduct(product);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return productService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(Product product) {
		return productService.update(product);
	}
}
