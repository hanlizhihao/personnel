package webModel;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.xuchengguo.personnel.entity.User;
//当前会话（Session）的用户的权限信息
//UserPower的生命周期与Session的生命周期一致，每个会话都会建立一个UserPower这个对象，记录正在访问这个服务器的用户的权限信息
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION,
proxyMode=ScopedProxyMode.TARGET_CLASS)//把一个不是单例的对象注入到一个单例对象中
public class UserPower {
	public UserPower(){
	}
	private User user;
	private  int power=-1;//初始值为-1，证明没有登录
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
