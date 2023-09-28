package com.OPTSApplication.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.OPTSApplication.Entity.ProjectEntity;
import com.OPTSApplication.Entity.UserEntity;
import com.OPTSApplication.Service.MainService;




@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/projectManagement")
public class MainController {
	
	private MainService service;
	
	@Autowired
	public MainController(MainService service) {
		this.service = service;
	}
	
	@PostMapping(value="/addNewUser")
	ResponseEntity<Map> saveNewUser(@RequestBody UserEntity user){
		return ResponseEntity.status(HttpStatus.OK).body(service.saveNewUser(user));
	}

	@PostMapping(value = "/checkLogin")
	ResponseEntity<Map> checkUserLogin(@RequestBody UserEntity user){
		return ResponseEntity.status(HttpStatus.OK).body(service.checkUserLogin(user));
	}
	
	@GetMapping(value = "/allProject")
	ResponseEntity<List<Map<String, Object>>> getAllProjectDetails(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllProjects());
	}
	
	@PostMapping(value="/newProject")
	ResponseEntity<Map> addNewProject(@RequestBody ProjectEntity project){
		return ResponseEntity.status(HttpStatus.OK).body(service.addNewProject(project));
	}
	
	@DeleteMapping(value="/cancelProject/{id}")
	ResponseEntity<Map> cancelProject(@PathVariable(name = "id",required = true) int id){
		return ResponseEntity.status(HttpStatus.OK).body(service.cancelProject(id));
	}
	
	@PutMapping(value="/updateStatus")
	ResponseEntity<Map> updateProjectStatus(@RequestBody ProjectEntity project){
		return ResponseEntity.status(HttpStatus.OK).body(service.updateProjectStatus(project));
	}
	
	@PutMapping(value="/updateProject")
	ResponseEntity<Map> updateProject(@RequestBody ProjectEntity project){
		return ResponseEntity.status(HttpStatus.OK).body(service.updateProject(project));
	}
	
	@GetMapping(value="/projectDetail/{projectId}")
	ResponseEntity<Map> getProject(@PathVariable(name="projectId",required=true)int projectId){
		return ResponseEntity.status(HttpStatus.OK).body(service.getProject(projectId));
	}
	
}
