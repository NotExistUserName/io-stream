package com.xx.stream.bytes;

import com.xx.asserts.XXAssert;
import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.IoUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @discription 字节流-使用InputStream读取文件
 * @author: xx
 * @date: 2021/2/17 11:39
 */
@Slf4j
public class InputStreamReadFile {

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
        //断言读取的目标目录不能为空
        XXAssert.notEmpty(targetFileUrl);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(targetFileUrl);
            //定义缓存数组，用于暂存字节数据，每次都是存储最新读取的数据；溢出部分会变成乱码，因为所剩余字节数不足以构建一个标准字符；1024也可使用 inputStream.available()
            byte[] buf = new byte[1024];
            while (inputStream.read(buf) != -1) {
                log.info("读取的字节数组为-->{}",buf);
                log.info("读取明文字符串结果为-->{}", new String(buf));
            }
        } finally {
            //利用java多态特性,将子类对象赋值给父类的引用类型变量，运行时表现为子类的属性与行为特征
            IoUtils.closeGracefully(inputStream);
        }
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
