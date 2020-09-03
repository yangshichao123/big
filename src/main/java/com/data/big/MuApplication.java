package com.data.big;

import com.data.big.netty.nettyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.unit.DataSize;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;
//打成war包   extends SpringBootServletInitializer
@SpringBootApplication
@MapperScan("com.data.big.mapper")
@PropertySource(value = "classpath:application.yml")
@EnableScheduling
public class MuApplication  {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MuApplication.class, args);

        new nettyThread(run);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxRequestSize(DataSize.parse("3500MB"));
        factory.setMaxFileSize(DataSize.parse("3500MB"));
        return factory.createMultipartConfig();
    }
   //打成war包需要增加得内容
  /*  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MuApplication.class);
    }*/



}
