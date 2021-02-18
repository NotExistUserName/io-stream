package com.xx.stream.bytes;

import com.xx.enums.RetCode;
import com.xx.exceptions.XXException;
import com.xx.utils.DateUtils;
import com.xx.utils.IoUtils;
import com.xx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @discription 字节流-使用Output写出文件
 * @author: xx
 * @date: 2021/2/17 11:39
 */
@Slf4j
public class OutputStreamWriteFile {

    /**
     * 文件写出地址
     */
    private static final String RESULT_FILE_URL = System.getProperty("user.dir") + "\\src\\main\\resources\\outputStreamWriteResult.txt";

    /**
     * 文件写出
     * @param resultFileUrl 目标文件地址
     * @param outputContent 需要写入文件的内容
     * @throws Exception
     */
    public static void writeFile(String resultFileUrl,String outputContent) throws Exception{
        //使用卫语句规范,若不满足条件则提前结束,使代码更加易读
        if (StringUtils.isEmpty(resultFileUrl) || StringUtils.isEmpty(outputContent)) {
            log.error("目标文件地址与输出内容均不能为空");
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(resultFileUrl);
            outputStream.write(outputContent.getBytes());
        } finally {
            IoUtils.closeGracefully(outputStream);
        }
    }

    public static void main(String[] args) {
        //\r\n表示换行符
        String outputContent = "这是outputStream需要输出的文件内容,\r\n完成时间为:" + DateUtils.dateToString(new Date(),DateUtils.SECOND_PRECISE);
        try {
            writeFile(RESULT_FILE_URL,outputContent);
        } catch (Exception e) {
            log.error("文件写出失败",e);
            throw new XXException(RetCode.ERROR.getCode(),RetCode.ERROR.getMsg());
        }
        log.info("文件写出成功...");
    }
}
