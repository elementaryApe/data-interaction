package com.herman.di.controller;

import com.herman.di.entity.Result;
import com.herman.di.service.HousingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author hsh
 * @create 2018-10-06 15:27
 **/
@Controller
@RequestMapping(value = "housing")
public class HousingController {

    @Resource
    private HousingService housingService;

    @RequestMapping(value = "data/import", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserInfo(@RequestParam(value = "startIndex") int startIndex,
                              @RequestParam(value = "count") int count,
                              @RequestParam(value = "endIndex") int endIndex) {
        try {
            for (int i = startIndex; i <= endIndex; i++) {
                if (!housingService.dataImport(startIndex, count))
                    return Result.returnOperateFail();
                else
                    startIndex += count;
            }
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.returnError("出错了 请查看日志");
        }
    }
}
