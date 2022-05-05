package com.cuicui.pgd.service.trd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan({"com.cuicui.pgd"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceTrdApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceTrdApplication.class, args);
    }
}
