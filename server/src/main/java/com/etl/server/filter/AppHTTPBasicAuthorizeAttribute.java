package com.etl.server.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.etl.common.util.Base64Util;
import com.etl.common.util.ResultStatusCode;
import com.etl.common.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class AppHTTPBasicAuthorizeAttribute implements Filter {

    private static final Log log = LogFactory.getLog(AppHTTPBasicAuthorizeAttribute.class);

    public static final String mdkey = "fdda7817bc6b8e5571a3d790297f5e5e";


    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ResultStatusCode resultStatusCode = checkHTTPBasicAuthorize(request);
        if (resultStatusCode != ResultStatusCode.OK) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String retMsg = Base64Util.encode(resultStatusCode.toJson().toJSONString());
            try {
                retMsg = URLEncoder.encode(retMsg, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e);
            }
            httpResponse.getWriter().write(retMsg);
            //httpResponse.getWriter().write(resultStatusCode.toJson().toJSONString());
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    private ResultStatusCode checkHTTPBasicAuthorize(ServletRequest request) {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String param = httpRequest.getParameter("p");
            if (param != null) {
                String strParam = Base64Util.decode(param);
                if (strParam.contains("%")) {
                    strParam = java.net.URLDecoder.decode(strParam);
                }
                log.info(strParam);
                JSONObject json = JSON.parseObject(strParam);
                long st = json.getLong("st");
                String authorCode = StringUtil.getMD5(st + mdkey);
                String sign = json.getString("sign");
                if (authorCode.equals(sign)) {
                    return ResultStatusCode.OK;
                }

            }
            return ResultStatusCode.AUTHOR_ERROR;
        } catch (Exception ex) {
            return ResultStatusCode.SERVICE_ERROR;
        }

    }
}
