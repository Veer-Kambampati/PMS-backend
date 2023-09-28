package com.OPTSApplication.Service;

import java.util.List;
import java.util.Map;

import com.OPTSApplication.Entity.ProjectEntity;
import com.OPTSApplication.Entity.UserEntity;

public interface MainService {

	Map<String, Object> saveNewUser(UserEntity user);

	Map<String, Object> checkUserLogin(UserEntity user);

	List<Map<String, Object>> getAllProjects();

	Map<String, Object> addNewProject(ProjectEntity project);

	Map<String, Object> cancelProject(int id);

	Map<String, Object> updateProject(ProjectEntity project);
	
	Map<String, Object> getProject(int projectId);

	Map<String, Object> updateProjectStatus(ProjectEntity project);

}
