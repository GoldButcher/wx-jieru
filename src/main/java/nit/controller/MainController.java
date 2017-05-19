package nit.controller;

import nit.utils.ResponseUtil;
import nit.utils.SignUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by evam on 16-12-15.
 */
@RestController
public class MainController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String get(HttpServletRequest request) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String post(HttpServletRequest request)
    {
        String response = ResponseUtil.processRequest(request);
        return response;
    }

}
