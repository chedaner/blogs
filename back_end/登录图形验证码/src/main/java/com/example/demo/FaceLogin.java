package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

@Controller
@RequestMapping("/code")
public class FaceLogin {

	@RequestMapping("/imgs")
	@ResponseBody
	public void authImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机大写字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		// 删除以前的
		session.removeAttribute("verCode");
		session.removeAttribute("codeTime");
		session.setAttribute("verCode", verifyCode.toLowerCase());
		session.setAttribute("codeTime", new Date());
		// 生成图片
		int w = 100, h = 30;
		OutputStream out = response.getOutputStream();
		VerifyCodeUtils.outputImage(w, h, out, verifyCode);
	}
}
