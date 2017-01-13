package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import service.LoginService;
import webController.LoginController;
import webModel.UserPower;
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy//开启切面自动代理
@ComponentScan(basePackageClasses={MyMvcConfig.class,LoginService.class,LoginController.class,UserPower.class})
public class MyMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	  private ApplicationContext applicationContext;

	  public void setApplicationContext(ApplicationContext applicationContext) {
	    this.applicationContext = applicationContext;
	  }
	  @Bean
	  public ViewResolver viewResolver() {
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	    resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding("UTF-8");
	    return resolver;
	  }
	  @Bean
	  public TemplateEngine templateEngine() {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setEnableSpringELCompiler(true);
	    engine.setTemplateResolver(templateResolver());
	    return engine;
	  }
	@Bean
	public ITemplateResolver templateResolver(){
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("/WEB-INF/classes/views/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver m=new CommonsMultipartResolver();
		return m;
	}
	//需要特别注意的是：/css/**的意思是这个文件夹下的文件为静态资源，它下面的文件夹不是静态资源，所以要单独的进行设置
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("css/**").addResourceLocations("classpath:/css/");
		registry.addResourceHandler("js/**").addResourceLocations("classpath:/js/");
		registry.addResourceHandler("img/**").addResourceLocations("classpath:/img/");
		registry.addResourceHandler("lib/**").addResourceLocations("classpath:/lib/");
		registry.addResourceHandler("static/**").addResourceLocations("classpath:/static/");
	}
	/**
	 * 对于/index访问参数视图解析器返回逻辑名称为index的视图用于显示
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/index").setViewName("/login");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}
}
