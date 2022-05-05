package com.cuicui.pgd.service.oss.service;

import java.io.InputStream;

public interface FileService {
//    阿里云oss文件上传
/*     @param inputStram 输入流
        @param module 文件夹名称
        @param originalFilename 原始文件名
        @return 文件在oss服务器上的url地址

* */
    String upload(InputStream inputStream,String modelu,String originalFilename);
//
//    ali云oss 文件删除
//    @param url 文件的url地址
    void removeFile(String url);
}
