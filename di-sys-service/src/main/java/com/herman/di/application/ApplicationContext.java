package com.herman.di.application;

import com.herman.di.util.DateUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.MessageFormat;
import java.util.Date;

/**
 * dubbo 启动上下文
 *
 * @author hsh
 * @create 2018-06-28 11:31
 **/
public class ApplicationContext {

    private static ApplicationContext applicationContext = new ApplicationContext();
    private static ClassPathXmlApplicationContext classPathXmlApplicationContext = null;

    private ApplicationContext() {

    }

    public static ApplicationContext getInstance() {
        return applicationContext;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=======启动 dubbo  start " + DateUtils.format(new Date(), DateUtils.DEFAULTDATETIMEPATTERN) + "==========");
        ApplicationContext.getInstance().start();
    }

    public void start() throws InterruptedException {
        if (classPathXmlApplicationContext == null) {
            classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
            classPathXmlApplicationContext.start();
            System.out.println("=======启动 dubbo  end："+DateUtils.format(new Date(), DateUtils.DEFAULTDATETIMEPATTERN));
        }
        System.out.println(MessageFormat.format("[STARTED]{0}", this.getClass().getSimpleName()));
        while (true) {
            Thread.currentThread().sleep(1000);
        }
    }
}
