package com.github.lyrric.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created on 2019-06-26.
 *
 * @author wangxiaodong
 */
public class IOUtil {

    /**
     * 下载文件
     * @param response
     * @param data
     */
    public static void download(HttpServletResponse response, byte[] data, String fileName){
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        try {
            response.addHeader("Content-disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件预览
     * @param response
     * @param data
     */
    public static void overview(HttpServletResponse response, byte[] data) {
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("utf-8");
        try (OutputStream os = response.getOutputStream();){
            os.write(data);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
