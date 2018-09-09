package comherman.di.api;

import com.herman.di.service.UserInfoService;
import comherman.di.application.ApplicationContext;

/**
 * 服务提供者api
 * @author hsh
 * @create 2018-09-09 12:04
 **/
public class DISysProviderApi {

    public final static UserInfoService userinfoService;


    static {
        userinfoService= ApplicationContext.getBean(UserInfoService.class);
    }
}
