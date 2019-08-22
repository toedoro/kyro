package com.chowis.kyro.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chowis.kyro.service.DeviceService;

@Controller
public class WebController {

//	@RequestMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @RequestMapping("/logout-success")
//    public String logoutPage() {
//        return "login";
//    }

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String main(Map<String, Object> model) {
////        model.put("users", userServices.getAllUser());
//        return "users";
//    }

//    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
//    public String users(Map<String, Object> model) {
////        model.put("users", userServices.getAllUser());
//        return "users";
//    }

//    @RequestMapping(value = "/newuser")
//    public String addUser() {
//        return "addUser";
//    }

//    @RequestMapping(value = "/viewuser", method = RequestMethod.GET)
//    public String editUser(Map<String, Object> model) {
//
////        model.put("users", userServices.getAllUser());
////        model.put("devices", devicesServices.getAllDevices());
//
//        return "editUser";
//    }

	
	@Autowired
	private DeviceService deviceService;
	
//    @RequestMapping(value = "/devices", method = RequestMethod.GET)
//    public String devices(Map<String, Object> model) {
//        model.put("devices", deviceService.read());
//        
//        return "devices";
//    }
//
//    @RequestMapping(value = "/newdevices")
//    public String addDevices() {
//        return "addDevices";
//    }
//
//    @RequestMapping(value = "/viewdevices", method = RequestMethod.GET)
//    public String editDevice(Map<String, Object> model) {
////        model.put("users", userServices.getAllUser());
//        return "editDevices";
//    }
    
//    @RequestMapping(value = "/videoBackground", method = RequestMethod.GET)
//    public String contents(Map<String, Object> model) {
//        model.put("devices", deviceService.read());
//        
//        return "videoBackground";
//    }
//    
//    @RequestMapping(value = "/contents")
//    public String contents() {
//        return "contents";
//    }
    
    @RequestMapping(value = "/products")
    public String products() {
        return "products";
    }

//    @RequestMapping("/settings")
//    public String settings() {
//        return "settings";
//    }

//    @RequestMapping("/savesettings")
//    public String saveSettings(Profile theSetting) {
//        settingServices.saveSettings(theSetting);
//        return "settings";
//    }

}
