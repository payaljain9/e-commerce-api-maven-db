package org.jain.user.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jain.common.DBUtils;
import org.jain.user.dto.UserRequest;
import org.jain.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserResponse response;
	
	ResultSet rs;
	
	public UserResponse saveUser(UserRequest request)
	{
		try
		{
			DBUtils.executeDMLQuery("insert into user values("+request.getId()+", '"+request.getName()+"', '"+request.getEmail()+"', '"+request.getPassword()+"')");
			
			response.setId(request.getId());
			response.setName(request.getName());
			response.setEmail(request.getEmail());
			response.setPassword(request.getPassword());
			
			response.setResponseCode("0000");
			response.setResponseMessage("User added successfully..!");
		}
		catch(Exception e)
		{
			response.setResponseCode("911");
			response.setResponseMessage("User add failed..!");
		}
		return response;
	}
	
	
	public UserResponse searchUser(int id)
	{
		rs=DBUtils.executeSelectQuery("select * from user where ID="+id+"");
		try
		{
			if(rs.next())
			{
				response.setId(rs.getInt("ID"));
				response.setName(rs.getString("Name"));
				response.setEmail(rs.getString("Email"));
				response.setPassword(rs.getString("Password"));
				
				response.setResponseMessage("User Records found..!");
				response.setResponseCode("0000");
			}
			else
			{
				response.setId(0);
				response.setName("");
				response.setEmail("");
				response.setPassword("");
				response.setResponseMessage("User Records not found..!");
				response.setResponseCode("911");
			}
		}
		catch(Exception e)
		{
			response.setResponseCode("911");
			response.setResponseMessage("User add failed..!");
		}
		return response;
	}
	
	
	//function that returns all users in List<UserResponse>
	public List<UserResponse> getAllUsers()
	{
		List<UserResponse> userListResponse=new ArrayList<>();
		
		rs=DBUtils.executeSelectQuery("select * from user");
		try 
		{
			while(rs.next())
			{
				UserResponse userResponse=new UserResponse();
				userResponse.setId(rs.getInt("ID"));
				userResponse.setName(rs.getString("Name"));
				userResponse.setEmail(rs.getString("Email"));
				userResponse.setPassword(rs.getString("Password"));
				
				userResponse.setResponseMessage("User Records found..!");
				userResponse.setResponseCode("0000");
				userListResponse.add(userResponse);
			}
		} 
		catch (SQLException e)
		{
			UserResponse userResponse=new UserResponse();
			userResponse.setResponseCode("911");
			userResponse.setResponseMessage("User add failed..!");
			
			userListResponse.add(userResponse);
		}
		
		return userListResponse;
	}
}
