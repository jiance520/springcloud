package com;

import com.utils.JdbcUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaTest {
    private static final Logger logger = LoggerFactory.getLogger(EurekaTest.class);
    @Autowired
    private JdbcUtil jdbcUtil;
    @Test
    public void contextLoads() {
//        Object object = JdbcUtil.exectueQuery("select * from dog");
//        System.out.println("-----object:");
        Object object = jdbcUtil.exectueQuery("select * from t_role");
        logger.debug("--logger---test:"+object);
    }

}
