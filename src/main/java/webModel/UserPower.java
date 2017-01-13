package webModel;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.xuchengguo.personnel.entity.User;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,
proxyMode=ScopedProxyMode.TARGET_CLASS)//这里声明这个Bean的作用域为Session，用于保存当前会话用户的权限
public class UserPower {
	public UserPower(){
		
	}
	private User user;
	private  int power=-1;
	public  int getUserPower(){
		return power;
	}
	public void setUserPower(int power){
		this.power=power;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
