package webModel;

import javax.validation.constraints.NotNull;
//用于与前端交互的model
public class UserModel {
	@NotNull
	private String username;
	@NotNull
	private String password;
	public UserModel(){
		
	}
	public UserModel(String username,String password){
		this.password=password;
		this.username=username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
