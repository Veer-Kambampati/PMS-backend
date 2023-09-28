package com.OPTSApplication.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.OPTSApplication.DB.DBConfig;
import com.OPTSApplication.Entity.ProjectEntity;
import com.OPTSApplication.Entity.UserEntity;



@Service
public class MainServiceImpl implements MainService {

	@Override
	public Map<String, Object> saveNewUser(UserEntity user) {
		Map<String, Object> resultMap = new HashMap<>();
		boolean isRegisterSuccessfull = false;
		String insertUserEntityQuery = "INSERT into ProjectManagementSystem.user"
				+ " (user_name,password,phone_number,email,user_role)"
				+ " values (?,?,?,?,?)";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(insertUserEntityQuery);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getPhoneNumber());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getUserRole());
			statement.execute();
			isRegisterSuccessfull = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("isRegisterSuccessfull", isRegisterSuccessfull);
		return resultMap;
	}

	@Override
	public Map<String, Object> checkUserLogin(UserEntity user) {
		Map<String, Object> resultMap = new HashMap();
		boolean isValidUserEntity = false;
		String query = "SELECT * from ProjectManagementSystem.user where user_name = ? and password = ?";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				resultMap.put("userId", rs.getInt("user_id"));
				resultMap.put("userRole", rs.getString("user_role"));
				isValidUserEntity = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("isValidUser", isValidUserEntity);
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> getAllProjects() {
		List<Map<String, Object>> resultMapList = new ArrayList();
		String query = "SELECT * from ProjectManagementSystem.project";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Map<String, Object> resultMap = new HashMap();
				resultMap.put("userId", rs.getInt("user_id"));
				resultMap.put("projectId", rs.getInt("project_id"));
				resultMap.put("projectName", rs.getString("project_name"));
				resultMap.put("description", rs.getString("description"));
				resultMap.put("url", rs.getString("url"));
				resultMap.put("license", rs.getString("license"));
				resultMap.put("state", rs.getString("state"));
				resultMap.put("lastUpdatedTime",  rs.getString("last_updated"));
				resultMapList.add(resultMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMapList;
	}

	@Override
	public Map<String, Object> addNewProject(ProjectEntity project) {
		Map<String, Object> resultMap = new HashMap<>();
		String query = "INSERT into ProjectManagementSystem.project"
				+ " (user_id,project_name,license,url,description,state,last_updated)"
				+ " values (?,?,?,?,?,?,?)";
		Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastUpdatedTime = formatter.format(ts);
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			statement.setInt(1, project.getUserId());
			statement.setString(2, project.getProjectName());
			statement.setString(3, project.getLicense());
			statement.setString(4, project.getUrl());
			statement.setString(5, project.getDescription());
			statement.setString(6, project.getState());
			statement.setString(7, lastUpdatedTime);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("projectDetail", project);
		return resultMap;
	}

	@Override
	public Map<String, Object> cancelProject(int id) {
		Map<String, Object> resultMap = new HashMap();
		boolean isDeleted = false;
		String query = "delete from ProjectManagementSystem.project where project_id = ? ";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
			isDeleted = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("isDeleted", isDeleted);
		return resultMap;
	}

	@Override
	public Map<String, Object> updateProjectStatus(ProjectEntity project) {
		
		Map<String, Object> resultMap = new HashMap();
		Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastUpdatedTime = formatter.format(ts);
		String query = "Update ProjectManagementSystem.project set state=?,last_updated=? where project_id=? ";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			statement.setString(1, project.getState());
			statement.setString(2, lastUpdatedTime);
			statement.setInt(3, project.getProjectId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("projectDetail", project);
		return resultMap;
	}

	

	@Override
	public Map<String, Object> updateProject(ProjectEntity project) {
		Map<String, Object> resultMap = new HashMap();
		boolean isUpdated = false;
		Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastUpdatedTime = formatter.format(ts);
		String query = "Update ProjectManagementSystem.project set project_name=?,license=?,url=?,description=?,"
				        + "state=?,last_updated=? where project_id=? ";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			statement.setString(1, project.getProjectName());
			statement.setString(2, project.getLicense());
			statement.setString(3, project.getUrl());
			statement.setString(4, project.getDescription());
			statement.setString(5, project.getState());
			statement.setString(6, lastUpdatedTime);
			statement.setInt(7, project.getProjectId());
			statement.executeUpdate();
			isUpdated= true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("isUpdated", isUpdated);
		return resultMap;
	}

	@Override
	public Map<String, Object> getProject(int projectId) {
		Map<String, Object> resultMap = new HashMap();
		String query = "SELECT * from ProjectManagementSystem.project where project_id=?";
		try {
			PreparedStatement statement = DBConfig.createNewconnection().prepareStatement(query);
			statement.setInt(1, projectId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				resultMap.put("userId", rs.getInt("user_id"));
				resultMap.put("projectId", rs.getInt("project_id"));
				resultMap.put("projectName", rs.getString("project_name"));
				resultMap.put("description", rs.getString("description"));
				resultMap.put("url", rs.getString("url"));
				resultMap.put("license", rs.getString("license"));
				resultMap.put("state", rs.getString("state"));
				resultMap.put("lastUpdatedTime",  rs.getString("last_updated"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	
	
	

}
