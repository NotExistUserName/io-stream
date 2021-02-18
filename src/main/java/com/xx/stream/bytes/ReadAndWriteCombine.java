package com.xx.stream.bytes;

import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.IoUtils;
import com.xx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @discription 字节流-读写结合，实现复制功能
 * @author: xx
 * @date: 2021/2/17 11:40
 */
@Slf4j
public class ReadAndWriteCombine {

    /**
     * 待复制图片原地址
     */
    private static final String SOURCE_IMAGE_URL = System.getProperty("user.dir") + "\\src\\main\\resources\\学习.jpg";

    /**
     * 复制图片目标地址
     */
    private static final String TARGET_BAK_IMAGE_URL = System.getProperty("user.dir") + "\\src\\main\\resources\\学习_bak.jpg";


    /**
     * 文件备份
     * @param sourceImageUrl 待复制图片原地址
     * @param targetBakImageUrl 复制图片目标地址
     */
    public static void bakFile(String sourceImageUrl,String targetBakImageUrl) throws Exception{
        if (StringUtils.isEmpty(sourceImageUrl) || StringUtils.isEmpty(targetBakImageUrl)) {
            log.error("待复制图片原地址和复制图片目标地址均不能为空");
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceImageUrl);
            outputStream = new FileOutputStream(targetBakImageUrl);
            //定义暂存读取的数据长度
            int len = 0;
            //定义暂存读取的字节数据
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                //从0开始写写len个长度，写的数据为buf
                outputStream.write(buf,0,len);
            }
        } finally {
            //注意:流的关闭顺序是先打开的后关闭,特别是有关联关系的一定要遵守这个原则,否则将导致关闭流失败
            IoUtils.closeGracefully(outputStream);
            IoUtils.closeGracefully(inputStream);
        }
        log.info("文件复制完成..");
    }

    public static void main(String[] args) {
        try {
            bakFile(SOURCE_IMAGE_URL,TARGET_BAK_IMAGE_URL);
        } catch (Exception e) {
            log.error("文件复制失败",e);
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
    }
}
