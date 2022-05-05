package com.cuicui.pgd.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.cuicui.pgd.service.oss.service.FileService;
import com.cuicui.pgd.service.oss.utlis.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String modelu, String originalFilename) {
//        读取配置信息
        String endpoint =ossProperties.getEndpoint();
        String keyid=ossProperties.getKeyid();
        String keysecret=ossProperties.getKeysecret();
        String bucketname= ossProperties.getBucketname();

//        创建OSSClient实例
        OSS ossClient=new OSSClientBuilder().build(endpoint,keyid,keysecret);
        if(!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }

//       构建objectName：文件路径 avatar/default.jpg
        String folder=new DateTime().toString("yyyy/MM/dd");
        String fileName= UUID.randomUUID().toString();
        String fileExtension= originalFilename.substring(originalFilename.lastIndexOf("."));
        String key= modelu+"/"+folder+fileName+fileExtension;
//        上传文件流
        ossClient.putObject(bucketname,key,inputStream);
//        关闭文件流
        ossClient.shutdown();

//        返回ulr

        return "https://"+bucketname+"."+endpoint+"/"+key;



    }

    @Override
    public void removeFile(String url) {
        //        读取配置信息
        String endpoint =ossProperties.getEndpoint();
        String keyid=ossProperties.getKeyid();
        String keysecret=ossProperties.getKeysecret();
        String bucketname= ossProperties.getBucketname();

//        创建OSSClient实例
        OSS ossClient=new OSSClientBuilder().build(endpoint,keyid,keysecret);

//        删除文件
//        https://project-gd-file.oss-cn-beijing.aliyuncs.com
//        /avatar/2022/04/11047df9b8-9efd-4026-9bb4-0730a0c7abc2.jpg
        String host = "https://" + bucketname + "." + endpoint + "/";
        String objectName = url.substring(host.length());
        ossClient.deleteObject(bucketname, objectName);
//关闭OSSClient
        ossClient.shutdown();
    }
}
