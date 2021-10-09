package hello.thymeleaf.basic;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Data;

@Controller
@RequestMapping("/basic")
public class BasicController {
	//기본
	@GetMapping("/text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data", "Hello Spring!");
		return "basic/text-basic";
	}
	
	//unescaped
	@GetMapping("/text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data", "Hello <b>Spring!</b>");
		return "basic/text-unescaped";
	}
	
	//객체사용
	@GetMapping("/variable")
	public String variable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);
		
		List<User> list = new ArrayList<>(); 
		list.add(userA);
		list.add(userB);
		
		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB); 
		
		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);
		
		return "basic/variable";
	}
	
	@Data
	static class User{
		
		private String username;
		private int age;
		
		public User(String username, int age) {
			this.username = username;
			this.age = age;
		}
		
	}
	
	//session사용하기
	@GetMapping("/basic-objects")
	public String textObject(HttpSession session) {
		
		session.setAttribute("sessionData", "Hello Session");
		
		return "basic/basic-objects";
	}
	
	@Component("helloBean")
	static class HelloBean{
		public String hello(String data) {
			return "Hello"+ data;
		}
	}
	
	//LocalDateTime사용하기
	@GetMapping("/date")
	public String date(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}
	
	//URL링크
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		
		return "basic/link";
	}
	
	//리터널
	@GetMapping("/literal")
	public String literal(Model model) {
		model.addAttribute("data", "Spring!");
		return "basic/literal";
	}
	
	
	
}