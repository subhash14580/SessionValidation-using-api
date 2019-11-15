package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value="/")
public class SessionValidation {
	@Autowired
	SessionDAO sessionDAO;
	
	@RequestMapping("/sessions")
	@ResponseBody
	public List<SessionPO> getSessionPO() {
		return sessionDAO.findAll();
	}
	
	@PostMapping(value = "/addSession",produces = "application/json", consumes="application/json")
	@ResponseBody
	public Map<String,String>  saveSessionPO(@RequestBody  SessionPO session) {
		System.out.println(session.toString());
		Map<String,String> map = new HashMap<>();
		UUID uuid1 = Generators.timeBasedGenerator().generate();
		System.out.println("UUID : "+uuid1);
		try {
			session.setSessionId(uuid1.toString());
		 	sessionDAO.save(session);
		}catch(Exception e) {
			e.printStackTrace();
			map.put("STATUS", "FAILURE");
			return map;
		}
		map.put("STATUS", "SUCCESS");
		return map;
	
	}
	
	@RequestMapping(value ="/isValid")
	public String isValidSession(@RequestParam Integer userId , @RequestParam String sessionId) {
		System.out.println("userId--"+userId + " SessionId"+sessionId);
		if(userId != null) {
			SessionPO sessionPO = sessionDAO.findById(userId).get();
			if(sessionPO.getSessionId().equals(sessionId)) {
				return "SUCCESS";
			}
			
		}
		return "FAILURE";
	}
}
