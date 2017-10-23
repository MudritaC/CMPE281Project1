package org.CMPE281.filemanager.controller;


import java.util.List;

import org.CMPE281.filemanager.dal.UserDaoImpl;
import org.CMPE281.filemanager.model.UploadResponseVO;
import org.CMPE281.filemanager.model.User;
import org.CMPE281.filemanager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.CMPE281.filemanager.constant.FileManagerConstants.*;

@Controller
@RequestMapping(value="/User")
public class UserController {

	private UserService userService;
	UserDaoImpl userDao;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@ResponseBody
	@RequestMapping(value=USER_COUNT_URL, method=RequestMethod.GET)
	public int getUserCount(){
		return userDao.getUserCount();	
	}
	

	@RequestMapping(value=INSERT_USER_URL, method=RequestMethod.POST)
	public @ResponseBody UploadResponseVO insertUser(@RequestBody User user){
		return userService.insertUser(user);
	}

	@RequestMapping(value=LOGIN_USER_URL, method=RequestMethod.POST)
	public @ResponseBody User loginUser(@RequestBody User user){
		return userService.loginUser(user);
	}


	@ResponseBody
	@RequestMapping(value=UPDATE_USER_URL, method=RequestMethod.GET)
	public String updateUser(){
		User user = new User();
		user.setFirstname("testFirstUpdate");
		user.setLastname("testLast");
		user.setUserID("testID");
		return userDao.updateUser(user);	
	}
	
	@ResponseBody
	@RequestMapping(value=DELETE_USER_URL, method=RequestMethod.GET)
	public String deleteUser(){
	String usrId="2";
		return userDao.deleteUser(usrId);	
	}
	
	@ResponseBody
	@RequestMapping(value=VIEW_USER_URL, method=RequestMethod.GET)
	public List<User> getUsers(){
		return userDao.getUsers();	
	}
	
	@ResponseBody
	@RequestMapping(value=VIEW_USER_BY_ID_URL, method=RequestMethod.GET, produces="application/json")
	public User getUsersById(){
	String usrId="1";
		return userDao.getUsersById(usrId);	
	}


	@ResponseBody
	@RequestMapping(value=VERIFY_USER_BY_ID, method=RequestMethod.POST)
	public boolean getUsersById(@RequestBody User user){
		return userService.isUserPresent(user.getUserID());
	}

}
