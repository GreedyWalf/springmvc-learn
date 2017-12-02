package com.qs.mvc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;

@Service
public class PushService {
    private DeferredResult<String> deferredResult;

    //PushService中产生的DeferredResult给控制器使用，提供@Scheduled注解的付费更新DeferredResult
    public DeferredResult<String> getDeferredResult() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    //定时任务，5s更新一次
    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if (deferredResult != null) {
            deferredResult.setResult("当前时间：" + new Date().toLocaleString());
        }
    }
}
