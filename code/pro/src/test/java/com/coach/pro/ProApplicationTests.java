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
		String a="file:1234567";
		System.out.println(a.substring(5));
	}
}
