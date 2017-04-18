package com.test.controller;

import com.test.model.TestRMessage;
import com.test.model.TestSMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhuquanwen
 * @date: 2017/4/18
 * @desc: 消息控制器
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/test")
    @SendTo("/topic/getResponse")
    public TestSMessage say(TestRMessage msg){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new TestSMessage("hi," + msg.getMsg() + "this is my response");
    }
    @RequestMapping("/toWs")
    public ModelAndView toWs(){
        ModelAndView mav = new ModelAndView("ws");
        return mav;
    }

    @MessageMapping("/testChat")
    //@SendToUser("/queue/notifications")
    public void testChat(TestRMessage msg ){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return new TestSMessage("hi," + msg.getMsg() + "this is my response");
        TestSMessage tsm = new TestSMessage("hi," + msg.getMsg() + "this is my response");
        template.convertAndSendToUser(msg.getUserId(),"/message",tsm);

    }
    //点对点的方式
    @RequestMapping("/toChat")
    public ModelAndView toChat(){
        ModelAndView mav = new ModelAndView("chat");
        return mav;
    }
}
