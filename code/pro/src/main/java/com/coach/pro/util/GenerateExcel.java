package com.coach.pro.util;

import com.coach.pro.entity.RetailShop;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * @author wanghui
 * @date 2021/08/30 18:42
 */
public class GenerateExcel {

    public static void main(String[] as) throws IOException {
//        GenerateExcel g=new GenerateExcel();
//        g.listToExcel();
    }

    //生成excel
    public static net.sf.json.JSONObject toExcel(String src, net.sf.json.JSONObject json) {
        //用来反馈函数调用结果
        net.sf.json.JSONObject result = new net.sf.json.JSONObject();
        try {
            //新建文件
            File file = new File(src);
            file.createNewFile();
            //创建工作簿
            OutputStream outputStream = new FileOutputStream(file);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);
            //创建新一页
            WritableSheet sheet = writableWorkbook.createSheet("店铺", 0);
            JSONArray jsonArray = json.getJSONArray("data");
            //单元格对象
            Label label;
            //列计数
            int column = 0;
            //将表头加到sheet中
            net.sf.json.JSONObject first = jsonArray.getJSONObject(0);
            //得到第一项的key集合
            Iterator<String> iterator = first.keys();
            //遍历key集合
            while (iterator.hasNext()) {
                //得到key
                String key = (String) iterator.next();
                //第一个参数是单元格所在列，第二个参数是单元格所在行，第三个是值
                label = new Label(column++, 0, key);
                sheet.addCell(label);
            }
            //遍历jsonArray
            for (int i = 0; i < jsonArray.size(); i++) {
                //得到每项的数组
                net.sf.json.JSONObject item = jsonArray.getJSONObject(i);
                //得到key集合
                iterator = item.keys();
                //从第0列开始放
                column = 0;
                while (iterator.hasNext()) {
                    //获得key
                    String key = iterator.next();
                    //获得key对应的value
                    String value = item.getString(key);
                    //第一个参数是单元格所在列，第二个参数是单元格所在行，第三个是值
                    label = new Label(column++, (i + 1), value);
                    //将单元格加到页
                    sheet.addCell(label);
                }
            }
            //加入到文件中
            writableWorkbook.write();
            //关闭文件释放资源
            writableWorkbook.close();
        } catch (Exception e) {
            //将调用该函数的结果返回
            result.put("result", "failed");
            //将调用该函数失败的原因返回
            result.put("reason", e.getMessage());
            return result;
        }
        result.put("result", "successes");
        return result;
    }

    public void retailShopToExcel(List<RetailShop> list) throws IOException {
        String url = "E:\\yhltd129\\工作\\huanghua_Coach_ribao_java\\code\\pro\\src\\main\\resources\\static\\excel\\retail_测试.xlsx";
        System.out.println(url);
//        File file = new File("files/report_Demo.xlsx");
//        XSSFWorkbook workbook = null;
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            workbook = new XSSFWorkbook(fileInputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //查找到指定的sheet
//        XSSFSheet sheet = workbook.getSheet("店铺");
//        sheet.setForceFormulaRecalculation(true);
//        Map<String, String> itemInfo = new HashMap<>();
//
//        int rowNum=4;
//        for (RetailShop retailShop : list) {
//            if (retailShop.getTmPrice() != null) {
//
//            } else if (retailShop.getJDPrice() != null) {
//
//            } else if (retailShop.getCoachPrice() != null) {
//
//            }
//
//        }
//        XSSFRow row = sheet.getRow(rowNum);
//        rowNum=rowNum+1;

        try {
            //创建工作簿
            FileInputStream fis = new FileInputStream(url);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = xssfWorkbook.getSheet("店铺");
            XSSFRow row = sheet.getRow(0);
            int hang = 0;

            //分别得到最后一行的行号，和一条记录的最后一个单元格
            FileOutputStream out = new FileOutputStream(url); //向excel中写数据
            for (int i = 0; i < list.size(); i++) {
                if ("".equals(row) || row == null) {
                    hang = 0;
                } else {
                    //hang = sheet.getLastRowNum();
                    hang=row.getLastCellNum();
                    hang = hang + 1;
                }
                row = sheet.createRow((short) (hang)); //在现有行号后追加数据
                if (list.get(i).getTmPrice() != null) {
                    //设置单元格的数据（列从0开始）
                    row.createCell(2).setCellValue(list.get(i).getGuanFang_price());
                    row.createCell(3).setCellValue(list.get(i).getGuanFangCuXiao_price());
                    row.createCell(4).setCellValue(list.get(i).getTmPrice());
                    row.createCell(5).setCellValue(list.get(i).getTmPriceDifference());
                    row.createCell(6).setCellValue(list.get(i).getTmDiscount());
                } else if (list.get(i).getJDPrice() != null) {
                    row.createCell(2).setCellValue(list.get(i).getGuanFang_price());
                    row.createCell(3).setCellValue(list.get(i).getGuanFangCuXiao_price());
                    row.createCell(13).setCellValue(list.get(i).getJDPrice());
                    row.createCell(14).setCellValue(list.get(i).getJDPriceDifference());
                    row.createCell(15).setCellValue(list.get(i).getJDPriceDiscount());
                } else if (list.get(i).getCoachPrice() != null) {
                    row.createCell(2).setCellValue(list.get(i).getGuanFang_price());
                    row.createCell(3).setCellValue(list.get(i).getGuanFangCuXiao_price());
                    row.createCell(16).setCellValue(list.get(i).getCoachPrice());
                    row.createCell(17).setCellValue(list.get(i).getCoachPriceDifference());
                    row.createCell(18).setCellValue(list.get(i).getCoachPriceDiscount());
                }
            }
            out.flush();
            xssfWorkbook.write(out);
            out.close();
            System.out.println(row.getPhysicalNumberOfCells() + " " + row.getLastCellNum());
            System.out.println("成功！");


//            fs = new FileInputStream(url);
//            POIFSFileSystem ps=new POIFSFileSystem(fs); //使用POI提供的方法得到excel的信息
//            HSSFWorkbook wb=new HSSFWorkbook(ps);
//            HSSFSheet sheet=wb.getSheetAt(0); //获取到工作表，因为一个excel可能有多个工作表
//            HSSFRow row=sheet.getRow(0);
//            int hang=0;
//            if("".equals(row)||row==null){
//                hang=0;
//            }else{
//                hang=sheet.getLastRowNum();
//                hang=hang+1;
//            }
//            //分别得到最后一行的行号，和一条记录的最后一个单元格
//            FileOutputStream out=new FileOutputStream(url); //向excel中写数据
//            for(int i=0;i<list.size();i++){
//                row=sheet.createRow((short)(hang)); //在现有行号后追加数据
//                if (list.get(i).getTmPrice()!=null){
//                    //设置单元格的数据（列从0开始）
//                    row.createCell(2).setCellValue(list.get(i).getGuanFang_price());
//                    row.createCell(3).setCellValue(list.get(i).getGuanFangCuXiao_price());
//                    row.createCell(4).setCellValue(list.get(i).getTmPrice());
//                    row.createCell(5).setCellValue(list.get(i).getTmPriceDifference());
//                    row.createCell(6).setCellValue(list.get(i).getTmDiscount());
//                }else if (list.get(i).getJDPrice()!=null){
//                    row.createCell(2).setCellValue(list.get(i).getGuanFang_price());
//                    row.createCell(3).setCellValue(list.get(i).getGuanFangCuXiao_price());
//                    row.createCell(13).setCellValue(list.get(i).getJDPrice());
//                    row.createCell(14).setCellValue(list.get(i).getJDPriceDifference());
//                    row.createCell(15).setCellValue(list.get(i).getJDPriceDiscount());
//                }else if (list.get(i).getCoachPrice()!=null){
//                    row.createCell(2).setCellValue(list.get(i).getGuanFang_price());
//                    row.createCell(3).setCellValue(list.get(i).getGuanFangCuXiao_price());
//                    row.createCell(16).setCellValue(list.get(i).getCoachPrice());
//                    row.createCell(17).setCellValue(list.get(i).getCoachPriceDifference());
//                    row.createCell(18).setCellValue(list.get(i).getCoachPriceDiscount());
//                }
//            }
//            out.flush();
//            wb.write(out);
//            out.close();
//            System.out.println(row.getPhysicalNumberOfCells()+" "+row.getLastCellNum());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
