package com.rrtx.controller;

import cn.hutool.json.JSONUtil;
import com.rrtx.dataobject.CvmInfo;
import com.rrtx.dataobject.EncCertId;
import com.rrtx.dataobject.MsgInfo;
import com.rrtx.dataobject.MsgResponse;
import com.rrtx.onlinemessages.KYCVerificationTrxInfo;
import com.rrtx.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static cn.hutool.core.text.csv.CsvUtil.getReader;
import static com.rrtx.util.JWTUtil.jwsAttestation;

@Controller
public class UnionPayTransactionController {


    /**
     * 接收scis账务处理回调:
     * @see UnionPayTransactionController#DebitTransaction(HttpServletRequest, HttpServletResponse)
     *
     */
    /**
     * @see UnionPayTransactionController#KYCVerification(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#OpenAccount(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#BalanceInquiry(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#P2PTransfer(HttpServletRequest, HttpServletResponse)
     * 支付接口需要的提供的回调接口
     * @see UnionPayTransactionController#DebitTransaction(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#CreditTransaction(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#ReversalTransaction(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#CashOutToken(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#MerchantVerification(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#TransactionResultInquiry(HttpServletRequest, HttpServletResponse)
     * @see UnionPayTransactionController#KeyExchange(HttpServletRequest, HttpServletResponse)
     */
    public static final String msgInfoConstant = "msgInfo";
    public static final String trxInfoConstant = "trxInfo";
    public static final String encCertIdConstant = "encCertId";
    public static final String msgResponseConstant = "msgResponse";

    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.KYCVerification.do")
    public void KYCVerification(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String servletPath = request.getServletPath();
        System.out.println("接收到转发" + servletPath);
        String upi_jws = request.getHeader("UPI-JWS");
        System.out.println("upi_jws:" + upi_jws);
        String resultMessage = readRequest(request);
        System.out.println("body:" + resultMessage);
        System.out.println("验签:");
        if (!JavaUtil.isEmpty(upi_jws)) {
            //验签
            boolean attestationResult = jwsAttestation(Constant.NETWORK_TYPE_APP_GATEWAY,resultMessage , upi_jws);
            if(attestationResult){
                System.out.println("验签成功");
            }else{
                System.out.println("验签失败");
            }
        }else{
            System.out.println("返回错误,不验签");
        }
        //获取请求实体类
        Map map = new HashMap();
        Map<String, Object> resultMap = JsonMapCoverUtill.coverJsonString2Map(resultMessage);
        MsgInfo msgInfoRes = (MsgInfo) SerializeUtil.deserialize(MsgInfo.class, resultMap.get(msgInfoConstant).toString());
        map.put(msgInfoConstant , msgInfoRes);
        Object trxInfoRes = SerializeUtil.deserialize(KYCVerificationTrxInfo.class, resultMap.get(trxInfoConstant).toString());
        map.put(trxInfoConstant , trxInfoRes);
        KYCVerificationTrxInfo kycverificationtrxinfo = (KYCVerificationTrxInfo) map.get(trxInfoConstant);
        String cvmInfo = kycverificationtrxinfo.getCvmInfo();
        //解密
        CvmInfo cvmInfoResult = (CvmInfo)SerializeUtil.deserialize(CvmInfo.class, JWTUtil.jweDecryption(Constant.NETWORK_TYPE_APP_GATEWAY,cvmInfo));
        System.out.println("cvmInfoResult:");
        System.out.println(cvmInfoResult.toString());

        // 组装返回报文
        response.setHeader("UPI-JWS", "KYCVerification response upi-jws message");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");

        OutputStream outputStream = response.getOutputStream();
        outputStream.write("".getBytes("UTF-8"));
    }

    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.OpenAccount.do")
    public String OpenAccount(HttpServletRequest request, HttpServletResponse response) {
        return "OpenAccount";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.BalanceInquiry.do")
    public String BalanceInquiry(HttpServletRequest request, HttpServletResponse response) {
        return "BalanceInquiry";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.P2PTransfer.do")
    public String P2PTransfer(HttpServletRequest request, HttpServletResponse response) {

        return "P2PTransfer";
    }


    /**
     * 银联二维码支付-主扫交易回调接口
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.DebitTransaction.do")
    public void DebitTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("接收到账务处理请求------------------");
        String servletPath = request.getServletPath();
        System.out.println("接收到转发" + servletPath);
        String upi_jws = request.getHeader("UPI-JWS");
        System.out.println("upi_jws" + upi_jws);
        String resultMessage = readRequest(request);
        System.out.println("body" + resultMessage);
        System.out.println("验签:");
        if (!JavaUtil.isEmpty(upi_jws)) {
            //验签
            boolean attestationResult = jwsAttestation(Constant.NETWORK_TYPE_ACCOUNT_SYSTEM,resultMessage , upi_jws);
            if(attestationResult){
                System.out.println("验签成功");

            }else{
                System.out.println("验签失败");
            }
        }else{
            System.out.println("返回错误,不验签");
        }
        //获取请求实体类
        Map map = new HashMap();
        Map<String, Object> resultMap = JsonMapCoverUtill.coverJsonString2Map(resultMessage);
        MsgInfo msgInfoRes = (MsgInfo) SerializeUtil.deserialize(MsgInfo.class, resultMap.get(msgInfoConstant).toString());
        map.put(msgInfoConstant , msgInfoRes);
        Object trxInfoRes = SerializeUtil.deserialize(KYCVerificationTrxInfo.class, resultMap.get(trxInfoConstant).toString());
        map.put(trxInfoConstant , trxInfoRes);
        //TODO 业务逻辑处理


        // 响应 MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("DEBIT_TRANSACTION").setMsgID(msgInfoRes.getMsgID()).createEntity();
        // 响应 MsgResponse报文
        MsgResponse msgResponse = new MsgResponse().builder().setResponseCode("00").setResponseMsg("Approved").createEntity();
        //实体类序列化
        String msgInfoSerialize = SerializeUtil.serialize(msgInfo);
        String msgResponseSerialize = SerializeUtil.serialize(msgResponse);
        //组装发送请求的MAP(key值需要和接口文档中对应)
        Map<String, Object> paramMap = new LinkedHashMap<>();
        paramMap.put(msgInfoConstant , msgInfoSerialize);
        paramMap.put(msgResponseConstant , msgResponseSerialize);
        //交易数据的 json字符串
        String onlineMessage = JSONUtil.parseFromMap(paramMap).toString();
        onlineMessage = onlineMessage.replace("\"{" , "{");
        onlineMessage = onlineMessage.replace("}\"" , "}");
        onlineMessage = onlineMessage.replace("\\" , "");
        System.out.println("相应 online message 信息:");
        System.out.println(onlineMessage);
        //签名
        String headerSign = JWTUtil.jwsSignature(Constant.NETWORK_TYPE_ACCOUNT_SYSTEM,onlineMessage);
        // TODO 组装返回报文
        response.setHeader("UPI-JWS", headerSign);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(onlineMessage.getBytes("UTF-8"));
    }




    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.CreditTransaction.do")
    public String CreditTransaction(HttpServletRequest request, HttpServletResponse response) {
        return "CreditTransaction";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.ReversalTransaction.do")
    public String ReversalTransaction(HttpServletRequest request, HttpServletResponse response) {
        return "ReversalTransaction";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.CashOutToken.do")
    public String CashOutToken(HttpServletRequest request, HttpServletResponse response) {
        return "CashOutToken";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.MerchantVerification.do")
    public String MerchantVerification(HttpServletRequest request, HttpServletResponse response) {
        return "MerchantVerification";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.TransactionResultInquiry.do")
    public String TransactionResultInquiry(HttpServletRequest request, HttpServletResponse response) {
        return "TransactionResultInquiry";
    }
    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.KeyExchange.do")
    public String KeyExchange(HttpServletRequest request, HttpServletResponse response) {
        return "KeyExchange";
    }

    public String readRequest(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null) {
            wholeStr += str;
        }
        return wholeStr;
    }
}
