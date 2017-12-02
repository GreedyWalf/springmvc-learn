package com.qs.mvc.config;

import com.qs.mvc.Entity.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 继承AbstractHttpMessageConverter接口实现自定义的HttpMessageConverter
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    //新建一个自定义的媒体类型 application/x-wisely
    public MyMessageConverter(){
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    //重写readInternal方法，处理请求的数据
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));

        //只用‘-’分割
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz);
    }

    //确定如何输出数据到response
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage)
            throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + demoObj.getId() +"_" + demoObj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }
}
