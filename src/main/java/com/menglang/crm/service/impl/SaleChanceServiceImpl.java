package com.menglang.crm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.mapper.SaleChanceMapper;
import com.menglang.crm.pojo.SaleChance;
import com.menglang.crm.pojo.SaleChanceExample;
import com.menglang.crm.pojo.SaleChanceExample.Criteria;
import com.menglang.crm.service.ISaleChanceService;
import com.menglang.crm.util.LikeNameUtil;
@Service
public class SaleChanceServiceImpl implements ISaleChanceService{

	@Autowired
	private SaleChanceMapper saleChanceMapper;

	@Override
	public EasyuiDataGridResult findAll(Integer page,Integer rows,SaleChance saleChance,
			String startDate,String endDate) {
		//1、设置分页  
		PageHelper.startPage(page, rows);
		Date startTime = null;
		Date endTime = null;
		String start = null;
		String end = null;
		if(StringUtils.isNotBlank(startDate) & StringUtils.isNotBlank(endDate)){
			start = startDate.replace("/", "-");
			end = endDate.replace("/", "-");
			System.out.println(start);
			try {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				startTime = simpleDateFormat.parse(start);
				endTime = simpleDateFormat.parse(end);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		SaleChanceExample example = new SaleChanceExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotBlank(saleChance.getCustomerName())){
			criteria.andCustomerNameLike(LikeNameUtil.formartLike(saleChance.getCustomerName()));
		}
		if(StringUtils.isNotBlank(saleChance.getOverview())){
			criteria.andOverviewLike(LikeNameUtil.formartLike(saleChance.getOverview()));
		}
		if(StringUtils.isNotBlank(saleChance.getCreateMan())){
			criteria.andCreateManLike(LikeNameUtil.formartLike(saleChance.getCreateMan()));
		}
		if(startTime != null && !startTime.equals("") && endTime != null && !endTime.equals("")){
			criteria.andCreateTimeBetween(start, end);
		}
		if(StringUtils.isNotBlank(saleChance.getCreateMan())){
			criteria.andCreateManLike(LikeNameUtil.formartLike(saleChance.getCreateMan()));
		}
		if(saleChance.getStatus()!=null){
			criteria.andStatusEqualTo(saleChance.getStatus());
		}
		//2、执行查询  
		List<SaleChance> list = saleChanceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<SaleChance> pageInfo = new PageInfo<SaleChance>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse addSaleChance(SaleChance saleChance) {
		SaleChanceExample example = new SaleChanceExample();
		if(StringUtils.isNotBlank(saleChance.getAssignMan())){
			saleChance.setStatus(1);
		}else {
			saleChance.setStatus(0);
		}
		saleChance.setDevResult(0);
		if(saleChanceMapper.insert(saleChance)>0){
			return SeverResponse.createSuccess("添加成功");
		}
		return SeverResponse.createError("添加成功");
	}

	@Override
	public SeverResponse delete(String ids) {
		String[] idArray = ids.split(",");
		int result = 0;
		for (String id : idArray) {
			result = saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		if(result>0){
			return SeverResponse.createSuccess("删除数据成功");
		}
		return SeverResponse.createError("删除数据失败");
	}

	@Override
	public SaleChance findById(Integer id) {
		return saleChanceMapper.selectByPrimaryKey(id);
	}

	@Override
	public SeverResponse update(SaleChance saleChance) {
		if(saleChanceMapper.updateByPrimaryKey(saleChance)>0){
			return SeverResponse.createSuccess("修改数据成功");
		}
		return SeverResponse.createError("修改数据失败");
	}

	@Override
	public List<SaleChance> findAssignMan() {
		return saleChanceMapper.findAssignMan();
	}

	@Override
	public EasyuiDataGridResult findStatusIsOne(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		EasyuiDataGridResult result = new EasyuiDataGridResult();
		SaleChanceExample example = new SaleChanceExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		//2、执行查询  
		List<SaleChance> list = saleChanceMapper.selectByExample(example);
		//3、取分页后结果  
		PageInfo<SaleChance> pageInfo = new PageInfo<SaleChance>(list);  
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public SeverResponse stopDevelopment(Integer id) {
		SaleChance saleChance = new SaleChance();
		saleChance.setId(id);
		saleChance.setDevResult(3);
		if(saleChanceMapper.updateByPrimaryKey(saleChance)>0){
			return SeverResponse.createSuccess("终止成功");
		}
		return SeverResponse.createError("终止失败");
	}

	@Override
	public void exportExcel(ServletOutputStream outputStream) {
		List<SaleChance> list = saleChanceMapper.selectByExample(new SaleChanceExample());
		try {
			// 1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 7);// 起始行号，结束行号，起始列号，结束列号

			// 1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short) 16);

			// 1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short) 13);
			style2.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));  

			// 2、创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			// 2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);

			// 3、创建行
			// 3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			// 加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");

			// 3.2、创建列标题行；并且设置列标题
			HSSFRow rowHead = sheet.createRow(1);
			String[] titles = { "编号", "客户名称", "概要", "联系人", "练习电话","创建人","创建时间","状态" };
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = rowHead.createCell(i);
				// 加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			
			// 4、操作单元格；将用户列表写入excel
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					HSSFRow row = sheet.createRow(j + 2);
					HSSFCell cellId = row.createCell(0);
					cellId.setCellValue(list.get(j).getId());
					HSSFCell cellName = row.createCell(1);
					cellName.setCellValue(list.get(j).getCustomerName());
					HSSFCell cellOverview = row.createCell(2);
					cellOverview.setCellValue(list.get(j).getOverview());
					HSSFCell cell14 = row.createCell(3);
					cell14.setCellValue(list.get(j).getLinkMan());
					HSSFCell cellLinkPhone = row.createCell(4);
					cellLinkPhone.setCellValue(list.get(j).getLinkPhone());
					HSSFCell cellCreateMan = row.createCell(5);
					cellCreateMan.setCellValue(list.get(j).getCreateMan());
					HSSFCell cellCreateTime = row.createCell(6);
					if (list.get(j).getCreateTime() != null) {
						cellCreateTime.setCellValue(list.get(j).getCreateTime());
					} else {
						cellCreateTime.setCellValue("");
					}
					HSSFCell cellStatus = row.createCell(7);
					if (list.get(j).getStatus() != null && list.get(j).getStatus() == 1) {
						cellStatus.setCellValue("已分配");
					} else {
						cellStatus.setCellValue("未分配");
					}
				}
			}
			// 5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 * 创建单元格样式
	 * 
	 * @param workbook
	 *            工作簿
	 * @param fontSize
	 *            字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗字体
		font.setFontHeightInPoints(fontSize);
		// 加载字体
		style.setFont(font);
		return style;
	}
}
