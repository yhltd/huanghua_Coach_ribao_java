package com.coach.pro.util;

import com.coach.pro.entity.*;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


/**
 * @author wanghui
 * @date 2021/08/30 18:42
 */
public class GenerateExcel {
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
                    hang = row.getLastCellNum();
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

    /**
     * 寫入Excel模板 （會保留原本的樣式）
     *
     * @param
     */
    public static void retail_Shop_QuanWang_excel(String filename, List<CoachItem> retailQuanWang, List<RetailShop> retailShop) throws IOException {
//        String pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath();
//        String pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath();
//        if(pathExcel.substring(0,4).equals("file")){
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(5);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(5);
//        }else{
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(1);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(1);
//        }
        String pathExcel = "C:\\coach\\excel\\";
        String pathImg = "C:\\coach\\img\\retailSKUimage\\";
        File file = new File(pathExcel + "retail_店铺&全网日报模板.xlsx");
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            workbook = WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查找到指定的sheet
        Sheet sheet = workbook.getSheet("全网");
        sheet.setForceFormulaRecalculation(true);
        //图片相关
        BufferedImage bufferImg = null;
        XSSFDrawing patriarch = null;


        for (int i = 0; i < retailQuanWang.size(); i++) {
            System.out.println("全网" + i);
            //将图片读到BufferedImage
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            if (i != 0 && retailQuanWang.get(i).getUserSKUCode().equals(retailQuanWang.get(i - 1).getUserSKUCode())) {

            } else {
                bufferImg = null;
                File img = new File(pathImg + retailQuanWang.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
                if (img.exists()) {
                    bufferImg = ImageIO.read(img);
                    // 将图片写入流中
                    ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                    patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 5, i + 2, 5, i + 2);
                    int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                    XSSFPicture picture = patriarch.createPicture(anchor, inx);
                    picture.resize(0.99, 0.90);
                    byteArrayOut.reset();
                }
            }
            if (i == 0) {
                bufferImg = null;
                File img = new File(pathImg + retailQuanWang.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
                if (img.exists()) {
                    bufferImg = ImageIO.read(img);
                    // 将图片写入流中
                    ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                    patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 5, i + 2, 5, i + 2);
                    int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                    XSSFPicture picture = patriarch.createPicture(anchor, inx);
                    picture.resize(0.99, 0.90);
                    byteArrayOut.reset();
                }
            }

            Row row = sheet.getRow(i + 2);
            if (row == null) {
                row = sheet.createRow(i + 2);
            }
            //观察时间
            Cell existingCell = row.getCell(0);
            if (existingCell == null) {
                existingCell = row.createCell(0);
            }
            CellStyle currentStyle = existingCell.getCellStyle();
            row.createCell(0);
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);

            if (retailQuanWang.get(i).getViewTime() != null && !retailQuanWang.get(i).getViewTime().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getViewTime().split("T")[0]);
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }
            //电商平台
            existingCell = row.getCell(1);
            if (existingCell == null) {
                existingCell = row.createCell(1);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(1);
            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getChannel() != null && !retailQuanWang.get(i).getChannel().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getChannel());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //店铺名称
            existingCell = row.getCell(2);
            if (existingCell == null) {
                existingCell = row.createCell(2);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(2);
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getShopName() != null && !retailQuanWang.get(i).getShopName().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getShopName());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //发货地
            existingCell = row.getCell(3);
            if (existingCell == null) {
                existingCell = row.createCell(3);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(3);
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getSellerLocation() != null && !retailQuanWang.get(i).getSellerLocation().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getSellerLocation());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //产品目录
            existingCell = row.getCell(4);
            if (existingCell == null) {
                existingCell = row.createCell(4);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(4);
            cell = row.getCell(4);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getUserSKUCode() != null && !retailQuanWang.get(i).getUserSKUCode().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getUserSKUCode());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //吊牌价
            existingCell = row.getCell(6);
            if (existingCell == null) {
                existingCell = row.createCell(6);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(6);
            cell = row.getCell(6);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getOriginalPrice() != null && !retailQuanWang.get(i).getOriginalPrice().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getOriginalPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //页面活动价
            existingCell = row.getCell(7);
            if (existingCell == null) {
                existingCell = row.createCell(7);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(7);
            cell = row.getCell(7);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getActivityPrice() != null && !retailQuanWang.get(i).getActivityPrice().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getActivityPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //官方指导促销价
            existingCell = row.getCell(8);
            if (existingCell == null) {
                existingCell = row.createCell(8);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(8);
            cell = row.getCell(8);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getGuidancePrice() != null && !retailQuanWang.get(i).getGuidancePrice().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getGuidancePrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //到手价
            existingCell = row.getCell(9);
            if (existingCell == null) {
                existingCell = row.createCell(9);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(9);
            cell = row.getCell(9);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getFinalPrice() != null && !retailQuanWang.get(i).getFinalPrice().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getFinalPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //价差
            existingCell = row.getCell(10);
            if (existingCell == null) {
                existingCell = row.createCell(10);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(10);
            cell = row.getCell(10);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getMarginPrice() != null && !retailQuanWang.get(i).getMarginPrice().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getMarginPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //是否破价
            existingCell = row.getCell(11);
            if (existingCell == null) {
                existingCell = row.createCell(11);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(11);
            cell = row.getCell(11);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getIsbreakPrice().equals("1")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (retailQuanWang.get(i).getIsbreakPrice().equals("0")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("");
                cell.setCellStyle(currentStyle);
            }

            //近30天滚动销量
            existingCell = row.getCell(12);
            if (existingCell == null) {
                existingCell = row.createCell(12);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(12);
            cell = row.getCell(12);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getScrollSales() != null && !retailQuanWang.get(i).getScrollSales().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getScrollSales());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //评论数
            existingCell = row.getCell(13);
            if (existingCell == null) {
                existingCell = row.createCell(13);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(13);
            cell = row.getCell(13);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getCommentNum() != null && !retailQuanWang.get(i).getCommentNum().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getCommentNum());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //库存
            existingCell = row.getCell(14);
            if (existingCell == null) {
                existingCell = row.createCell(14);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(14);
            cell = row.getCell(14);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getStockNum() != null && !retailQuanWang.get(i).getStockNum().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getStockNum());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //在库状态
            existingCell = row.getCell(15);
            if (existingCell == null) {
                existingCell = row.createCell(15);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(15);
            cell = row.getCell(15);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getStockStatus() != null && !retailQuanWang.get(i).getStockStatus().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getStockStatus());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //商品名称
            existingCell = row.getCell(16);
            if (existingCell == null) {
                existingCell = row.createCell(16);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(16);
            cell = row.getCell(16);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getProductName() != null && !retailQuanWang.get(i).getProductName().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getProductName());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //链接
            existingCell = row.getCell(17);
            if (existingCell == null) {
                existingCell = row.createCell(17);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(17);
            cell = row.getCell(17);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getURL() != null && !retailQuanWang.get(i).getURL().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getURL());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //所有促销信息
            existingCell = row.getCell(18);
            if (existingCell == null) {
                existingCell = row.createCell(18);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(18);
            cell = row.getCell(18);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getAllPromotion() != null && !retailQuanWang.get(i).getAllPromotion().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getAllPromotion());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //应用促销信息
            existingCell = row.getCell(19);
            if (existingCell == null) {
                existingCell = row.createCell(19);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(19);
            cell = row.getCell(19);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getUsePromotion() != null && !retailQuanWang.get(i).getUsePromotion().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getUsePromotion());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //超级促销
            existingCell = row.getCell(20);
            if (existingCell == null) {
                existingCell = row.createCell(20);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(20);
            cell = row.getCell(20);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getSuperPromotion() != null && !retailQuanWang.get(i).getSuperPromotion().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getSuperPromotion());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //是否含税
            existingCell = row.getCell(21);
            if (existingCell == null) {
                existingCell = row.createCell(21);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(21);
            cell = row.getCell(21);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getIsTariff() != null && !retailQuanWang.get(i).getIsTariff().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getIsTariff());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //进口税
            existingCell = row.getCell(22);
            if (existingCell == null) {
                existingCell = row.createCell(22);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(22);
            cell = row.getCell(22);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getTariff() != null && !retailQuanWang.get(i).getTariff().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getTariff());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //含进口税到手价
            existingCell = row.getCell(23);
            if (existingCell == null) {
                existingCell = row.createCell(23);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(23);
            cell = row.getCell(23);
            cell.setCellType(CellType.STRING);
            if (retailQuanWang.get(i).getTariffPrice() != null && !retailQuanWang.get(i).getTariffPrice().equals("")) {
                cell.setCellValue(retailQuanWang.get(i).getTariffPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }
        }

        sheet = workbook.getSheet("店铺");
        sheet.setForceFormulaRecalculation(true);
        for (int i = 0; i < retailShop.size(); i++) {
            System.out.println("店铺" + i);
            //将图片读到BufferedImage
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            bufferImg = null;
            File img = new File(pathImg + retailShop.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
            if (img.exists()) {
                bufferImg = ImageIO.read(img);
                // 将图片写入流中
                ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 0, i + 4, 0, i + 4);
                int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                // 创建图片
                XSSFPicture picture = patriarch.createPicture(anchor, inx);
                picture.resize(0.99, 0.90);
            }

            Row row = sheet.getRow(i + 4);
            if (row == null) {
                row = sheet.createRow(i + 4);
            }
            //SKU
            Cell existingCell = row.getCell(1);
            if (existingCell == null) {
                existingCell = row.createCell(1);
            }
            CellStyle currentStyle = existingCell.getCellStyle();
            row.createCell(1);
            Cell cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(retailShop.get(i).getUserSKUCode());
            cell.setCellStyle(currentStyle);
            //官方指导价
            existingCell = row.getCell(2);
            if (existingCell == null) {
                existingCell = row.createCell(2);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(2);
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getGuanFang_price() != null && !retailShop.get(i).getGuanFang_price().equals("")) {
                cell.setCellValue(retailShop.get(i).getGuanFang_price());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //官方指导促销价
            existingCell = row.getCell(3);
            if (existingCell == null) {
                existingCell = row.createCell(3);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(3);
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getGuanFangCuXiao_price() != null && !retailShop.get(i).getGuanFangCuXiao_price().equals("")) {
                cell.setCellValue(retailShop.get(i).getGuanFangCuXiao_price());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //天猫到手价
            existingCell = row.getCell(4);
            if (existingCell == null) {
                existingCell = row.createCell(4);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(4);
            cell = row.getCell(4);
            if (retailShop.get(i).getTmPrice() != null && !retailShop.get(i).getTmPrice().equals("")) {
                if (retailShop.get(i).getTmPrice() != null) {
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula("HYPERLINK(\"" + retailShop.get(i).getTMUrl() + "\",\"" + retailShop.get(i).getTmPrice() + "\")");
                    cell.setCellStyle(currentStyle);
                } else {
                    cell.setCellValue("/");
                    cell.setCellStyle(currentStyle);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //天猫是否有差异
            existingCell = row.getCell(5);
            if (existingCell == null) {
                existingCell = row.createCell(5);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(5);
            cell = row.getCell(5);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getTmPriceDifference() != null && retailShop.get(i).getTmPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (retailShop.get(i).getTmPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }

            //天猫应用机制
            existingCell = row.getCell(6);
            if (existingCell == null) {
                existingCell = row.createCell(6);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(6);
            cell = row.getCell(6);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getTmDiscount() != null && !retailShop.get(i).getTmDiscount().equals("")) {
                cell.setCellValue(retailShop.get(i).getTmDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //Coach到手价
            existingCell = row.getCell(7);
            if (existingCell == null) {
                existingCell = row.createCell(7);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(7);
            cell = row.getCell(7);
            if (retailShop.get(i).getCoachPrice() != null) {
                if (retailShop.get(i).getCoachPrice() != null && !retailShop.get(i).getCoachPrice().equals("")) {
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula("HYPERLINK(\"" + retailShop.get(i).getCoachUrl() + "\",\"" + retailShop.get(i).getCoachPrice() + "\")");
                    cell.setCellStyle(currentStyle);
                } else {
                    cell.setCellValue("/");
                    cell.setCellStyle(currentStyle);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //Coach是否有差异
            existingCell = row.getCell(8);
            if (existingCell == null) {
                existingCell = row.createCell(8);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(8);
            cell = row.getCell(8);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getCoachPriceDifference() != null && retailShop.get(i).getCoachPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (retailShop.get(i).getCoachPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }


            //Coach应用机制
            existingCell = row.getCell(9);
            if (existingCell == null) {
                existingCell = row.createCell(9);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(9);
            cell = row.getCell(9);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getCoachPriceDiscount() != null && !retailShop.get(i).getCoachPriceDiscount().equals("")) {
                cell.setCellValue(retailShop.get(i).getCoachPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //零售到手价
            existingCell = row.getCell(10);
            if (existingCell == null) {
                existingCell = row.createCell(10);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(10);
            cell = row.getCell(10);
            if (retailShop.get(i).getLingShouPrice() != null) {
                if (retailShop.get(i).getLingShouPrice() != null && !retailShop.get(i).getLingShouPrice().equals("")) {
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula("HYPERLINK(\"" + retailShop.get(i).getLingShouUrl() + "\",\"" + retailShop.get(i).getLingShouPrice() + "\")");
                    cell.setCellStyle(currentStyle);
                } else {
                    cell.setCellValue("/");
                    cell.setCellStyle(currentStyle);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //零售是否有差异
            existingCell = row.getCell(11);
            if (existingCell == null) {
                existingCell = row.createCell(11);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(11);
            cell = row.getCell(11);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getLingShouPriceDifference() != null && retailShop.get(i).getLingShouPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (retailShop.get(i).getLingShouPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }
            //零售应用机制
            existingCell = row.getCell(12);
            if (existingCell == null) {
                existingCell = row.createCell(12);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(12);
            cell = row.getCell(12);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getLingShouPriceDiscount() != null && !retailShop.get(i).getLingShouPriceDiscount().equals("")) {
                cell.setCellValue(retailShop.get(i).getLingShouPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //京东到手价
            existingCell = row.getCell(13);
            if (existingCell == null) {
                existingCell = row.createCell(13);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(13);
            cell = row.getCell(13);
            if (retailShop.get(i).getJDPrice() != null) {
                if (retailShop.get(i).getJDPrice() != null && !retailShop.get(i).getJDPrice().equals("")) {
                    cell.setCellType(CellType.FORMULA);
                    cell.setCellFormula("HYPERLINK(\"" + retailShop.get(i).getJDUrl() + "\",\"" + retailShop.get(i).getJDPrice() + "\")");
                    cell.setCellStyle(currentStyle);
                } else {
                    cell.setCellValue("/");
                    cell.setCellStyle(currentStyle);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东是否有差异
            existingCell = row.getCell(14);
            if (existingCell == null) {
                existingCell = row.createCell(14);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(14);
            cell = row.getCell(14);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getJDPriceDifference() != null && retailShop.get(i).getJDPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (retailShop.get(i).getJDPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }

            //京东应用机制
            existingCell = row.getCell(15);
            if (existingCell == null) {
                existingCell = row.createCell(15);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(15);
            cell = row.getCell(15);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getJDPriceDiscount() != null && !retailShop.get(i).getJDPriceDiscount().equals("")) {
                cell.setCellValue(retailShop.get(i).getJDPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //海外到手价
            existingCell = row.getCell(16);
            if (existingCell == null) {
                existingCell = row.createCell(16);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(16);
            cell = row.getCell(16);
            if (retailShop.get(i).getOverseasPrice() != null && !retailShop.get(i).getOverseasPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailShop.get(i).getOverseasUrl() + "\",\"" + retailShop.get(i).getOverseasPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //海外是否有差异
            existingCell = row.getCell(17);
            if (existingCell == null) {
                existingCell = row.createCell(17);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(17);
            cell = row.getCell(17);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getOverseasPriceDifference() != null && retailShop.get(i).getOverseasPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (retailShop.get(i).getOverseasPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }
            //海外应用机制
            existingCell = row.getCell(18);
            if (existingCell == null) {
                existingCell = row.createCell(18);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(18);
            cell = row.getCell(18);
            cell.setCellType(CellType.STRING);
            if (retailShop.get(i).getOverseasPriceDiscount() != null && !retailShop.get(i).getOverseasPriceDiscount().equals("")) {
                cell.setCellValue(retailShop.get(i).getOverseasPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            File outfile = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            fileOutputStream.write(bos.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功！");
    }

    public static void retail_sku_excel(String filename, List<RetailSku> retailSku) throws IOException {
        double tm, jd, overseas, coach, ls;
//        String pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath();
//        String pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath();
//        if(pathExcel.substring(0,4).equals("file")){
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(5);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(5);
//        }else{
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(1);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(1);
//        }
        String pathExcel = "C:\\coach\\excel\\";
        String pathImg = "C:\\coach\\img\\retailSKUimage\\";
        File file = new File(pathExcel + "retail_sku模板.xlsx");
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            workbook = WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //查找到指定的sheet
        Sheet sheet = workbook.getSheet("SKU");
        sheet.setForceFormulaRecalculation(true);
        //图片相关
        BufferedImage bufferImg = null;
        XSSFDrawing patriarch = null;
        //给单元格变黄
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = 0; i < retailSku.size(); i++) {
            //将图片读到BufferedImage
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            bufferImg = null;
            File img = new File(pathImg + retailSku.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
            if (img.exists()) {
                bufferImg = ImageIO.read(img);
                // 将图片写入流中
                ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 0, i + 4, 0, i + 4);
                int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                // 创建图片
                XSSFPicture picture = patriarch.createPicture(anchor, inx);
                picture.resize(0.99, 0.90);
            }
            Row row = sheet.getRow(i + 4);
            if (row == null) {
                row = sheet.createRow(i + 4);
            }
            //sku
            Cell existingCell = row.getCell(1);
            if (existingCell == null) {
                existingCell = row.createCell(1);
            }
            CellStyle currentStyle = existingCell.getCellStyle();
            row.createCell(1);
            Cell cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(retailSku.get(i).getUserSKUCode());
            cell.setCellStyle(currentStyle);
            //天猫官方指导价
            existingCell = row.getCell(2);
            if (existingCell == null) {
                existingCell = row.createCell(2);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(2);
            cell = row.getCell(2);
            if (retailSku.get(i).getTMPrice() != null && !retailSku.get(i).getTMPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getTMUrl() + "\",\"" + retailSku.get(i).getTMPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH官网官方指导价
            existingCell = row.getCell(3);
            if (existingCell == null) {
                existingCell = row.createCell(3);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(3);
            cell = row.getCell(3);
            if (retailSku.get(i).getCoachPrice() != null && !retailSku.get(i).getCoachPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getCoachUrl() + "\",\"" + retailSku.get(i).getCoachPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //零售官方指导价
            existingCell = row.getCell(4);
            if (existingCell == null) {
                existingCell = row.createCell(4);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(4);
            cell = row.getCell(4);
            if (retailSku.get(i).getLingShouPrice() != null && !retailSku.get(i).getLingShouPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getLingShouUrl() + "\",\"" + retailSku.get(i).getLingShouPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东官方指导价
            existingCell = row.getCell(5);
            if (existingCell == null) {
                existingCell = row.createCell(5);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(5);
            cell = row.getCell(5);
            if (retailSku.get(i).getJDPrice() != null && !retailSku.get(i).getJDPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getJDUrl() + "\",\"" + retailSku.get(i).getJDPrice() + "\")");
                cell.setCellStyle(currentStyle);
                jd = 1000000;
                if (retailSku.get(i).getJDPrice() != null) {
                    if (retailSku.get(i).getJDPrice().contains("(")) {
                        jd = Double.parseDouble(retailSku.get(i).getJDPrice().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(retailSku.get(i).getJDPrice());
                    }
                }
                overseas = 1000000;
                if (retailSku.get(i).getOverseasPrice() != null) {
                    if (retailSku.get(i).getOverseasPrice().contains("(")) {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasPrice().split("\\(")[0]);
                    } else {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasPrice());
                    }
                }
//                tm = 1000000;
//                if (retailSku.get(i).getTMPrice() != null) {
//                    if (retailSku.get(i).getTMPrice().contains("(")) {
//                        tm = Double.parseDouble(retailSku.get(i).getTMPrice().split("\\(")[0]);
//                    } else {
//                        tm = Double.parseDouble(retailSku.get(i).getTMPrice());
//                    }
//                }
//                ls = 1000000;
//                if (retailSku.get(i).getLingShouPrice() != null) {
//                    if (retailSku.get(i).getLingShouPrice().contains("(")) {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouPrice().split("\\(")[0]);
//                    } else {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouPrice());
//                    }
//                }
//                coach = 1000000;
//                if (retailSku.get(i).getCoachPrice() != null) {
//                    if (retailSku.get(i).getCoachPrice().contains("(")) {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachPrice().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachPrice());
//                    }
//                }
                if (jd < overseas) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH海外旗舰店官方指导价
            existingCell = row.getCell(6);
            if (existingCell == null) {
                existingCell = row.createCell(6);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(6);
            cell = row.getCell(6);
            if (retailSku.get(i).getOverseasPrice() != null && !retailSku.get(i).getOverseasPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getOverseasUrl() + "\",\"" + retailSku.get(i).getOverseasPrice() + "\")");
                cell.setCellStyle(currentStyle);
                jd = 1000000;
                if (retailSku.get(i).getJDPrice() != null) {
                    if (retailSku.get(i).getJDPrice().contains("(")) {
                        jd = Double.parseDouble(retailSku.get(i).getJDPrice().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(retailSku.get(i).getJDPrice());
                    }
                }
                overseas = 1000000;
                if (retailSku.get(i).getOverseasPrice() != null) {
                    if (retailSku.get(i).getOverseasPrice().contains("(")) {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasPrice().split("\\(")[0]);
                    } else {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasPrice());
                    }
                }
//                tm = 1000000;
//                if (retailSku.get(i).getTMPrice() != null) {
//                    if (retailSku.get(i).getTMPrice().contains("(")) {
//                        tm = Double.parseDouble(retailSku.get(i).getTMPrice().split("\\(")[0]);
//                    } else {
//                        tm = Double.parseDouble(retailSku.get(i).getTMPrice());
//                    }
//                }
//                ls = 1000000;
//                if (retailSku.get(i).getLingShouPrice() != null) {
//                    if (retailSku.get(i).getLingShouPrice().contains("(")) {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouPrice().split("\\(")[0]);
//                    } else {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouPrice());
//                    }
//                }
//                coach = 1000000;
//                if (retailSku.get(i).getCoachPrice() != null) {
//                    if (retailSku.get(i).getCoachPrice().contains("(")) {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachPrice().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachPrice());
//                    }
//                }
                if (overseas < jd) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //官方指导价价差
            existingCell = row.getCell(7);
            if (existingCell == null) {
                existingCell = row.createCell(7);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(7);
            cell = row.getCell(7);
            cell.setCellType(CellType.STRING);
            if (retailSku.get(i).getGF_Jiacha() == null || !retailSku.get(i).getGF_Jiacha().equals("Y")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            }

            //天猫官方指导促销价
            existingCell = row.getCell(8);
            if (existingCell == null) {
                existingCell = row.createCell(8);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(8);
            cell = row.getCell(8);
            if (retailSku.get(i).getTMGuidancePrice() != null && !retailSku.get(i).getTMGuidancePrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getTMUrl() + "\",\"" + retailSku.get(i).getTMGuidancePrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH官网官方指导促销价
            existingCell = row.getCell(9);
            if (existingCell == null) {
                existingCell = row.createCell(9);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(9);
            cell = row.getCell(9);
            if (retailSku.get(i).getCoachGuidancePrice() != null && !retailSku.get(i).getCoachGuidancePrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getCoachUrl() + "\",\"" + retailSku.get(i).getCoachGuidancePrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //零售官方指导促销价
            existingCell = row.getCell(10);
            if (existingCell == null) {
                existingCell = row.createCell(10);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(10);
            cell = row.getCell(10);
            if (retailSku.get(i).getLingShouGuidancePrice() != null && !retailSku.get(i).getLingShouGuidancePrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getLingShouUrl() + "\",\"" + retailSku.get(i).getLingShouGuidancePrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东官方指导促销价
            existingCell = row.getCell(11);
            if (existingCell == null) {
                existingCell = row.createCell(11);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(11);
            cell = row.getCell(11);
            if (retailSku.get(i).getJDGuidancePrice() != null && !retailSku.get(i).getJDGuidancePrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getJDUrl() + "\",\"" + retailSku.get(i).getJDGuidancePrice() + "\")");
                cell.setCellStyle(currentStyle);
                jd = 1000000;
                if (retailSku.get(i).getJDGuidancePrice() != null) {
                    if (retailSku.get(i).getJDGuidancePrice().contains("(")) {
                        jd = Double.parseDouble(retailSku.get(i).getJDGuidancePrice().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(retailSku.get(i).getJDGuidancePrice());
                    }
                }
                overseas = 1000000;
                if (retailSku.get(i).getOverseasGuidancePrice() != null) {
                    if (retailSku.get(i).getOverseasGuidancePrice().contains("(")) {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasGuidancePrice().split("\\(")[0]);
                    } else {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasGuidancePrice());
                    }
                }
//                tm = 1000000;
//                if (retailSku.get(i).getTMGuidancePrice() != null) {
//                    if (retailSku.get(i).getTMGuidancePrice().contains("(")) {
//                        tm = Double.parseDouble(retailSku.get(i).getTMGuidancePrice().split("\\(")[0]);
//                    } else {
//                        tm = Double.parseDouble(retailSku.get(i).getTMGuidancePrice());
//                    }
//                }
//                ls = 1000000;
//                if (retailSku.get(i).getLingShouGuidancePrice() != null) {
//                    if (retailSku.get(i).getLingShouGuidancePrice().contains("(")) {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouGuidancePrice().split("\\(")[0]);
//                    } else {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouGuidancePrice());
//                    }
//                }
//                coach = 1000000;
//                if (retailSku.get(i).getCoachGuidancePrice() != null) {
//                    if (retailSku.get(i).getCoachGuidancePrice().contains("(")) {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachGuidancePrice().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachGuidancePrice());
//                    }
//                }
                if (jd < overseas) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH海外旗舰店官方指导促销价
            existingCell = row.getCell(12);
            if (existingCell == null) {
                existingCell = row.createCell(12);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(12);
            cell = row.getCell(12);
            if (retailSku.get(i).getOverseasGuidancePrice() != null && !retailSku.get(i).getOverseasGuidancePrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getOverseasUrl() + "\",\"" + retailSku.get(i).getOverseasGuidancePrice() + "\")");
                cell.setCellStyle(currentStyle);
                jd = 1000000;
                if (retailSku.get(i).getJDGuidancePrice() != null) {
                    if (retailSku.get(i).getJDGuidancePrice().contains("(")) {
                        jd = Double.parseDouble(retailSku.get(i).getJDGuidancePrice().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(retailSku.get(i).getJDGuidancePrice());
                    }
                }
                overseas = 1000000;
                if (retailSku.get(i).getOverseasGuidancePrice() != null) {
                    if (retailSku.get(i).getOverseasGuidancePrice().contains("(")) {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasGuidancePrice().split("\\(")[0]);
                    } else {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasGuidancePrice());
                    }
                }
//                tm = 1000000;
//                if (retailSku.get(i).getTMGuidancePrice() != null) {
//                    if (retailSku.get(i).getTMGuidancePrice().contains("(")) {
//                        tm = Double.parseDouble(retailSku.get(i).getTMGuidancePrice().split("\\(")[0]);
//                    } else {
//                        tm = Double.parseDouble(retailSku.get(i).getTMGuidancePrice());
//                    }
//                }
//                ls = 1000000;
//                if (retailSku.get(i).getLingShouGuidancePrice() != null) {
//                    if (retailSku.get(i).getLingShouGuidancePrice().contains("(")) {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouGuidancePrice().split("\\(")[0]);
//                    } else {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouGuidancePrice());
//                    }
//                }
//                coach = 1000000;
//                if (retailSku.get(i).getCoachGuidancePrice() != null) {
//                    if (retailSku.get(i).getCoachGuidancePrice().contains("(")) {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachGuidancePrice().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachGuidancePrice());
//                    }
//                }
                if (overseas < jd) {
                    cell.setCellStyle(style);
                }

            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //官方指导促销价价差
            existingCell = row.getCell(13);
            if (existingCell == null) {
                existingCell = row.createCell(13);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(13);
            cell = row.getCell(13);
            cell.setCellType(CellType.STRING);
            if (retailSku.get(i).getGFCX_Jiacha() == null || !retailSku.get(i).getGFCX_Jiacha().equals("Y")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            }

            //天猫到手价
            existingCell = row.getCell(14);
            if (existingCell == null) {
                existingCell = row.createCell(14);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(14);
            cell = row.getCell(14);
            if (retailSku.get(i).getTMFinalPrice() != null && !retailSku.get(i).getTMFinalPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getTMUrl() + "\",\"" + retailSku.get(i).getTMFinalPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH官网官方到手价
            existingCell = row.getCell(15);
            if (existingCell == null) {
                existingCell = row.createCell(15);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(15);
            cell = row.getCell(15);
            if (retailSku.get(i).getCoachFinalPrice() != null && !retailSku.get(i).getCoachFinalPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getCoachUrl() + "\",\"" + retailSku.get(i).getCoachFinalPrice() + "\")");
                cell.setCellStyle(currentStyle);

            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //零售官方到手价
            existingCell = row.getCell(16);
            if (existingCell == null) {
                existingCell = row.createCell(16);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(16);
            cell = row.getCell(16);
            if (retailSku.get(i).getLingShouFinalPrice() != null && !retailSku.get(i).getLingShouFinalPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getLingShouUrl() + "\",\"" + retailSku.get(i).getLingShouFinalPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东官方到手价
            existingCell = row.getCell(17);
            if (existingCell == null) {
                existingCell = row.createCell(17);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(17);
            cell = row.getCell(17);
            if (retailSku.get(i).getJDFinalPrice() != null && !retailSku.get(i).getJDFinalPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getJDUrl() + "\",\"" + retailSku.get(i).getJDFinalPrice() + "\")");
                cell.setCellStyle(currentStyle);
                jd = 1000000;
                if (retailSku.get(i).getJDFinalPrice() != null) {
                    if (retailSku.get(i).getJDFinalPrice().contains("(")) {
                        jd = Double.parseDouble(retailSku.get(i).getJDFinalPrice().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(retailSku.get(i).getJDFinalPrice());
                    }
                }
                overseas = 1000000;
                if (retailSku.get(i).getOverseasFinalPrice() != null) {
                    if (retailSku.get(i).getOverseasFinalPrice().contains("(")) {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasFinalPrice().split("\\(")[0]);
                    } else {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasFinalPrice());
                    }
                }
//                tm = 1000000;
//                if (retailSku.get(i).getTMFinalPrice() != null) {
//                    if (retailSku.get(i).getTMFinalPrice().contains("(")) {
//                        tm = Double.parseDouble(retailSku.get(i).getTMFinalPrice().split("\\(")[0]);
//                    } else {
//                        tm = Double.parseDouble(retailSku.get(i).getTMFinalPrice());
//                    }
//                }
//                ls = 1000000;
//                if (retailSku.get(i).getLingShouFinalPrice() != null) {
//                    if (retailSku.get(i).getLingShouFinalPrice().contains("(")) {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouFinalPrice().split("\\(")[0]);
//                    } else {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouFinalPrice());
//                    }
//                }
//                coach = 1000000;
//                if (retailSku.get(i).getCoachFinalPrice() != null) {
//                    if (retailSku.get(i).getCoachFinalPrice().contains("(")) {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachFinalPrice().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachFinalPrice());
//                    }
//                }
                if (jd < overseas) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH海外旗舰店官方到手价
            existingCell = row.getCell(18);
            if (existingCell == null) {
                existingCell = row.createCell(18);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(18);
            cell = row.getCell(18);
            if (retailSku.get(i).getOverseasFinalPrice() != null && !retailSku.get(i).getOverseasFinalPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + retailSku.get(i).getOverseasUrl() + "\",\"" + retailSku.get(i).getOverseasFinalPrice() + "\")");
                cell.setCellStyle(currentStyle);

                jd = 1000000;
                if (retailSku.get(i).getJDFinalPrice() != null) {
                    if (retailSku.get(i).getJDFinalPrice().contains("(")) {
                        jd = Double.parseDouble(retailSku.get(i).getJDFinalPrice().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(retailSku.get(i).getJDFinalPrice());
                    }
                }
                overseas = 1000000;
                if (retailSku.get(i).getOverseasFinalPrice() != null) {
                    if (retailSku.get(i).getOverseasFinalPrice().contains("(")) {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasFinalPrice().split("\\(")[0]);
                    } else {
                        overseas = Double.parseDouble(retailSku.get(i).getOverseasFinalPrice());
                    }
                }
//                tm = 1000000;
//                if (retailSku.get(i).getTMFinalPrice() != null) {
//                    if (retailSku.get(i).getTMFinalPrice().contains("(")) {
//                        tm = Double.parseDouble(retailSku.get(i).getTMFinalPrice().split("\\(")[0]);
//                    } else {
//                        tm = Double.parseDouble(retailSku.get(i).getTMFinalPrice());
//                    }
//                }
//                ls = 1000000;
//                if (retailSku.get(i).getLingShouFinalPrice() != null) {
//                    if (retailSku.get(i).getLingShouFinalPrice().contains("(")) {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouFinalPrice().split("\\(")[0]);
//                    } else {
//                        ls = Double.parseDouble(retailSku.get(i).getLingShouFinalPrice());
//                    }
//                }
//                coach = 1000000;
//                if (retailSku.get(i).getCoachFinalPrice() != null) {
//                    if (retailSku.get(i).getCoachFinalPrice().contains("(")) {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachFinalPrice().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(retailSku.get(i).getCoachFinalPrice());
//                    }
//                }
                if (overseas < jd ) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //官方到手价价差
            existingCell = row.getCell(19);
            if (existingCell == null) {
                existingCell = row.createCell(19);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(19);
            cell = row.getCell(19);
            cell.setCellType(CellType.STRING);
            if (retailSku.get(i).getFinal_Jiacha() == null || !retailSku.get(i).getFinal_Jiacha().equals("Y")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            File outfile = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            fileOutputStream.write(bos.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功！");
    }

    public static void outlet_Shop_QuanWang_excel(String filename, List<CoachItem> outletQuanWang, List<OutletShop> outletShop) throws IOException {
//        String pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath();
//        String pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/outletSKUimage/").getPath();
//        if(pathExcel.substring(0,4).equals("file")){
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(5);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(5);
//        }else{
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(1);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(1);
//        }
        String pathExcel = "C:\\coach\\excel\\";
        String pathImg = "C:\\coach\\img\\outletSKUimage\\";
        File file = new File(pathExcel + "outlet_店铺&全网日报模板.xlsx");
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            workbook = WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查找到指定的sheet
        Sheet sheet = workbook.getSheet("全网");
        sheet.setForceFormulaRecalculation(true);
        //图片相关
        BufferedImage bufferImg = null;
        XSSFDrawing patriarch = null;
        for (int i = 0; i < outletQuanWang.size(); i++) {
            System.out.println("outlet全网" + i);
            //将图片读到BufferedImage
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            if (i != 0 && outletQuanWang.get(i).getUserSKUCode().equals(outletQuanWang.get(i - 1).getUserSKUCode())) {

            } else {
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                bufferImg = null;
                File img = new File(pathImg + outletQuanWang.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
                if (img.exists()) {
                    bufferImg = ImageIO.read(img);
                    // 将图片写入流中
                    ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                    patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 5, i + 2, 5, i + 2);
                    int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                    // 创建图片
                    XSSFPicture picture = patriarch.createPicture(anchor, inx);
                    picture.resize(0.99, 0.90);
                }
            }
            if (i == 0) {
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                bufferImg = null;
                File img = new File(pathImg + outletQuanWang.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
                if (img.exists()) {
                    bufferImg = ImageIO.read(img);
                    // 将图片写入流中
                    ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                    patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 5, i + 2, 5, i + 2);
                    int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                    // 创建图片
                    XSSFPicture picture = patriarch.createPicture(anchor, inx);
                    picture.resize(0.99, 0.90);
                }
            }

            Row row = sheet.getRow(i + 2);
            if (row == null) {
                row = sheet.createRow(i + 2);
            }
            //观察时间
            Cell existingCell = row.getCell(0);
            if (existingCell == null) {
                existingCell = row.createCell(0);
            }
            CellStyle currentStyle = existingCell.getCellStyle();
            row.createCell(0);
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
//            cell.setCellValue(outletQuanWang.get(i).getViewTime());
//            cell.setCellStyle(currentStyle);

            if (outletQuanWang.get(i).getViewTime() != null && !outletQuanWang.get(i).getViewTime().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getViewTime().split("T")[0]);
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //电商平台
            existingCell = row.getCell(1);
            if (existingCell == null) {
                existingCell = row.createCell(1);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(1);
            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(outletQuanWang.get(i).getChannel());
            cell.setCellStyle(currentStyle);
            //店铺名称
            existingCell = row.getCell(2);
            if (existingCell == null) {
                existingCell = row.createCell(2);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(2);
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getShopName() != null && !outletQuanWang.get(i).getShopName().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getShopName());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //发货地
            existingCell = row.getCell(3);
            if (existingCell == null) {
                existingCell = row.createCell(3);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(3);
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getSellerLocation() != null && !outletQuanWang.get(i).getSellerLocation().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getSellerLocation());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //产品目录
            existingCell = row.getCell(4);
            if (existingCell == null) {
                existingCell = row.createCell(4);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(4);
            cell = row.getCell(4);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getUserSKUCode() != null && !outletQuanWang.get(i).getUserSKUCode().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getUserSKUCode());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //吊牌价
            existingCell = row.getCell(6);
            if (existingCell == null) {
                existingCell = row.createCell(6);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(6);
            cell = row.getCell(6);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getOriginalPrice() != null && !outletQuanWang.get(i).getOriginalPrice().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getOriginalPrice());
                cell.setCellStyle(currentStyle);
            }

            //页面活动价
            existingCell = row.getCell(7);
            if (existingCell == null) {
                existingCell = row.createCell(7);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(7);
            cell = row.getCell(7);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getActivityPrice() != null && !outletQuanWang.get(i).getActivityPrice().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getActivityPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //官方指导促销价
            existingCell = row.getCell(8);
            if (existingCell == null) {
                existingCell = row.createCell(8);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(8);
            cell = row.getCell(8);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getGuidancePrice() != null && !outletQuanWang.get(i).getGuidancePrice().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getGuidancePrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //到手价
            existingCell = row.getCell(9);
            if (existingCell == null) {
                existingCell = row.createCell(9);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(9);
            cell = row.getCell(9);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getFinalPrice() != null && !outletQuanWang.get(i).getFinalPrice().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getFinalPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //价差
            existingCell = row.getCell(10);
            if (existingCell == null) {
                existingCell = row.createCell(10);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(10);
            cell = row.getCell(10);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getMarginPrice() != null && !outletQuanWang.get(i).getMarginPrice().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getMarginPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //是否破价
            existingCell = row.getCell(11);
            if (existingCell == null) {
                existingCell = row.createCell(11);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(11);
            cell = row.getCell(11);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getIsbreakPrice().equals("1")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (outletQuanWang.get(i).getIsbreakPrice().equals("0")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //近30天滚动销量
            existingCell = row.getCell(12);
            if (existingCell == null) {
                existingCell = row.createCell(12);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(12);
            cell = row.getCell(12);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getScrollSales() != null && !outletQuanWang.get(i).getScrollSales().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getScrollSales());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //评论数
            existingCell = row.getCell(13);
            if (existingCell == null) {
                existingCell = row.createCell(13);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(13);
            cell = row.getCell(13);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getCommentNum() != null && !outletQuanWang.get(i).getCommentNum().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getCommentNum());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //库存
            existingCell = row.getCell(14);
            if (existingCell == null) {
                existingCell = row.createCell(14);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(14);
            cell = row.getCell(14);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getStockNum() != null && !outletQuanWang.get(i).getStockNum().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getStockNum());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //在库状态
            existingCell = row.getCell(15);
            if (existingCell == null) {
                existingCell = row.createCell(15);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(15);
            cell = row.getCell(15);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getStockStatus() != null && !outletQuanWang.get(i).getStockStatus().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getStockStatus());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //商品名称
            existingCell = row.getCell(16);
            if (existingCell == null) {
                existingCell = row.createCell(16);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(16);
            cell = row.getCell(16);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getProductName() != null && !outletQuanWang.get(i).getProductName().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getProductName());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //链接
            existingCell = row.getCell(17);
            if (existingCell == null) {
                existingCell = row.createCell(17);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(17);
            cell = row.getCell(17);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getURL() != null && !outletQuanWang.get(i).getURL().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getURL());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //所有促销信息
            existingCell = row.getCell(18);
            if (existingCell == null) {
                existingCell = row.createCell(18);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(18);
            cell = row.getCell(18);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getAllPromotion() != null && !outletQuanWang.get(i).getAllPromotion().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getAllPromotion());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //应用促销信息
            existingCell = row.getCell(19);
            if (existingCell == null) {
                existingCell = row.createCell(19);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(19);
            cell = row.getCell(19);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getUsePromotion() != null && !outletQuanWang.get(i).getUsePromotion().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getUsePromotion());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }


            //超级促销
            existingCell = row.getCell(20);
            if (existingCell == null) {
                existingCell = row.createCell(20);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(20);
            cell = row.getCell(20);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getSuperPromotion() != null && !outletQuanWang.get(i).getSuperPromotion().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getSuperPromotion());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //是否含税
            existingCell = row.getCell(21);
            if (existingCell == null) {
                existingCell = row.createCell(21);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(21);
            cell = row.getCell(21);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getIsTariff() != null && !outletQuanWang.get(i).getIsTariff().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getIsTariff());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //进口税
            existingCell = row.getCell(22);
            if (existingCell == null) {
                existingCell = row.createCell(22);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(22);
            cell = row.getCell(22);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getTariff() != null && !outletQuanWang.get(i).getTariff().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getTariff());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

            //含进口税到手价
            existingCell = row.getCell(23);
            if (existingCell == null) {
                existingCell = row.createCell(23);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(23);
            cell = row.getCell(23);
            cell.setCellType(CellType.STRING);
            if (outletQuanWang.get(i).getTariffPrice() != null && !outletQuanWang.get(i).getTariffPrice().equals("")) {
                cell.setCellValue(outletQuanWang.get(i).getTariffPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("-");
                cell.setCellStyle(currentStyle);
            }

        }

        sheet = workbook.getSheet("店铺");
        sheet.setForceFormulaRecalculation(true);
        for (int i = 0; i < outletShop.size(); i++) {
            System.out.println("outlet店铺" + i);
            //将图片读到BufferedImage
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            bufferImg = null;
            File img = new File(pathImg + outletShop.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
            if (img.exists()) {
                bufferImg = ImageIO.read(img);
                // 将图片写入流中
                ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 0, i + 4, 0, i + 4);
                int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                // 创建图片
                XSSFPicture picture = patriarch.createPicture(anchor, inx);
                picture.resize(0.99, 0.90);
            }
            Row row = sheet.getRow(i + 4);
            if (row == null) {
                row = sheet.createRow(i + 4);
            }
            //SKU
            Cell existingCell = row.getCell(1);
            if (existingCell == null) {
                existingCell = row.createCell(1);
            }
            CellStyle currentStyle = existingCell.getCellStyle();
            row.createCell(1);
            Cell cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(outletShop.get(i).getUserSKUCode());
            cell.setCellStyle(currentStyle);
            //官方指导价
            existingCell = row.getCell(2);
            if (existingCell == null) {
                existingCell = row.createCell(2);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(2);
            cell = row.getCell(2);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(outletShop.get(i).getGuanFang_price());
            cell.setCellStyle(currentStyle);
            //官方指导促销价
            existingCell = row.getCell(3);
            if (existingCell == null) {
                existingCell = row.createCell(3);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(3);
            cell = row.getCell(3);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(outletShop.get(i).getGuanFangCuXiao_price());
            cell.setCellStyle(currentStyle);
            //outlet到手价
            existingCell = row.getCell(4);
            if (existingCell == null) {
                existingCell = row.createCell(4);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(4);
            cell = row.getCell(4);
            if (outletShop.get(i).getOutletPrice() != null && !outletShop.get(i).getOutletPrice().equals("")) {
                //cell.setCellType(CellType.STRING);
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletShop.get(i).getOutletUrl() + "\",\"" + outletShop.get(i).getOutletPrice() + "\")");
                //retailShop.get(i).getTMUrl(),retailShop.get(i).getTmPrice()
                //cell.setCellValue(retailShop.get(i).getTmPrice());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //outlet是否有差异
            existingCell = row.getCell(5);
            if (existingCell == null) {
                existingCell = row.createCell(5);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(5);
            cell = row.getCell(5);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getOutletPriceDifference() != null && outletShop.get(i).getOutletPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (outletShop.get(i).getOutletPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }

            //outlet应用机制
            existingCell = row.getCell(6);
            if (existingCell == null) {
                existingCell = row.createCell(6);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(6);
            cell = row.getCell(6);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getOutletDiscount() != null && !outletShop.get(i).getOutletDiscount().equals("")) {
                cell.setCellValue(outletShop.get(i).getOutletDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //Coach到手价
            existingCell = row.getCell(7);
            if (existingCell == null) {
                existingCell = row.createCell(7);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(7);
            cell = row.getCell(7);
            if (outletShop.get(i).getCoachPrice() != null && !outletShop.get(i).getCoachPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletShop.get(i).getCoachUrl() + "\",\"" + outletShop.get(i).getCoachPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //Coach是否有差异
            existingCell = row.getCell(8);
            if (existingCell == null) {
                existingCell = row.createCell(8);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(8);
            cell = row.getCell(8);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getCoachPriceDifference() != null && outletShop.get(i).getCoachPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (outletShop.get(i).getCoachPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }

            //Coach应用机制
            existingCell = row.getCell(9);
            if (existingCell == null) {
                existingCell = row.createCell(9);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(9);
            cell = row.getCell(9);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getCoachPriceDiscount() != null && !outletShop.get(i).getCoachPriceDiscount().equals("")) {
                cell.setCellValue(outletShop.get(i).getCoachPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //海外到手价
            existingCell = row.getCell(10);
            if (existingCell == null) {
                existingCell = row.createCell(10);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(10);
            cell = row.getCell(10);
            if (outletShop.get(i).getOverseasPrice() != null && !outletShop.get(i).getOverseasPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletShop.get(i).getOverseasUrl() + "\",\"" + outletShop.get(i).getOverseasPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //海外是否有差异
            existingCell = row.getCell(11);
            if (existingCell == null) {
                existingCell = row.createCell(11);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(11);
            cell = row.getCell(11);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getOverseasPriceDifference() != null && outletShop.get(i).getOverseasPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (outletShop.get(i).getOverseasPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }

            //海外应用机制
            existingCell = row.getCell(12);
            if (existingCell == null) {
                existingCell = row.createCell(12);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(12);
            cell = row.getCell(12);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getOverseasPriceDiscount() != null && !outletShop.get(i).getOverseasPriceDiscount().equals("")) {
                cell.setCellValue(outletShop.get(i).getOverseasPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //京东到手价
            existingCell = row.getCell(13);
            if (existingCell == null) {
                existingCell = row.createCell(13);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(13);
            cell = row.getCell(13);
            if (outletShop.get(i).getJDPrice() != null && !outletShop.get(i).getJDPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletShop.get(i).getJDUrl() + "\",\"" + outletShop.get(i).getJDPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东是否有差异
            existingCell = row.getCell(14);
            if (existingCell == null) {
                existingCell = row.createCell(14);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(14);
            cell = row.getCell(14);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getJDPriceDifference() != null && outletShop.get(i).getJDPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (outletShop.get(i).getJDPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }

            //京东应用机制
            existingCell = row.getCell(15);
            if (existingCell == null) {
                existingCell = row.createCell(15);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(15);
            cell = row.getCell(15);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getJDPriceDiscount() != null && !outletShop.get(i).getJDPriceDiscount().equals("")) {
                cell.setCellValue(outletShop.get(i).getJDPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }

            //授权海外到手价
            existingCell = row.getCell(16);
            if (existingCell == null) {
                existingCell = row.createCell(16);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(16);
            cell = row.getCell(16);
            if (outletShop.get(i).getCoachOSPrice() != null && !outletShop.get(i).getCoachOSPrice().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletShop.get(i).getCoachOSUrl() + "\",\"" + outletShop.get(i).getCoachOSPrice() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //授权海外是否有差异
            existingCell = row.getCell(17);
            if (existingCell == null) {
                existingCell = row.createCell(17);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(17);
            cell = row.getCell(17);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getCoachOSPriceDifference() != null && outletShop.get(i).getCoachOSPriceDifference().equals("Y")) {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            } else if (outletShop.get(i).getCoachOSPrice() == null) {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            }
            //授权海外应用机制
            existingCell = row.getCell(18);
            if (existingCell == null) {
                existingCell = row.createCell(18);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(18);
            cell = row.getCell(18);
            cell.setCellType(CellType.STRING);
            if (outletShop.get(i).getCoachOSPriceDiscount() != null && !outletShop.get(i).getCoachOSPriceDiscount().equals("")) {
                cell.setCellValue(outletShop.get(i).getCoachOSPriceDiscount());
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            File outfile = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            fileOutputStream.write(bos.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功！");
    }

    public static void outlet_sku_excel(String filename, List<OutletSku> outletSku) throws IOException {
//        String pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath();
//        String pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath();
//        if(pathExcel.substring(0,4).equals("file")){
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(5);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(5);
//        }else{
//            pathExcel=Thread.currentThread().getContextClassLoader().getResource("static/excel/").getPath().substring(1);
//            pathImg=Thread.currentThread().getContextClassLoader().getResource("static/img/retailSKUimage/").getPath().substring(1);
//        }
        String pathExcel = "C:\\coach\\excel\\";
        String pathImg = "C:\\coach\\img\\outletSKUimage\\";
        double hw, jd, sqhw, coach, outlet;
        File file = new File(pathExcel + "outlet_sku模板.xlsx");
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            workbook = WorkbookFactory.create(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查找到指定的sheet
        Sheet sheet = workbook.getSheet("SKU");
        sheet.setForceFormulaRecalculation(true);
        //图片相关
        BufferedImage bufferImg = null;
        XSSFDrawing patriarch = null;
        //给单元格变黄
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = 0; i < outletSku.size(); i++) {
            //将图片读到BufferedImage
            //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            bufferImg = null;
            File img = new File(pathImg + outletSku.get(i).getUserSKUCode().replaceAll(" ", "").replaceAll("/", "") + ".jpeg");
            if (img.exists()) {
                bufferImg = ImageIO.read(img);
                //将图片写入流中
                ImageIO.write(bufferImg, "jpeg", byteArrayOut);
                patriarch = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor(10000 * 10, 10000 * 10, 0, 0, 0, i + 4, 0, i + 4);
                int inx = workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG);
                // 创建图片
                XSSFPicture picture = patriarch.createPicture(anchor, inx);
                picture.resize(0.99, 0.90);
            }

            Row row = sheet.getRow(i + 4);
            if (row == null) {
                row = sheet.createRow(i + 4);
            }
            //sku
            Cell existingCell = row.getCell(1);
            if (existingCell == null) {
                existingCell = row.createCell(1);
            }
            CellStyle currentStyle = existingCell.getCellStyle();
            row.createCell(1);
            Cell cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(outletSku.get(i).getUserSKUCode());
            cell.setCellStyle(currentStyle);
            //outlet官方指导价
            existingCell = row.getCell(2);
            if (existingCell == null) {
                existingCell = row.createCell(2);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(2);
            cell = row.getCell(2);
            if (outletSku.get(i).getOutlieFlagshipStore_gfzdj() != null && !outletSku.get(i).getOutlieFlagshipStore_gfzdj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOutlieUrl() + "\",\"" + outletSku.get(i).getOutlieFlagshipStore_gfzdj() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH官网官方指导价
            existingCell = row.getCell(3);
            if (existingCell == null) {
                existingCell = row.createCell(3);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(3);
            cell = row.getCell(3);
            if (outletSku.get(i).getWebsite_gfzdj() != null && !outletSku.get(i).getWebsite_gfzdj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getWebsiteUrl() + "\",\"" + outletSku.get(i).getWebsite_gfzdj() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //海外官方指导价
            existingCell = row.getCell(4);
            if (existingCell == null) {
                existingCell = row.createCell(4);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(4);
            cell = row.getCell(4);
            if (outletSku.get(i).getOverseasStores_gfzdj() != null && !outletSku.get(i).getOverseasStores_gfzdj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOverseasUrl() + "\",\"" + outletSku.get(i).getOverseasStores_gfzdj() + "\")");
                cell.setCellStyle(currentStyle);
                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_gfzdj() != null) {
                    if (outletSku.get(i).getOverseasStores_gfzdj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_gfzdj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_gfzdj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_gfzdj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_gfzdj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_gfzdj() != null) {
//                    if (outletSku.get(i).getWebsite_gfzdj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_gfzdj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_gfzdj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdj());
//                    }
//                }
                if (hw < jd  && hw < sqhw ) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东官方指导价
            existingCell = row.getCell(5);
            if (existingCell == null) {
                existingCell = row.createCell(5);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(5);
            cell = row.getCell(5);
            if (outletSku.get(i).getJDSelfOperated_gfzdj() != null && !outletSku.get(i).getJDSelfOperated_gfzdj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getJDUrl() + "\",\"" + outletSku.get(i).getJDSelfOperated_gfzdj() + "\")");
                cell.setCellStyle(currentStyle);
                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_gfzdj() != null) {
                    if (outletSku.get(i).getOverseasStores_gfzdj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_gfzdj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_gfzdj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_gfzdj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_gfzdj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_gfzdj() != null) {
//                    if (outletSku.get(i).getWebsite_gfzdj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_gfzdj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_gfzdj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdj());
//                    }
//                }
                if (jd < hw  && jd < sqhw ) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH海外授权店官方指导价
            existingCell = row.getCell(6);
            if (existingCell == null) {
                existingCell = row.createCell(6);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(6);
            cell = row.getCell(6);
            if (outletSku.get(i).getOverseasStores_gfzdj() != null && !outletSku.get(i).getOverseasStores_gfzdj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOverseasUrl() + "\",\"" + outletSku.get(i).getOverseasStores_gfzdj() + "\")");
                cell.setCellStyle(currentStyle);

                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_gfzdj() != null) {
                    if (outletSku.get(i).getOverseasStores_gfzdj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_gfzdj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_gfzdj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_gfzdj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_gfzdj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_gfzdj() != null) {
//                    if (outletSku.get(i).getWebsite_gfzdj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_gfzdj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_gfzdj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdj());
//                    }
//                }
                if (sqhw < hw  && sqhw < jd ) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //官方指导价价差
            existingCell = row.getCell(7);
            if (existingCell == null) {
                existingCell = row.createCell(7);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(7);
            cell = row.getCell(7);
            cell.setCellType(CellType.STRING);
            if (outletSku.get(i).getPricrDifference_gfzdj() == null || !outletSku.get(i).getPricrDifference_gfzdj().equals("Y")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            }

            //outlet官方指导价
            existingCell = row.getCell(8);
            if (existingCell == null) {
                existingCell = row.createCell(8);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(8);
            cell = row.getCell(8);
            if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj() != null && !outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOutlieUrl() + "\",\"" + outletSku.get(i).getOutlieFlagshipStore_gfzdcxj() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH官网官方指导价
            existingCell = row.getCell(9);
            if (existingCell == null) {
                existingCell = row.createCell(9);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(9);
            cell = row.getCell(9);
            if (outletSku.get(i).getWebsite_gfzdcxj() != null && !outletSku.get(i).getWebsite_gfzdcxj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getWebsiteUrl() + "\",\"" + outletSku.get(i).getWebsite_gfzdcxj() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //海外官方指导价
            existingCell = row.getCell(10);
            if (existingCell == null) {
                existingCell = row.createCell(10);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(10);
            cell = row.getCell(10);
            if (outletSku.get(i).getTMallGlobalStore_gfzdcxj() != null && !outletSku.get(i).getTMallGlobalStore_gfzdcxj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOverseasUrl() + "\",\"" + outletSku.get(i).getTMallGlobalStore_gfzdcxj() + "\")");
                cell.setCellStyle(currentStyle);

                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_gfzdj() != null) {
                    if (outletSku.get(i).getOverseasStores_gfzdj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_gfzdcxj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_gfzdcxj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdcxj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdcxj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_gfzdcxj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_gfzdcxj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdcxj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdcxj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_gfzdcxj() != null) {
//                    if (outletSku.get(i).getWebsite_gfzdcxj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdcxj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdcxj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdcxj());
//                    }
//                }
                if (hw < jd  && hw < sqhw ) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东官方指导价
            existingCell = row.getCell(11);
            if (existingCell == null) {
                existingCell = row.createCell(11);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(11);
            cell = row.getCell(11);
            if (outletSku.get(i).getJDSelfOperated_gfzdcxj() != null && !outletSku.get(i).getJDSelfOperated_gfzdcxj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getJDUrl() + "\",\"" + outletSku.get(i).getJDSelfOperated_gfzdcxj() + "\")");
                cell.setCellStyle(currentStyle);
                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_gfzdcxj() != null) {
                    if (outletSku.get(i).getOverseasStores_gfzdcxj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdcxj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdcxj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_gfzdcxj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_gfzdcxj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdcxj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdcxj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_gfzdcxj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_gfzdcxj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdcxj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdcxj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_gfzdcxj() != null) {
//                    if (outletSku.get(i).getWebsite_gfzdcxj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdcxj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdcxj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdcxj());
//                    }
//                }
                if (jd < hw  && jd < sqhw ) {
                    cell.setCellStyle(style);
                }
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH海外旗舰店官方指导价
            existingCell = row.getCell(12);
            if (existingCell == null) {
                existingCell = row.createCell(12);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(12);
            cell = row.getCell(12);
            if (outletSku.get(i).getOverseasStores_gfzdcxj() != null && !outletSku.get(i).getOverseasStores_gfzdcxj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOverseasUrl() + "\",\"" + outletSku.get(i).getOverseasStores_gfzdcxj() + "\")");
                cell.setCellStyle(currentStyle);
                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_gfzdcxj() != null) {
                    if (outletSku.get(i).getOverseasStores_gfzdcxj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdcxj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_gfzdcxj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_gfzdcxj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_gfzdcxj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdcxj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_gfzdcxj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_gfzdcxj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_gfzdcxj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdcxj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_gfzdcxj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_gfzdcxj() != null) {
//                    if (outletSku.get(i).getWebsite_gfzdcxj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdcxj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_gfzdcxj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdcxj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_gfzdcxj());
//                    }
//                }
                if (sqhw < hw  && sqhw < jd ) {
                    cell.setCellStyle(style);
                }

            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //官方指导促销价价差
            existingCell = row.getCell(13);
            if (existingCell == null) {
                existingCell = row.createCell(13);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(13);
            cell = row.getCell(13);
            cell.setCellType(CellType.STRING);
            if (outletSku.get(i).getPricrDifference_gfzdcxj() == null || !outletSku.get(i).getPricrDifference_gfzdcxj().equals("Y")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            }

            //outlet到手价
            existingCell = row.getCell(14);
            if (existingCell == null) {
                existingCell = row.createCell(14);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(14);
            cell = row.getCell(14);
            if (outletSku.get(i).getOutlieFlagshipStore_dsj() != null && !outletSku.get(i).getOutlieFlagshipStore_dsj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOutlieUrl() + "\",\"" + outletSku.get(i).getOutlieFlagshipStore_dsj() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //COACH官网到手价
            existingCell = row.getCell(15);
            if (existingCell == null) {
                existingCell = row.createCell(15);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(15);
            cell = row.getCell(15);
            if (outletSku.get(i).getWebsite_dsj() != null && !outletSku.get(i).getWebsite_dsj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getWebsiteUrl() + "\",\"" + outletSku.get(i).getWebsite_dsj() + "\")");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //海外到手价
            existingCell = row.getCell(16);
            if (existingCell == null) {
                existingCell = row.createCell(16);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(16);
            cell = row.getCell(16);
            if (outletSku.get(i).getTMallGlobalStore_dsj() != null && !outletSku.get(i).getTMallGlobalStore_dsj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getTMallUrl() + "\",\"" + outletSku.get(i).getTMallGlobalStore_dsj() + "\")");
                cell.setCellStyle(currentStyle);

                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_dsj() != null) {
                    if (outletSku.get(i).getOverseasStores_dsj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_dsj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_dsj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_dsj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_dsj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_dsj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_dsj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_dsj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_dsj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_dsj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_dsj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_dsj() != null) {
//                    if (outletSku.get(i).getWebsite_dsj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_dsj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_dsj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_dsj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_dsj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_dsj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_dsj());
//                    }
//                }
                if (hw < sqhw && hw < jd ) {
                    cell.setCellStyle(style);
                }

            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //京东到手价
            existingCell = row.getCell(17);
            if (existingCell == null) {
                existingCell = row.createCell(17);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(17);
            cell = row.getCell(17);
            if (outletSku.get(i).getJDSelfOperated_dsj() != null && !outletSku.get(i).getJDSelfOperated_dsj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getJDUrl() + "\",\"" + outletSku.get(i).getJDSelfOperated_dsj() + "\")");
                cell.setCellStyle(currentStyle);
                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_dsj() != null) {
                    if (outletSku.get(i).getOverseasStores_dsj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_dsj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_dsj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_dsj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_dsj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_dsj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_dsj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_dsj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_dsj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_dsj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_dsj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_dsj() != null) {
//                    if (outletSku.get(i).getWebsite_dsj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_dsj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_dsj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_dsj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_dsj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_dsj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_dsj());
//                    }
//                }
                if (jd < hw  && jd < sqhw ) {
                    cell.setCellStyle(style);
                }

            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //海外授权到手价
            existingCell = row.getCell(18);
            if (existingCell == null) {
                existingCell = row.createCell(18);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(18);
            cell = row.getCell(18);
            if (outletSku.get(i).getOverseasStores_dsj() != null && !outletSku.get(i).getOverseasStores_dsj().equals("")) {
                cell.setCellType(CellType.FORMULA);
                cell.setCellFormula("HYPERLINK(\"" + outletSku.get(i).getOverseasUrl() + "\",\"" + outletSku.get(i).getOverseasStores_dsj() + "\")");
                cell.setCellStyle(currentStyle);
                sqhw = 1000000;
                if (outletSku.get(i).getOverseasStores_dsj() != null) {
                    if (outletSku.get(i).getOverseasStores_dsj().contains("(")) {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_dsj().split("\\(")[0]);
                    } else {
                        sqhw = Double.parseDouble(outletSku.get(i).getOverseasStores_dsj());
                    }
                }
                jd = 1000000;
                if (outletSku.get(i).getJDSelfOperated_dsj() != null) {
                    if (outletSku.get(i).getJDSelfOperated_dsj().contains("(")) {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_dsj().split("\\(")[0]);
                    } else {
                        jd = Double.parseDouble(outletSku.get(i).getJDSelfOperated_dsj());
                    }
                }
                hw = 1000000;
                if (outletSku.get(i).getTMallGlobalStore_dsj() != null) {
                    if (outletSku.get(i).getTMallGlobalStore_dsj().contains("(")) {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_dsj().split("\\(")[0]);
                    } else {
                        hw = Double.parseDouble(outletSku.get(i).getTMallGlobalStore_dsj());
                    }
                }
//                coach = 1000000;
//                if (outletSku.get(i).getWebsite_dsj() != null) {
//                    if (outletSku.get(i).getWebsite_dsj().contains("(")) {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_dsj().split("\\(")[0]);
//                    } else {
//                        coach = Double.parseDouble(outletSku.get(i).getWebsite_dsj());
//                    }
//                }
//                outlet = 1000000;
//                if (outletSku.get(i).getOutlieFlagshipStore_dsj() != null) {
//                    if (outletSku.get(i).getOutlieFlagshipStore_dsj().contains("(")) {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_dsj().split("\\(")[0]);
//                    } else {
//                        outlet = Double.parseDouble(outletSku.get(i).getOutlieFlagshipStore_dsj());
//                    }
//                }
                if (sqhw < hw && sqhw < jd ) {
                    cell.setCellStyle(style);
                }

            } else {
                cell.setCellValue("/");
                cell.setCellStyle(currentStyle);
            }
            //官方到手价价差
            existingCell = row.getCell(19);
            if (existingCell == null) {
                existingCell = row.createCell(19);
            }
            currentStyle = existingCell.getCellStyle();
            row.createCell(19);
            cell = row.getCell(19);
            cell.setCellType(CellType.STRING);
            if (outletSku.get(i).getPricrDifference_dsj() == null || !outletSku.get(i).getPricrDifference_dsj().equals("Y")) {
                cell.setCellValue("N");
                cell.setCellStyle(currentStyle);
            } else {
                cell.setCellValue("Y");
                cell.setCellStyle(currentStyle);
            }

        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
            File outfile = new File(filename);
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            fileOutputStream.write(bos.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功！");
    }

}
