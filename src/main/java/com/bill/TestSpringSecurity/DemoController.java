package com.bill.TestSpringSecurity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {
	
	@GetMapping("/admin/hello")
    public Map<String, String> adminSayHello() {
		Map<String, String> result = new HashMap();
	    result.put("message", "admin say hello");
        return result;
    }
	
    @GetMapping("/user/hello")
    public Map<String, String> userSayHello() {
    	Map<String, String> result = new HashMap();
	    result.put("message", "user say hello");
        return result;
    }
}
