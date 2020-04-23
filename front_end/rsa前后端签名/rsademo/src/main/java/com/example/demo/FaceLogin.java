package com.example.demo;


import com.example.demo.util.SignverifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/faceLogin")
public class FaceLogin {

	private static final Logger logger = LoggerFactory.getLogger(FaceLogin.class);

	@RequestMapping("/searchFaceTest")
	@ResponseBody
	public String searchFaceTest(@RequestBody String params) {
		System.out.println("backEnd_requestParams="+params);
		boolean verifyResult = SignverifyUtil.getSignVeryfy(params);
		System.out.println(verifyResult);
		return null;
	}
}
