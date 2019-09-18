package com.rrtx.util;

import java.util.HashMap;
import java.util.Map;

public class UnionQRCodeParsing {

    public static Map<String , String> parsingQRCode2Map(String QRCodeMessage){
        Map map = new HashMap<>();
        for(int i = 0;i < QRCodeMessage.length(); i=i){
            //System.out.println("begin i:" + i);
            String tar = QRCodeMessage.substring(i, i+2);
            //System.out.println("tar:" + tar);
            int length = Integer.parseInt(QRCodeMessage.substring(i+2, i+4));
            //System.out.println("length:" + length);
            String value = QRCodeMessage.substring(i+4, i + 4 + length);
            //System.out.println("value:" + value);
            i = i+ 4 + length;
            //System.out.println("end i:" + i);
            map.put(tar,value);
        }
        return map;
    }
    public static void main(String[] args) {
        String QRCodeMessage = "0002010102121531250003440002034410000000000000652045972530334454031005802HK5913Test Merchant6002HK626001200000000000000000000005200000000000000000000007080000001063045855";
        Map<String , String> maplLayer1 = UnionQRCodeParsing.parsingQRCode2Map(QRCodeMessage);
        System.out.println("第一层:");
        System.out.println(maplLayer1);
        String tar62 = maplLayer1.get("62");
        Map<String , String> maptar62 = UnionQRCodeParsing.parsingQRCode2Map(tar62);
        System.out.println("tar62:");
        System.out.println(maptar62);

        //商户名称
        String ta59 = maplLayer1.get("59");
        //mcc(Merchant Category Code)
        String ta52 = maplLayer1.get("52");
        //交易金额
        String ta54 = maplLayer1.get("54");
        //交易币种
        String ta53 = maplLayer1.get("53");

        //商户信息(AcquirerIIN/FwdIIN/Mid)
        String tar15 = maplLayer1.get("15");
        String acquirerIIN = tar15.substring(0,8);
        String fwdIIN = tar15.substring(8,16);
        String mid = tar15.substring(16,31);







    }
}
