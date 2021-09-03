package com.coach.pro;

import com.alibaba.excel.EasyExcel;
import com.coach.pro.entity.CoachItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ProApplicationTests {

	@Test
	void contextLoads() {
//		String templateFileName = "E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel"+ "测试.xlsx";
//		// 方案1 根据对象填充
//		String fileName = "E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel\\retail_店铺&全网日报日期" + ".xlsx";
//		// 这里 会填充到第一个sheet， 然后文件流会自动关闭
//		CoachItem fillData = new CoachItem();
//		fillData.setActivityPrice("张三");
//		fillData.setAssetID("123213213213213213");
//		EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);
	}
}
