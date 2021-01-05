package com.sumit.shingari.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sumit.shingari.user.VO.Department;
import com.sumit.shingari.user.VO.ResponseTemplateVO;
import com.sumit.shingari.user.entity.User;
import com.sumit.shingari.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	private final UserRepository userRepository;
	private final RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("Inside saveUser method of User Service!");
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("Inside getUserWithDepartment method of User Service!");
		ResponseTemplateVO response = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		
		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
		
		response.setUser(user);
		response.setDepartment(department);
		return response;
	}

}
