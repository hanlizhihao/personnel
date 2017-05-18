package initWeb;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import config.MyMvcConfig;
//Servlet3.0的新特性，不需要Web.xml就可以配置项目，通过实现WebApplicationInitializer接口
public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext arg0) throws ServletException {
		//Spring注解配置web应用程序上下文，
		//由它指定Spring的配置类
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		ctx.register(MyMvcConfig.class);
		ctx.setServletContext(arg0);
		//添加一个DispatcherServlet，由它去分发请求
		Dynamic servlet=arg0.addServlet("dispatcher",new DispatcherServlet(ctx));
		//这个Servlet所映射的请求范围,"/"所有请求
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
		servlet.setAsyncSupported(true);//开启Servlet异步方法的支持
		//添加一个Spring官方的Character过滤器
		//将前段传递过来的数据，编码方式转化为UTF-8，以解决中文乱码问题
		javax.servlet.FilterRegistration.Dynamic filter=arg0.addFilter("encodingFilter",CharacterEncodingFilter.class);
		filter.addMappingForUrlPatterns(null, false, "/*");
		filter.setInitParameter("encoding", "UTF-8");//解决中文乱码
	}
}
