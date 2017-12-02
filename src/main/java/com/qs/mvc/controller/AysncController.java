package com.qs.mvc.controller;

import com.qs.mvc.service.PushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;

/**
 * 服务端数据推送：异步方法实现形式；
 */
@Controller
public class AysncController {
    @Resource
    private PushService pushService;


    @RequestMapping(value = "/defer", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        //PushService里产生的DeferredResult给控制器使用，提供@Scheduled注解的付费更新DeferredResult数据，返回到前端
        return pushService.getDeferredResult();
    }

}
