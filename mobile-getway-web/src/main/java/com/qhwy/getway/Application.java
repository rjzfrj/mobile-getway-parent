package com.qhwy.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
//@EnableTransactionManagement 
//@MapperScan("com.demo.mapper")
public class Application {
//public class Application extends WebMvcConfigurerAdapter{
	
	
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
