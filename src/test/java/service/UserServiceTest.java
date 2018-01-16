package service;



import base.BaseTest;
import com.qs.mvc.entity.user.User;
import com.qs.mvc.service.HibernateBaseService;
import com.qs.mvc.util.UUIDGenerator;
import org.junit.Test;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

public class UserServiceTest extends BaseTest {
    @Resource
    private HibernateBaseService baseService;


    @Test
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
    public void testSaveOrUpdate() {
        User user = new User();
        String userId = UUIDGenerator.uuid();
        user.setId(userId);
        user.setCreateBy(userId);
        user.setCreateTime(new Date());

        //此时的user对象是瞬时状态（Transient）
        System.out.println(user.getId() + " in transient status");
        baseService.save(user);

        //此时的user对象是脱管的（Detached）
        System.out.println(user.getId() + " in detached status");
    }

}
