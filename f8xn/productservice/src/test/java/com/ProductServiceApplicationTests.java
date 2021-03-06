package com;

import com.utils.JdbcUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

//SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class)
//可指定我们SpringBoot工程的App启动类
@SpringBootTest(classes = ProductServiceApplication.class)
//由于是Web项目，Junit需要模拟ServletContext，
// 因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ProductServiceApplicationTests {
    //private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceApplicationTests.class);
    @Autowired
    private JdbcUtil jdbcUtil;
    @Test
    @Transactional
    @Rollback
    public void contextLoads() {
        //测试前，一定要注入Bean,不包括JdbcUtil
        String name = "tom";
        String password = "123";
        //String sql = "INSERT INTO t_user VALUES(2,?,?)";
        //String sql = "INSERT INTO t_user VALUES(2,\"tom\",\"123\")";
        //int i = jdbcUtil.executeUpdate2(sql,name,password);
        //int i = jdbcUtil.executeUpdate2(sql);
//        System.out.println("-----i:"+i);
        Object object = jdbcUtil.exectueQuery2("select * from t_user");
        logger.debug("--logger---test:"+object);
        System.out.println("---test:"+object);
//        Object object2 = it_userService.selectByPrimaryKey(1);
//        System.out.println("---test:"+object2.toString());
    }

}
