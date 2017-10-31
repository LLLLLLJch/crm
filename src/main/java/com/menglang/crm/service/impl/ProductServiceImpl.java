package com.menglang.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.ProductMapper;
import com.menglang.crm.pojo.Product;
import com.menglang.crm.pojo.ProductExample;
import com.menglang.crm.pojo.ProductExample.Criteria;
import com.menglang.crm.service.IProductService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductMapper ProductMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,Product Product) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(Product.getName())){
			criteria.andNameLike(LikeNameUtil.formartLike(Product.getName()));
		}
		if(StringUtils.isNotBlank(Product.getModel())){
			criteria.andModelLike(LikeNameUtil.formartLike(Product.getModel()));
		}
		if(Product.getPrice() != null){
			criteria.andPriceEqualTo(Product.getPrice());
		}
		if(Product.getStock() != null){
			criteria.andStockEqualTo(Product.getStock());
		}
		if(StringUtils.isNotBlank(Product.getRemark())){
			criteria.andRemarkLike(LikeNameUtil.formartLike(Product.getRemark()));
		}
		//2、执行查询  
		List<Product> list = ProductMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<Product> pageInfo = new PageInfo<Product>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addProduct(Product Product) {
		ProductExample example = new ProductExample();
		if(ProductMapper.insert(Product)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = ProductMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public Product findById(Integer id) {
		return ProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(Product Product) {
		if(ProductMapper.updateByPrimaryKey(Product)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}
}
