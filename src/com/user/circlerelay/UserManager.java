package com.user.circlerelay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserManager {

	
	private static Map<String,Object> users = new HashMap<String,Object>();
	private static List<User> userList = new ArrayList<User>();
	
	public Map<String, Object> getUsers() {
		return users;
	}

	public void setUsers(Map<String, Object> users) {
		this.users = users;
	}

	public void add(User user){
		
		userList.add(user);
		users.put("users",userList);
	}

	public void printUsers() {
		// TODO Auto-generated method stub
		Set keys = users.keySet();
		for (Object key : keys) {
			System.out.println(users.get(key));
		}
		System.out.println(users);
	}
	
}
