package com.dcx.poz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/** 
* 配置文件载入类  
* @ClassName: BaseSpringTestCase  
* @Description: 要想实现Spring自动注入，必须继承此类 
* 
 */ 
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:/spring-mvc-context.xml"}) 
//添加注释@Transactional 回滚对数据库操作    
@Transactional 
public class BaseSpringTestCase {
	@Test  
    public void testLogin() {   
        try {  
        		System.out.println("sdfdsf");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
