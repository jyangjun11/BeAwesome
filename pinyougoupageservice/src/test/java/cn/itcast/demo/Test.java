package cn.itcast.demo;

import com.pinyougou.page.service.ItemPageService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 37269 on 2018/8/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext*.xml")
public class Test {

    @Autowired
    private ItemPageService itemPageService;

    @org.junit.Test
    public void test(){
        itemPageService.genItemHtml(149187842867970L);
    }

}
