package com.coach.pro.entity;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author wanghui
 * @date 2021/09/05 16:14
 */
public class DemoData {
    //设置excel表头的名称
    @ExcelProperty("学生编号")
    private Integer sno;
    //设置excel表头的名称
    @ExcelProperty("学生姓名")
    private String sname;

    public DemoData(){}
    public DemoData(Integer sno, String sname) {
        this.sno = sno;
        this.sname = sname;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
