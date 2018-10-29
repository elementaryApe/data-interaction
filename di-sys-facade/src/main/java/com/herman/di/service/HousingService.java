package com.herman.di.service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.herman.di.entity.HousingDetail;
import com.herman.di.entity.HousingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 房屋信息服务
 * Created by hsh on 18/10/06.
 */
@Path("/housingService")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface HousingService {

    @GET
    @Path("/get/{userId}")
    HousingDetail getHousingDetailById(long id)throws Exception;

    @GET
    Page findUserInfo(HousingInfo housingInfo, PageRequest request) throws Exception;

    @POST
    @Path("/post/{pageIndex}/{count}")
    boolean dataImport(@PathParam(value = "pageIndex")int pageIndex,@PathParam(value = "count")int count)throws Exception;
}
