package crm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class POITest {

	  @Test
	    public void testWrite03ExcelStyle() throws Exception {
	       //1、创建工作薄。
	       HSSFWorkbook workbook = new HSSFWorkbook();
	       //1.1 创建合并单元格对象，
	       CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 6, 1, 11);
	       HSSFCellStyle style = createStyle(workbook, (short)16);
	       
	       //2、创建工作表。
	       HSSFSheet sheet = workbook.createSheet("Hello POI");
	       // 设置默认列宽
	       sheet.setDefaultColumnWidth(25);
	       //2.1 加载合并单元格对象
	        sheet.addMergedRegion(cellRangeAddress);
	       //3、创建行
	       HSSFRow row = sheet.createRow(2);//第三行
	       //4、创建单元格
	       HSSFCell cell = row.createCell(1);
	       //给单元格加载样式
	       cell.setCellStyle(style);
	       cell.setCellValue("用户列表");
	       
	       HSSFRow rowHead = sheet.createRow(7);//第三行
	       //4、创建单元格
	       String[] headArray = {"编号", "客户名称", "概要", "联系人"};
	       for (int i = 0; i < headArray.length; i++) {
	           HSSFCell cellHead = rowHead.createCell(i + 1);
	           HSSFCellStyle styleHead = createStyle(workbook, (short)13);
	           cellHead.setCellStyle(styleHead);
	           cellHead.setCellValue(headArray[i]);
	       }
	       
	       FileOutputStream outputStream = new FileOutputStream("E:\\poitest\\测试29.xls");
	       //输出excel到文件
	       workbook.write(outputStream);
	       workbook.close();
	       outputStream.close();
	    }
	    private HSSFCellStyle createStyle(HSSFWorkbook workbook, short fontSize) {
	       //1.2 创建单元格样式
	       HSSFCellStyle style = workbook.createCellStyle();
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//horizontal 水平居中
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
	       // 创建字体
	       HSSFFont font = workbook.createFont();
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
	        font.setFontHeightInPoints(fontSize);//设置字体大小
	       //加载字体到样式
	       style.setFont(font);
	       //单元格背景
	       //设置背景填充模式
	        //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	       //设置填充背景色
	        //style.setFillBackgroundColor(HSSFColor.YELLOW.index);
	       //设置填充前景色
	        //style.setFillForegroundColor(HSSFColor.RED.index);
	       return style;
	    }
	    @Test
	    public void testReadExcel() throws Exception {
			FileInputStream inputStream = new FileInputStream("E:\\poitest\\测试1.xls");
			//读取工作薄
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			//读取工作表
			HSSFSheet sheet = workbook.getSheetAt(0);
			//读取行
			HSSFRow row = sheet.getRow(0);
			//读取单元格
			for (int i = 0; i < 6; i++) {
				HSSFCell cell = row.getCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
				String value = cell.getStringCellValue();
				System.out.println(value);
			}
			workbook.close();
			inputStream.close();
			
		}
	}

