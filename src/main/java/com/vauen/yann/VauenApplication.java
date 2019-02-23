package com.vauen.yann;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;


@EnableScheduling//开启定时任务
@EnableAsync//开启异步调用方法
@SpringBootApplication
@MapperScan(basePackages = "com.vauen.yann.mapper")//扫描 mybatis mapper 包路径
@ComponentScan(basePackages= {"com.vauen.yann"})//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
public class VauenApplication {

	public static void main(String[] args) {
		SpringApplication.run(VauenApplication.class, args);
	}

}
