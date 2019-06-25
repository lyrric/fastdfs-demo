package com.github.lyrric.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "fastdfs_file")
@Getter
@Setter
public class FastdfsFile extends BaseEntity {
    /**
     * 上传时的文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * fastdfs路径
     */
    private String path;

    /**
     * 上传时间
     */
    @Column(name = "upload_time")
    private Date uploadTime;

    /**
     * 是否删除，true=已删除
     */
    private Boolean deleted;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;



}