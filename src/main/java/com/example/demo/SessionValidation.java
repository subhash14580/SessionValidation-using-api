package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	
public class SessionValidation {
	@RequestMapping("/sessions")
	public SessionPO getSessionPO() {
		SessionPO sessionPo = new SessionPO();
		sessionPo.setSessionId("123456");
		return sessionPo;
	}
}
