package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.xuchengguo.personnel.dao.IntroductionDAO;

import service.LoginService;
import webController.LoginController;
import webModel.UserPower;
//意思是这个类作为Spring的配置类
@Configuration
@EnableWebMvc//只有在@Configuration下才可以进行配置，@EnableMvc开启SpringMVC的支持
//组件扫描，basePackageClasses指定Spring扫描类所在包的所有的类
@ComponentScan(basePackageClasses={MyMvcConfig.class,LoginService.class,LoginController.class,UserPower.class,
		IntroductionDAO.class})
public class MyMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	  private ApplicationContext applicationContext;

	  public void setApplicationContext(ApplicationContext applicationContext) {
	    this.applicationContext = applicationContext;
	  }
	  //视图解析器
	  @Bean
	  public ViewResolver viewResolver() {
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	    resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding("UTF-8");
	    return resolver;
	  }
	  //视图解析器
	  @Bean
	  public TemplateEngine templateEngine() {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setEnableSpringELCompiler(true);
	    engine.setTemplateResolver(templateResolver());
	    return engine;
	  }
	  //视图解析器
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
		//html页面请求的资源的url，将对应服务器的存储地址
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
	}
}
