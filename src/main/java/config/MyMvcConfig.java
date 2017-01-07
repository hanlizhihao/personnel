package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={MyMvcConfig.class})
public class MyMvcConfig extends WebMvcConfigurerAdapter{
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
		ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
	@Bean
	public TemplateEngine templateEngine(TemplateResolver templateResolver){
		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
	@Bean
	public TemplateResolver templateResolver(){
		TemplateResolver templateResolver=new TemplateResolver();
		templateResolver.setPrefix("/WEB-INF/classes/views");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver m=new CommonsMultipartResolver();
		return m;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/image/**").addResourceLocations("classpath:/image/");
	}
	/**
	 * 对于/index访问参数视图解析器返回逻辑名称为index的视图用于显示
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}
}
