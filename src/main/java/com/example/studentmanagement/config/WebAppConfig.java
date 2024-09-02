package com.example.studentmanagement.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages = "com.example.studentmanagement")
@Configuration
@EnableWebMvc
public class WebAppConfig {

}
