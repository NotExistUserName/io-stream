package com.xx.stream.characters;

import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.IoUtils;
import com.xx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @discription 字符流-文件复制
 * @author: xx
 * @date: 2021/2/18 13:30
 */
@Slf4j
public class CharReadAndWriteCombine {

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
        Reader fileReader = null;
        Writer fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileReader = new FileReader(sourceImageUrl);
            fileWriter = new FileWriter(targetBakImageUrl);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            //定义暂存读取的数据长度
            int len = 0;
            //定义暂存读取的字符数据
            char[] buf = new char[1024];
            while ((len = bufferedReader.read(buf)) != -1) {
                //从0开始写写len个长度，写的数据为buf
                bufferedWriter.write(buf,0,len);
            }
        } finally {
            //注意:流的关闭顺序是先打开的后关闭,特别是有关联关系的一定要遵守这个原则,否则将导致关闭流失败
            IoUtils.closeGracefully(bufferedWriter);
            IoUtils.closeGracefully(fileWriter);
            IoUtils.closeGracefully(bufferedReader);
            IoUtils.closeGracefully(fileReader);
        }
        log.info("文件复制完成..");
    }

    /**
     * 通过字符流生成的图片会发现无法打开
     * 这是因为解码与编码的问题，因为图片是以字节数据存储的，而字符流读取数据后是以字符数据进行存储的，
     * 而字符与字节之间是需要编码与解码的，而字节流就是以字节读以字节存储的，所以字节流能够正常复制图片，
     * 而字符流复制的图片无法打开，所以非纯文本不要使用字符流，
     * 字符流最好用的就是BufferedReader的readLine方法
     * @param args
     */
    public static void main(String[] args) {
        try {
            bakFile(SOURCE_IMAGE_URL,TARGET_BAK_IMAGE_URL);
        } catch (Exception e) {
            log.error("文件复制失败",e);
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
    }

}
