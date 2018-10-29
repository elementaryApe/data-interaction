package com.herman.di.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.herman.di.dao.HousingDetailRepository;
import com.herman.di.dao.HousingInfoRepository;
import com.herman.di.entity.HousingDetail;
import com.herman.di.entity.HousingInfo;
import com.herman.di.service.HousingService;
import com.herman.di.util.DateUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 房屋信息服务实现
 *
 * @author hsh
 * @create 2018-10-06 13:59
 **/
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = com.herman.di.service.HousingService.class, protocol = {"rest", "dubbo"}, version = "1.0")
public class HousingServiceImpl implements HousingService {

    @Resource
    private HousingInfoRepository housingInfoRepository;

    @Resource
    private HousingDetailRepository housingDetailRepository;

    @Override
    public HousingDetail getHousingDetailById(long id) throws Exception {
        return null;
    }

    @Override
    public Page findUserInfo(HousingInfo housingInfo, PageRequest request) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public boolean dataImport(int pageIndex, int count) throws Exception {

        System.out.println("dataImportBegin:" + DateUtils.format(new Date(), DateUtils.DEFAULTDATETIMEPATTERN));
        if (pageIndex == -1)
            pageIndex = 16;
        int startIndex = pageIndex;
        while (true) {
            System.out.println("pageIndex========>" + pageIndex);
            if (pageIndex >= startIndex + count) {
                System.out.println("pageIndex========>" + pageIndex);
                break;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://hefei.zhongjiebao.com:8081/c/mobile/list?page=" + pageIndex)
                    .get()
                    .addHeader("cache-control", "no-cache")
                    .build();
            Response response = client.newCall(request).execute();
            String body = new String(response.body().bytes(), "UTF-8");
            JSONObject object = JSON.parseObject(body);
            List<HousingInfo> data = JSONArray.parseArray(JSON.parseObject(object.get("page").toString()).get("data").toString(), HousingInfo.class);
            if (CollectionUtils.isEmpty(data)) {
                break;
            }
            for (HousingInfo ele : data) {
                HousingDetail housingDetail = new HousingDetail();
                try {
                    HousingInfo save = housingInfoRepository.save(ele);
                    if (save != null) {
                        OkHttpClient clientDetail = new OkHttpClient();
                        Request requestDetail = new Request.Builder()
                                .url("http://hefei.zhongjiebao.com:8081/c/mobile/detail?houseId=" + ele.getId() + "&userId=25005")
                                .get()
                                .addHeader("cache-control", "no-cache")
                                .build();
                        Response responseDetail = clientDetail.newCall(requestDetail).execute();
                        body = new String(responseDetail.body().bytes(), "UTF-8");
                        housingDetail = JSONObject.parseObject(body, HousingDetail.class);
                        HousingDetail saveDetail = housingDetailRepository.save(housingDetail);
                        if (saveDetail == null)
                            throw new Exception("DetailException:pageIndex=" + pageIndex + ",id=" + ele.getId());
                    } else {
                        throw new Exception("DetailException:pageIndex=" + pageIndex + ",id=" + ele.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ele:" + JSONObject.toJSONString(ele));
                    System.out.println("housingDetail:" + JSONObject.toJSONString(housingDetail));
                    System.out.println("pageIndex:" + pageIndex);
                    throw new Exception(e.getMessage());
                }
            }
            pageIndex++;
        }

        System.out.println("dataImportEnd:" + DateUtils.format(new Date(), DateUtils.DEFAULTDATETIMEPATTERN));
        return true;
    }
}
