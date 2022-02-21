package cn.wn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.wn.mapper")
@SpringBootApplication
public class ElasticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticApplication.class, args);
    }
}
