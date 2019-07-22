package com.example.demo.controller;


import com.example.demo.models.User;
import com.example.demo.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by ram on 26/02/16.
 */
@Controller
public class TestController {

    @Autowired
    private UserDao _userDao;

    @RequestMapping(value = "/createuser", method = RequestMethod.POST)
    public ModelAndView createUser(HttpServletRequest request,
																	 HttpServletResponse response,
																	 @RequestParam String name,
																	 @RequestParam String email) {
        try {
            // create new user object
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setTimestamp(new Date().getTime());

            // save user in db (if new)
            if (_userDao.getByEmail(email) == null) {
                _userDao.save(user);
            }

            // save in cookie
            Cookie cookie = new Cookie("name", name);
            Cookie cookie1 = new Cookie("email", email);
            response.addCookie(cookie);
            response.addCookie(cookie1);
        } catch (Exception e) {
           e.printStackTrace();
        }

        return new ModelAndView("redirect:/");
    }


    @ResponseBody
    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        try {
            return _userDao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
