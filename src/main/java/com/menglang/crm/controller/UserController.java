package com.menglang.crm.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglang.crm.common.EasyuiDataGridResult;
import com.menglang.crm.common.SeverResponse;
import com.menglang.crm.pojo.User;
import com.menglang.crm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/index")
	public String userIndex() {
		return "user_index";
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public EasyuiDataGridResult findAll(Integer page, Integer rows, User user) {
		return userService.findAll(page, rows, user);
	}

	@RequestMapping("/add")
	@ResponseBody
	public SeverResponse add(User user) {
		return userService.addUser(user);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public SeverResponse delete(String ids) {
		return userService.delete(ids);
	}

	@RequestMapping("/update")
	@ResponseBody
	public SeverResponse update(User user) {
		return userService.update(user);
	}
	@RequestMapping("/checkName")
	@ResponseBody
	public SeverResponse checkName(String name) {
		return userService.checkName(name);
	}
	@RequestMapping("/checkPasswordByName")
	@ResponseBody
	public SeverResponse checkPasswordByName(String name,String password) {
		return userService.checkPasswordByName(name,password);
	}
	@RequestMapping("/surePassword")
	@ResponseBody
	public SeverResponse surePassword(String newPassword,String surePassword) {
		return userService.surePassword(newPassword,surePassword);
	}
	@RequestMapping("/updatePassword")
	@ResponseBody
	public SeverResponse updatePassword(User user,String newPassword) {
		return userService.updatePassword(user,newPassword);
	}
	@RequestMapping("/findManger")
	@ResponseBody
	public List<User> findManger() {
		return userService.findManger();
	}
	@RequestMapping("/inputExcel")
	@ResponseBody
	public SeverResponse inputExcel() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("E:\\poitest\\测试1.xls");
			//读取工作薄
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			//读取工作表
			HSSFSheet sheet = workbook.getSheetAt(0);
			//读取行
			HSSFRow row = sheet.getRow(0);
			//读取单元格
			User user = new User();
			//读取单元格
			for (int i = 0; i < 6; i++) {
				HSSFCell cell = row.getCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
				String value = cell.getStringCellValue();
				if(i == 0){
					user.setName(value);
				}else if (i == 1) {
					user.setPassword(value);
				}else if (i == 2) {
					user.setTrueName(value);
				}else if (i == 3) {
					user.setPhone(value);
				}else if (i == 4) {
					user.setEmail(value);
				}else {
					user.setRoleName(value);
				}
			}
			workbook.close();
			inputStream.close();
			return userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
