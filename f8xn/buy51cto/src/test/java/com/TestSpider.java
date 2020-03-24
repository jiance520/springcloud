package com;

import com.entity.Course;
import com.service.ICourseService;
import com.utils.JdbcUtil;
import com.utils.MapToBeanUtil;
import com.utils.My51ctoChromeSpider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//SpringJUnit支持，由此引入Spring-Test框架支持！
//@RunWith(SpringRunner.class)
//@RunWith(SpringRunner.class)
//可指定我们SpringBoot工程的App启动类
//@SpringBootTest(classes = Buy51ctoApp.class)
//由于是Web项目，Junit需要模拟ServletContext，
// 因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TestSpider {
    @Autowired
    private ICourseService iCourseService;
    @Test
    //@Transactional
    //@Rollback
    public void chromeSpider() throws FileNotFoundException, InterruptedException {
        Course course = new Course();
        int j = 0;
        for (int i = 0; i <My51ctoChromeSpider.pagenums ; i++) {
        //for (int i = 0; i <1 ; i++) {
            if(i>=2){
                My51ctoChromeSpider.pageclick65=5; //下一页节点改变。
            }
            if(i>=1){
                My51ctoChromeSpider.page65=5; //下一页节点改变。
            }
            if(My51ctoChromeSpider.pagemax>=2){
                My51ctoChromeSpider.pageclick65=5; //下一页节点改变。
            }
            if(My51ctoChromeSpider.pagemax>=1){
                My51ctoChromeSpider.page65=5; //下一页节点改变。
            }

            List<HashMap> hashMapList = My51ctoChromeSpider.buy51cto("docker");
            for(HashMap hashMap:hashMapList){
                course=new Course();
                Object object = MapToBeanUtil.backInstanceMapBean(course,hashMap);
                System.out.println("-----object:"+object.toString());
                course = (Course)object;
                //int j = iCourseService.insertSelective(course);
                JdbcUtil jdbcUtil = new JdbcUtil();
                String sql = "INSERT INTO course(stitle,snumman,sscore,sprice,sprice2,shuodong,stime,sauthor,sdetail,sdate) VALUES (\"" +
                        course.getStitle()+"\",\""+course.getSnumman()+"\",\""+course.getSscore()+"\","+course.getSprice()+",\""
                        +course.getSprice2()+"\",\""+course.getShuodong()+"\",\""+course.getStime()+"\",\""+course.getSauthor()+"\",\""+
                        course.getSdetail()+"\",NOW())";
                j=jdbcUtil.executeUpdate(sql);
            }
        }
        My51ctoChromeSpider.closeDrive();
    }


}
