package com.xx.stream.characters;

import com.xx.asserts.XXAssert;
import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.IoUtils;
import com.xx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
 * @discription 字符流-使用 BufferedReader读取文件
 * @author: xx
 * @date: 2021/2/18 13:27
 */
@Slf4j
public class BufferedReaderReadFile {

    /**
     * 读取目标文件地址 System.getProperty("user.dir")可以获取到当前项目的目录
     */
    private static final String TARGET_FILE_URL = System.getProperty("user.dir") + "\\src\\main\\resources\\forInputStreamRead.txt";

    /**
     * 具体读取文件的方法
     * @param targetFileUrl
     * @throws Exception
     */
    public static void readFile(String targetFileUrl) throws Exception{
        XXAssert.notEmpty(targetFileUrl);
        Reader fileReader = null;
        BufferedReader bufferedReader = null;
        //存储所有读取到的数据
        StringBuilder readContent = new StringBuilder();
        try {
            //通过FileReader构造BufferedReader，将FileInputStream(字节流)转成字符流
            fileReader = new FileReader(targetFileUrl);
            bufferedReader = new BufferedReader(fileReader);
            //读取数据临时存放变量
            String tempContent = "";
            //重点角色bufferedReader.readLine
            while (StringUtils.isNotEmpty(tempContent = bufferedReader.readLine())) {
                readContent.append(tempContent).append("\r\n");
            }
        } finally {
            //注意:流的关闭顺序是先打开的后关闭,特别是有关联关系的一定要遵守这个原则,否则将导致关闭流失败
            IoUtils.closeGracefully(bufferedReader);
            IoUtils.closeGracefully(fileReader);
        }
        log.info("读取到的内容为-->{}",readContent.toString());
    }

    public static void main(String[] args) {
        try {
            readFile(TARGET_FILE_URL);
        } catch (Exception e) {
            log.error("读取文件失败",e);
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
    }
}
