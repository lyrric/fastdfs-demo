package com.github.lyrric.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Created on 2019-06-25.
 *
 * @author wangxiaodong
 */
@Component
public class FastdfsUtil {

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 上传文件
     * @param data
     * @param fileSize
     * @param extension
     * @return
     */
    public String upload(byte[] data, long fileSize, String extension){
        InputStream is = new ByteArrayInputStream(data);
        StorePath path = fastFileStorageClient.uploadFile(is, fileSize, extension, null);
        return path.getFullPath();
    }

    /**
     * 下载文件，获取字节主句
     * @param path
     */
    public byte[] download(String path){
        String groupName  =path.split("/")[0];
        path = path.substring(groupName.length()+1);
        return fastFileStorageClient.downloadFile(groupName, path, is->{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int n;
            while(-1 != (n = is.read(buffer))){
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        });

    }

}
