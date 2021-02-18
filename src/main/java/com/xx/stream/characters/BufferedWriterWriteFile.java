package com.xx.stream.characters;

import com.xx.asserts.XXAssert;
import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.DateUtils;
import com.xx.utils.IoUtils;
import com.xx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;

/**
 * @discription 字符流-使用BufferedWriter写文件
 * @author: xx
 * @date: 2021/2/18 13:29
 */
@Slf4j
public class BufferedWriterWriteFile {
    /**
     * 文件写出地址
     */
    private static final String RESULT_FILE_URL = System.getProperty("user.dir") + "\\src\\main\\resources\\bufferedWriterWriteResult.txt";

    /**
     * 文件写出
     * @param resultFileUrl 目标文件地址
     * @param outputContent 需要写入文件的内容
     * @throws Exception
     */
    public static void writeFile(String resultFileUrl,String outputContent) throws Exception{
        XXAssert.isTrue(StringUtils.isNotEmpty(resultFileUrl) && StringUtils.isNotEmpty(outputContent));
        Writer fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(resultFileUrl);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(outputContent);
        } finally {
            //注意:流的关闭顺序是先打开的后关闭,特别是有关联关系的一定要遵守这个原则,否则将导致关闭流失败
            IoUtils.closeGracefully(bufferedWriter);
            IoUtils.closeGracefully(fileWriter);
        }
    }

    public static void main(String[] args) {
        String outputContent = "这是使用BufferedWriter写出文件例子,\r\n" + "完成时间为:" + DateUtils.dateToString(new Date(),DateUtils.SECOND_PRECISE);
        try {
            writeFile(RESULT_FILE_URL,outputContent);
        } catch (Exception e) {
            log.error("文件写出失败",e);
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
        log.info("文件写出成功...");
    }
}
