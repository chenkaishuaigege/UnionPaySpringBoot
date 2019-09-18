import com.rrtx.dataobject.CvmInfo;
import com.rrtx.dataobject.EncCertId;
import com.rrtx.dataobject.MsgInfo;
import com.rrtx.dataobject.MsgResponse;
import com.rrtx.onlinemessages.*;
import com.rrtx.service.impl.UnionPayServiceImpl;
import com.rrtx.util.JWTUtil;
import org.junit.Test;

import java.util.Map;

public class InterfaceTest1_10 {

    /**
     * 卡申请流程:
     * @see InterfaceTest1_10#CardEnrollmentTest()  CardEnrollment 测试接口 2.4
     *      此消息用于使用现有实体卡申请令牌，或使用给定帐户号申请新的虚拟卡
     * @see InterfaceTest1_10#CardStatusManagementTest()  CardStatusManagement 测试接口 2.7
     *      申请更改虚拟卡状态，管理操作包括挂起、恢复、删除。
     * @see InterfaceTest1_10#cardStatusInquiryTest()  cardStatusInquiry 测试接口 2.9
     *      此消息由APPGW发送到SCIS，以获得虚拟卡的状态
     */


    /**
     * 2.1
     * Functions:This message is sent by APPGW to SCIS to get the list of cardholder verification(CV) methods required.
     * 获取所需的持卡人验证(CV)方法列表。
     */
    @Test
    public void cvmInquiryTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("CVM_INQUIRY").createEntity();
        // trxInfo交易信息
        CVMInquiryTrxInfo trxInfo = new CVMInquiryTrxInfo().builder().createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }

    /**
     * KYC Verification 测试接口 2.2
     * Functions:Transmits the KYC verification information entered by users to SCIS for verification.
     * 将用户输入的KYC验证信息传输给SCIS进行验证。
     * TODO CvmInfo  使用加密证书的公钥加密。
     */
    @Test
    public void KYCVerificationTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("KYC_VERIFICATION").createEntity();
        // CvmInfo报文
        CvmInfo cvmInfo = new CvmInfo().builder().setFirstName("陈").setLastName("凯").setIdType("ID card").setIdNo("1234567890").setIdCountry("CHN").setAccountNo("100000001").createEntity();
        // trxInfo交易信息
        KYCVerificationTrxInfo trxInfo = new KYCVerificationTrxInfo().builder()
                .setDeviceID("1b5ddc2562a8de5b4e175d418f5b7edf")
                .setUserID("3329941324335104")
                .setCvmInfo(cvmInfo)
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        MsgInfo msgInfoReturn = (MsgInfo) map.get("msgInfo");
        MsgResponse msgResponseReturn = (MsgResponse) map.get("msgResponse");
        System.out.println(msgInfoReturn.getMsgType());
        System.out.println(msgInfoReturn.getInsID());
        System.out.println(msgInfoReturn.getMsgID());
        System.out.println(msgInfoReturn.getTimeStamp());
        System.out.println(msgInfoReturn.getVersionNo());
        System.out.println(msgResponseReturn.getResponseCode());
        System.out.println(msgResponseReturn.getResponseMsg());
        printMethod(map);
    }


    /**
     * 2.3 Open Account
     * Apply for a virtual card.If this application is approved,a new virtual card is created by SCIS,the PAN and token of the virtual card will be returned by the response message to APPGW.
     * 申请一张虚拟卡。如果这个应用程序被批准，SCIS将创建一个新的虚拟卡，响应消息将把虚拟卡的PAN和令牌返回给APPGW。
     */
    @Test
    public void openAccountTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("OPEN_ACCOUNT").createEntity();
        // trxInfo交易信息
        OpenAccountTrxInfo trxInfo = new OpenAccountTrxInfo().builder()
                                                            .setDeviceID("1b5ddc2562a8de5b4e175d418f5b7edf")
                                                            .setUserID("3329941324335104")
                                                            .setOrigMsgID("A3999999920190808105159700443")
                                                            .setOrigReferNo("119771")
                                                            .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }

    /**
     * CardEnrollment 测试接口 2.4
     * This message is used to apply for a token with an existing entity card,or apply for a new virtual card with a given account no
     * 此消息用于使用现有实体卡申请令牌，或使用给定帐户号申请新的虚拟卡
     * 返回实体类交易信息:
     * {deviceID='1b5ddc2562a8de5b4e175d418f5b7edf', userID='null', cvmInfo='null', token='6291103788756416', tokenExpiry='801230235959', pan='eyJhbGciOiJSU0ExXzUiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2Iiwia2lkIjoiMjAxODA1MDcwMDIifQ.OnBd4WDXfIGDFArhmH2fo71cY_TokpYoffWNDJGYkCx0snJB7a5p6Dt4evR0O9ABfmbdQ5xnusRTQFrYO0HqVFHt9XYyN5ClWvZyqrV_J6uP-6_m-gFxhdUH4GCB5fooofT9wdnUKxtxCrCqp0vUW-mFhD22z8C0df2lkqMy2yoz3Zp9rfKmqOW26H8j8bPfKrzOn_B0I6b8rY2FCcLinyAnfBU7OoIuFNCixS5lAo5Yk6FPepME8D6eDJPAbCJ7gHSYGyMRpINn9-1hO7r9UufUP8E4euGiZuWd0MoHBYs4UklJpIXQ66cKs7tGh9yFLNldvR6BPrgk7ziqU0qQbQ.NTc0YjAxNGNlYTQwNGU3MQ.6nGXVZVQkGOqU-ztQTUVLCnSAzBTwXyRkSdriLzt4nY.n_k6aKfPFdbiaDdOb6Ex7g', panExpiry='09/22', maskedPan='6291********0018', cardType='Debit Card', cardFaceID='100205'}
     * 返回实体类msgInfo:
     * MsgInfo{versionNo='1.0.0', msgID='A3999006120190917152901', timeStamp='20190917152901', msgType='CARD_ENROLLMENT', insID='39990061'}
     * 返回实体类MsgResponse:
     * {responseCode='00', responseMsg='Approved'}
     *
     */
    @Test
    public void CardEnrollmentTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("CARD_ENROLLMENT").createEntity();
        // CvmInfo报文
        CvmInfo cvmInfo = new CvmInfo().builder().setFirstName("chen").setLastName("kai")
                .setIdType("ID card").setIdNo("1234567890").setIdCountry("CHN")
                .setAccountNo("100000001").createEntity();
        // trxInfo交易信息
        CardEnrollmentTrxInfo trxInfo = new CardEnrollmentTrxInfo().builder()
                .setDeviceID("1b5ddc2562a8de5b4e175d418f5b7edf")
                .setUserID("3329941324335104")
                .setCvmInfo(cvmInfo)
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }

    /**
     * Cardface Downloading 测试接口 2.5
     *
     */
    @Test
    public void CardfaceDownloadingTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("CARDFACE_DOWNLOADING").createEntity();
        // trxInfo交易信息
        CardfaceDownloadingTrxInfo trxInfo = new CardfaceDownloadingTrxInfo().builder()
                .setCardfaceID("100201")
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }

    /**
     * Account Update Notification 测试接口 2.6
     */
    @Test
    public void AccountUpdateNotificationTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("ACCOUNT_UPDATE_NOTIFICATION").createEntity();
        // trxInfo交易信息
        AccountUpdateNotificationTrxInfo trxInfo = new AccountUpdateNotificationTrxInfo().builder()
                .setToken("6292610358444335641")
                .setAccountTier("1")
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }


    /**
     * Card Status Management 测试接口 2.7
     * Apply for changing the state of a virtual card,the management operation includes suspend,resume,delete.
     * 申请更改虚拟卡状态，管理操作包括挂起、恢复、删除。
     */
    @Test
    public void CardStatusManagementTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("CARD_STATUS_MANAGEMENT").createEntity();
        // trxInfo交易信息
        CardStatusManagementTrxInfo trxInfo = new CardStatusManagementTrxInfo().builder()
                .setDeviceID("1b5ddc2562a8de5b4e175d418f5b7edf")
                .setUserID("3333800261535744")
                .setToken("6292610358444335641")
                .setTokenAction("DELETE")
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }
    /**
     * Card Status Notification 2.8  SCIS调用APPGW
     *
     */

    /**
     * Card Status Inquiry测试接口 2.9
     * This message is sent by APPGW to SCIS to get the state of a virtual card
     * 此消息由APPGW发送到SCIS，以获得虚拟卡的状态
     */
    @Test
    public void cardStatusInquiryTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("CARD_STATUS_INQUIRY").createEntity();
        // trxInfo交易信息
        CardStatusInquiryTrxInfo trxInfo = new CardStatusInquiryTrxInfo().builder()
                .setDeviceID("1b5ddc2562a8de5b4e175d418f5b7edf")
                .setUserID("3301817853921280")
                .setToken("6200099880516965")
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }

    /**
     * Card Balance Inquiry 测试接口 2.10
     */
    @Test
    public void cardBalanceInquiryTest() throws Exception {
        // MsgInfo公共报文
        MsgInfo msgInfo = new MsgInfo().builder().setMsgType("CARD_BALANCE_INQUIRY").createEntity();
        // trxInfo交易信息
        CardBalanceInquiryTrxInfo trxInfo = new CardBalanceInquiryTrxInfo().builder().setDeviceID("1b5ddc2562a8de5b4e175d418f5b7edf").setUserID("3333800261535744").setToken("6292610358444335641")
                .createEntity();
        // 调用service服务
        Map map = new UnionPayServiceImpl().unionPayService(msgInfo, trxInfo);
        printMethod(map);
    }

    private static void printMethod(Map map ) throws Exception {
        System.out.println("---------------------返回信息----------------------");
        Object trxInfo2 = map.get("trxInfo");
        MsgResponse msgResponse2 = (MsgResponse) map.get("msgResponse");
        EncCertId encCertId2 = (EncCertId) map.get("encCertId");
        MsgInfo msgInfo2 = (MsgInfo) map.get("msgInfo");
        System.out.println("返回实体类交易信息:");
        System.out.println(trxInfo2);
        System.out.println("返回实体类msgInfo:");
        System.out.println(msgInfo2);
        System.out.println("返回实体类MsgResponse:");
        System.out.println(msgResponse2);
        System.out.println("返回实体类EncCertId:");
        System.out.println(encCertId2);
        CardEnrollmentTrxInfo cardenrollmenttrxinfo = (CardEnrollmentTrxInfo) trxInfo2;
    }

}
