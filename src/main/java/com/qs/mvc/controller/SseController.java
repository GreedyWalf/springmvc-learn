package com.qs.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRegistration;
import java.util.Random;

/**
 * 服务端推送技术：浏览器对SSE的支持
 *  前端使用js添加SSE客户端监听，获取服务器端推送的消息；（只兼容新版的浏览器）
 *
 */
@Controller
public class SseController {

    //注意：这里输出的媒体类型为"text/event-stream"，这是服务端SSE的支持，本例演示每隔5s向浏览器推送随机消息
    @RequestMapping(value = "/push", produces = "text/event-stream")
    @ResponseBody
    public String push() {
        Random random = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "data:Testing 1,2,3" + random.nextInt() + "\n\n";
    }
}
