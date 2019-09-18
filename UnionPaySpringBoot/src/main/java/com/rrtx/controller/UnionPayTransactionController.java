package com.rrtx.controller;

import com.rrtx.dataobject.MsgInfo;
import com.rrtx.dataobject.MsgResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class UnionPayTransactionController {

    /**
     * 接收scis账务处理回调:
     * @see UnionPayTransactionController#DebitTransaction(HttpServletRequest, HttpServletResponse)
     *      此消息用于使用现有实体卡申请令牌，或使用给定帐户号申请新的虚拟卡
     */


    @ResponseBody
    @RequestMapping("UnionPayTransactionAction.KYCVerification.do")
    public String KYCVerification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        System.out.println("接收到转发" + servletPath);
        String upi_jws = request.getHeader("UPI-JWS");
        System.out.println("upi_jws" + upi_jws);
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println("body" + wholeStr);
        response.setHeader("UPI-JWS", "KYCVerification response upi-jws message");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return "KYCVerification response body message";
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
    public String DebitTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("接收到账务处理请求------------------");
        String servletPath = request.getServletPath();
        System.out.println("接收到转发" + servletPath);
        String upi_jws = request.getHeader("UPI-JWS");
        System.out.println("upi_jws" + upi_jws);
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println("body" + wholeStr);
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("DEBIT_TRANSACTION").createEntity();
        // MsgResponse报文
        MsgResponse msgResponse = new MsgResponse().builder().setResponseCode("").setResponseMsg("").createEntity();
        return "DebitTransaction";
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

/**
 /xservice/UnionPayTransactionAction.KYCVerification.do
 /xservice/UnionPayTransactionAction.OpenAccount.do
 /xservice/UnionPayTransactionAction.BalanceInquiry.do
 /xservice/UnionPayTransactionAction.P2PTransfer.do
 /xservice/UnionPayTransactionAction.DebitTransaction.do
 /xservice/UnionPayTransactionAction.CreditTransaction.do
 /xservice/UnionPayTransactionAction.ReversalTransaction.do
 /xservice/UnionPayTransactionAction.CashOutToken.do
 /xservice/UnionPayTransactionAction.MerchantVerification.do
 /xservice/UnionPayTransactionAction.TransactionResultInquiry.do
 /xservice/UnionPayTransactionAction.KeyExchange.do
 */

}
