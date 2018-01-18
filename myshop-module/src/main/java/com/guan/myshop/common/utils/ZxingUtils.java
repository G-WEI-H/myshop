package com.guan.myshop.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.validation.constraints.Max;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guan
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @date ${date} ${time}
 */
public class ZxingUtils {
    public static void  getEncodeImage() throws WriterException, IOException {
        String filePath = "D://";
        String fileName = "guan.png";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(
                "guan",
                "https://www.baidu.com");
        String content = jsonObject.toString();//设置内容能
        int width = 200; // 图像宽度
        int height = 200; // 图像高度
        String format="png";
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        BitMatrix encode = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        MatrixToImageWriter.writeToPath(encode,format,path);
        System.out.println("输出成功");
    }
}
