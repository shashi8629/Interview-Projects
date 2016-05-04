package com.crunchify.controller;
 
import java.util.UUID;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CrunchifyHelloWorld {
 
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		System.out.println(" in model");
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}
	
	
	@RequestMapping(value="/deposit", method=RequestMethod.GET)
	public ModelAndView depositMoney(){
		System.out.println(" in deposit  get");
		ModelAndView model=new ModelAndView("deposit");
		model.addObject("account1", new Account());
		return model;
		
	}
	
	@RequestMapping(value="/deposit",method=RequestMethod.POST)
	public ModelAndView depositSuccess(@Valid@ModelAttribute("account1") Account account,BindingResult result){
		System.out.println(" in deposit  post");
		if(result.hasErrors()){
			ModelAndView model=new ModelAndView("deposit");
			return model;
		}
		ModelAndView model=new ModelAndView("success");
		model.addObject("msg","Amount"+account.getAmount()+"has been deposited into account"+account.getAccountNo());
		return model;
	}
	
	
	@RequestMapping(value="/withdraw", method=RequestMethod.GET)
	public ModelAndView withdrawMoney(){
		ModelAndView model=new ModelAndView("withdraw");
		return model;
		
	}
	
	
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public ModelAndView withdrawSuccess(@Valid@ModelAttribute("account1") Account account,BindingResult result){
		if(result.hasErrors()){
			ModelAndView model=new ModelAndView("withdraw");
			return model;
		}
		ModelAndView model=new ModelAndView("success");
		model.addObject("msg","Amount"+account.getAmount()+"has been withdrawn from account"+account.getAccountNo());
		return model;
	}
	
	@RequestMapping(value="/newAccount", method=RequestMethod.POST)
	public ModelAndView createAccount(){
		ModelAndView model=new ModelAndView("newAccount");
		return model;
		
	}
	
	
	
	@RequestMapping(value="/newAccount",method=RequestMethod.POST)
	public ModelAndView newAccountSuccess(@Valid@ModelAttribute("account1") Account account,BindingResult result){
		if(result.hasErrors()){
			ModelAndView model=new ModelAndView("withdraw");
			return model;
		}
		ModelAndView model=new ModelAndView("success");
		model.addObject("msg","Account"+UUID.randomUUID().toString()+"is created");
		return model;
	}
	
	
	
	@RequestMapping(value="/closeAccount", method=RequestMethod.GET)
	public ModelAndView closeAccount(){
		ModelAndView model=new ModelAndView("closeAccount");
		return model;
		
	}
	
	
	@RequestMapping(value="/closeAccount",method=RequestMethod.POST)
	public ModelAndView closeAccSuccess(@Valid@ModelAttribute("account1") Account account,BindingResult result){
		if(result.hasErrors()){
			ModelAndView model=new ModelAndView("closeAccount");
			return model;
		}
		ModelAndView model=new ModelAndView("success");
		model.addObject("msg","Account Number"+account.getAccountNo()+"is closed");
		return model;
	}

	
	
	
}