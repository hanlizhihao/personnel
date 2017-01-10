package webEntity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)//这里声明这个Bean的作用域为Session，用于保存当前会话用户的权限
public class UserPower {
	private UserPower(){
		
	}
	private  int power=-1;
	public  int getUserPower(){
		return power;
	}
	public void setUserPower(int power){
		this.power=power;
	}
}
