package com.wanghaotian.example;

import org.elasticsearch.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/29
 * @modify By:
 */
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class,
        ElasticsearchTemplate.class, Client.class, ElasticsearchDataAutoConfiguration.class} )
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
