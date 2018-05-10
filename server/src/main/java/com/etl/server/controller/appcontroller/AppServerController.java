
package com.etl.server.controller.appcontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etl.common.util.Base64Util;
import com.etl.common.util.ResultStatusCode;
import com.etl.server.register.CacheMeta;
import com.hive.util.HiveUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by houlongbin on 2016/11/11.
 */
@Controller
@RequestMapping("serverapi")
public class AppServerController {
    private final int defaultPageSize = 10;

    private static final Log log = LogFactory.getLog(AppServerController.class);

    @RequestMapping(value = "10001")
    /**
     * 表结构注册
     */
    @ResponseBody
    public String getPageGpsDataByCarId(@RequestParam(value = "p") String inputStr, HttpServletRequest request) {
        long start = System.currentTimeMillis();
        String inputInt = request.getParameter("p");
        String msgBody = Base64Util.decode(inputInt);
        JSONObject jsonParam = JSON.parseObject(msgBody);
        if (!jsonParam.containsKey("hiveTable")) {
            log.info("10001api cost=" + (System.currentTimeMillis() - start) + "ms;result=" + ResultStatusCode.PARAM_ERROR.toJson().toString() + ";param=" + jsonParam);
            return ResultStatusCode.PARAM_ERROR.toJson().toString();
        } else {
            try {
                ResultStatusCode resultStatusCode = new ResultStatusCode("C0000", "OK");
                String hiveTable = jsonParam.getString("hiveTable");
//                CacheMeta.HIVE_META_CACHE.put(hiveTable,HiveUtil.getInstance().getHiveListColumns(hiveTable));
                log.info("register hiveTable="+hiveTable);
                log.info("10001api cost=" + (System.currentTimeMillis() - start) + "ms;result=" + resultStatusCode.toJson().toString() + ";param=" + jsonParam);
                return resultStatusCode.toJson().toString();
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e);
                log.info("10001api cost=" + (System.currentTimeMillis() - start) + "ms;result=" + ResultStatusCode.SERVICE_ERROR.toJson().toString() + ";param=" + jsonParam);
                return ResultStatusCode.SERVICE_ERROR.toJson().toString();
            }
        }
    }


}
