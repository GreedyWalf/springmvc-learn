package base;

import com.qs.mvc.base.config.RootConfig;
import com.qs.mvc.base.config.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@WebAppConfiguration("classpath*:views")
public class BaseTest {

}
