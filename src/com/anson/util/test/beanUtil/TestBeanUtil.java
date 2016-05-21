package com.anson.util.test.beanUtil;

import com.anson.util.beanUtil.BeanUtils;
import com.anson.util.external.command.CommandAction;
import com.anson.util.external.impl.StringCommand;
import com.anson.util.test.beanUtil.bean.Test1;
import com.anson.util.test.beanUtil.bean.Test2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ludao on 16/2/4.
 */
public class TestBeanUtil {

    @Test
    public void testCopyProperties(){
        Test1 test1 = new Test1("test",1);
        Test2 test2 = new Test2();
        BeanUtils.copyProperties(test1,test2);
        System.out.println(test2);
    }

    @Test
    public void testIsSimpleBeanEmpty(){
        Test2 test2 = new Test2();
        test2.setTall(180);
        Assert.assertFalse(BeanUtils.isSimpleBeanEmpty(Test2.class, test2));
    }

    @Test
    public void testChangeBean2Map(){
        Test2 test = new Test2("test",1,180,"guess");
        List<Object> list = new ArrayList<Object>();
        list.add(test);
        Map<String,Object> result = BeanUtils.changeBean2Map(list);
        System.out.println(result);
    }

    @Test
    public void testDoAssignField() throws IllegalAccessException {
        Test1 test1 = new Test1("test",21);
        CommandAction commandAction = new StringCommand();
        BeanUtils.doAssignField(test1,String.class, commandAction);
    }

}
