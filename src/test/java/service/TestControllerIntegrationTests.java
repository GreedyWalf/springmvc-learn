package service;


import com.qs.mvc.config.MyMvcConfig;
import com.qs.mvc.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//导入静态付费

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
//生命加载的ApplicationContext是一个WebApplicationContext，属性指定的Web资源的位置，默认为src/main/webapp,本例修改为src/main/resources
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {

    private MockMvc mockMvc;

    @Resource
    private DemoService demoService;

    @Resource
    private WebApplicationContext context;

    //可注入http session
    @Resource
    private MockHttpSession session;

    //可注入http request
    @Resource
    private MockHttpServletRequest request;

    @Before
    public void setup() {
        //MockMvc-模拟Mvc对象，提供下面方式初始化
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testNormalController() throws Exception {
        //模拟发出get请求
        mockMvc.perform(get("/normal"))
                //预期控制返回的状态
                .andExpect(status().isOk())
                //预期使用的view为page页面
                .andExpect(view().name("page"));
    }

    @Test
    public void testRestController() throws Exception{
        //get请求
        mockMvc.perform(get("/rest/testRest"))
                .andExpect(status().isOk())
                //设置预期返回的媒体类型
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                //预期返回值的内容
                .andExpect(MockMvcResultMatchers.content().string(demoService.saySomething()));
    }
}
