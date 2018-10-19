package com.domor.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domor.model.ServerMessage;

@Controller
@RequestMapping("/webscoket")
public class UseWebscoket{
	
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "webscoket/index";
    }

    //客户端只要订阅了/topic/subscribeTest主题，调用这个方法即可
    @RequestMapping("/test")
    public void templateTest() {
    	System.out.println("webscoket test>>>>>>>>>>>>>>>>>>");
        messagingTemplate.convertAndSend("/topic/subscribeTest", new ServerMessage("服务器主动推的数据"));
    }
    
}