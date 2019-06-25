package com.github.lyrric.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

}
