package com.example.demo.controller;


import com.example.demo.util.MessageUtil;
import com.example.demo.util.WechatUtil;
import com.example.demo.util.WeixinUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class WechatController {



    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);


    /**
     * 确认请求来自微信服务器
     *
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        logger.info(signature + "+" + timestamp + "+" + nonce + "+" + echostr);
        String isTrue = "F";
        if (WechatUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
            isTrue = "T";
        }
        logger.info(isTrue);

        out.close();
        out = null;
    }

    /**
     * 处理微信服务器发来的消息
     */
    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(req);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            logger.info("FromUserName:"+fromUserName+",ToUserName:"+toUserName+",MsgType:"+msgType+",Content:"+content);

            String message = null;
            if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
                if("1".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
                }else if("2".equals(content)){
                    message = MessageUtil.initNewsMessage(toUserName, fromUserName);
                }else if("3".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
                }else if("?".equals(content) || "？".equals(content)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else if(content.startsWith("翻译")){
                    String word = content.replaceAll("^翻译", "").trim();
                    if("".equals(word)){
                        message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
                    }else{
                        message = MessageUtil.initText(toUserName, fromUserName, WeixinUtil.translate(word));
                    }
                }
            }else if(MessageUtil.MESSAGE_EVNET.equals(msgType)){
                String eventType = map.get("Event");
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
                    message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
                }else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
                    String url = map.get("EventKey");
                    message = MessageUtil.initText(toUserName, fromUserName, url);
                }else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
                    String key = map.get("EventKey");
                    message = MessageUtil.initText(toUserName, fromUserName, key);
                }
            }else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
                String label = map.get("Label");
                message = MessageUtil.initText(toUserName, fromUserName, label);
            }

            System.out.println("message:"+message);

            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }

}
