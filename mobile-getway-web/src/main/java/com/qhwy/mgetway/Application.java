package com.qhwy.mgetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableTransactionManagement 
//@MapperScan("com.qhwy.mgetway")
//public class Application {
public class Application extends SpringBootServletInitializer{

	 /** 
    * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行 
    */  
   @Override  
   protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
       builder.sources(this.getClass());  
       return super.configure(builder);  
   }  
//public class Application extends WebMvcConfigurerAdapter{
//	
//	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
//		FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
//		FastJsonConfig fastJsonConfig=new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//		fastConverter.setFastJsonConfig(fastJsonConfig);
//		converters.add(fastConverter);
//		
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
