package com.rrtx.encryption;

import com.rrtx.util.ProConst;

public class UnionPaySingleton {

    public static String gateway_encryption_public_file;
    public static String gateway_attestation_public_file;
    public static String account_encryption_public_file;
    public static String account_attestation_public_file;
    public static String gateway_decryption_private_file;
    public static String gateway_decryption_private_str;
    public static String gateway_signature_private_file;
    public static String gateway_signature_private_str;
    public static String account_decryption_private_file;
    public static String account_decryption_private_str;
    public static String account_signature_private_file;
    public static String account_signature_private_str;
    public static String union_url;
    public static String upi_appid;
    public static String upi_certid;
    public static String kid_encryption;
    public static String kid_signature;



    private static UnionPaySingleton unionPaySingleton= null;

    /*构造方法私有化*/
    private UnionPaySingleton() {}


    private static synchronized void syncInit(){
        System.out.println("初始化对象");
        if(unionPaySingleton == null){
            gateway_encryption_public_file = ProConst.getValue("gateway_encryption_public_file");
            gateway_attestation_public_file = ProConst.getValue("gateway_attestation_public_file");
            account_encryption_public_file = ProConst.getValue("account_encryption_public_file");
            account_attestation_public_file = ProConst.getValue("account_attestation_public_file");
            gateway_decryption_private_file = ProConst.getValue("gateway_decryption_private_file");
            gateway_decryption_private_str = ProConst.getValue("gateway_decryption_private_str");
            gateway_signature_private_file = ProConst.getValue("gateway_signature_private_file");
            gateway_signature_private_str = ProConst.getValue("gateway_signature_private_str");
            account_decryption_private_file = ProConst.getValue("account_decryption_private_file");
            account_decryption_private_str = ProConst.getValue("account_decryption_private_str");
            account_signature_private_file = ProConst.getValue("account_signature_private_file");
            account_signature_private_str = ProConst.getValue("account_signature_private_str");
            union_url = ProConst.getValue("union_url");
            upi_appid = ProConst.getValue("upi_appid");
            upi_certid = ProConst.getValue("upi_certid");
            kid_encryption = ProConst.getValue("kid_encryption");
            kid_signature = ProConst.getValue("kid_signature");

            unionPaySingleton = new UnionPaySingleton();
            unionPaySingleton.setGateway_encryption_public_file(gateway_encryption_public_file);
            unionPaySingleton.setGateway_attestation_public_file(gateway_attestation_public_file);
            unionPaySingleton.setAccount_encryption_public_file(account_encryption_public_file);
            unionPaySingleton.setAccount_attestation_public_file(account_attestation_public_file);
            unionPaySingleton.setGateway_decryption_private_file(gateway_decryption_private_file);
            unionPaySingleton.setGateway_decryption_private_str(gateway_decryption_private_str);
            unionPaySingleton.setGateway_signature_private_file(gateway_signature_private_file);
            unionPaySingleton.setGateway_signature_private_str(gateway_signature_private_str);
            unionPaySingleton.setAccount_decryption_private_file(account_decryption_private_file);
            unionPaySingleton.setAccount_decryption_private_str(account_decryption_private_str);
            unionPaySingleton.setAccount_signature_private_file(account_signature_private_file);
            unionPaySingleton.setAccount_signature_private_str(account_signature_private_str);
            unionPaySingleton.setUnion_url(union_url);
            unionPaySingleton.setUpi_appid(upi_appid);
            unionPaySingleton.setUpi_certid(upi_certid);
            unionPaySingleton.setKid_encryption(kid_encryption);
            unionPaySingleton.setKid_signature(kid_signature);
        }
    }

    /*对外提供获取对象的静态方法*/
    public static UnionPaySingleton getSingletonMethod(){
        if(unionPaySingleton == null){
            syncInit();
        }
        return unionPaySingleton;
    }

    public String getGateway_encryption_public_file() {
        return gateway_encryption_public_file;
    }

    private void setGateway_encryption_public_file(String gateway_encryption_public_file) {
        UnionPaySingleton.gateway_encryption_public_file = gateway_encryption_public_file;
    }

    public String getGateway_attestation_public_file() {
        return gateway_attestation_public_file;
    }

    private void setGateway_attestation_public_file(String gateway_attestation_public_file) {
        UnionPaySingleton.gateway_attestation_public_file = gateway_attestation_public_file;
    }

    public String getAccount_encryption_public_file() {
        return account_encryption_public_file;
    }

    private void setAccount_encryption_public_file(String account_encryption_public_file) {
        UnionPaySingleton.account_encryption_public_file = account_encryption_public_file;
    }

    public String getAccount_attestation_public_file() {
        return account_attestation_public_file;
    }

    private void setAccount_attestation_public_file(String account_attestation_public_file) {
        UnionPaySingleton.account_attestation_public_file = account_attestation_public_file;
    }

    public String getGateway_decryption_private_file() {
        return gateway_decryption_private_file;
    }

    private void setGateway_decryption_private_file(String gateway_decryption_private_file) {
        UnionPaySingleton.gateway_decryption_private_file = gateway_decryption_private_file;
    }

    public String getGateway_decryption_private_str() {
        return gateway_decryption_private_str;
    }

    private void setGateway_decryption_private_str(String gateway_decryption_private_str) {
        UnionPaySingleton.gateway_decryption_private_str = gateway_decryption_private_str;
    }

    public String getGateway_signature_private_file() {
        return gateway_signature_private_file;
    }

    private void setGateway_signature_private_file(String gateway_signature_private_file) {
        UnionPaySingleton.gateway_signature_private_file = gateway_signature_private_file;
    }

    public String getGateway_signature_private_str() {
        return gateway_signature_private_str;
    }

    private void setGateway_signature_private_str(String gateway_signature_private_str) {
        UnionPaySingleton.gateway_signature_private_str = gateway_signature_private_str;
    }

    public String getAccount_decryption_private_file() {
        return account_decryption_private_file;
    }

    private void setAccount_decryption_private_file(String account_decryption_private_file) {
        UnionPaySingleton.account_decryption_private_file = account_decryption_private_file;
    }

    public String getAccount_decryption_private_str() {
        return account_decryption_private_str;
    }

    private void setAccount_decryption_private_str(String account_decryption_private_str) {
        UnionPaySingleton.account_decryption_private_str = account_decryption_private_str;
    }

    public String getAccount_signature_private_file() {
        return account_signature_private_file;
    }

    private void setAccount_signature_private_file(String account_signature_private_file) {
        UnionPaySingleton.account_signature_private_file = account_signature_private_file;
    }

    public String getAccount_signature_private_str() {
        return account_signature_private_str;
    }

    private void setAccount_signature_private_str(String account_signature_private_str) {
        UnionPaySingleton.account_signature_private_str = account_signature_private_str;
    }

    public String getUnion_url() {
        return union_url;
    }

    private void setUnion_url(String union_url) {
        UnionPaySingleton.union_url = union_url;
    }

    public String getUpi_appid() {
        return upi_appid;
    }

    private void setUpi_appid(String upi_appid) {
        UnionPaySingleton.upi_appid = upi_appid;
    }

    public String getUpi_certid() {
        return upi_certid;
    }

    private void setUpi_certid(String upi_certid) {
        UnionPaySingleton.upi_certid = upi_certid;
    }

    public String getKid_encryption() {
        return kid_encryption;
    }

    private void setKid_encryption(String kid_encryption) {
        UnionPaySingleton.kid_encryption = kid_encryption;
    }

    public String getKid_signature() {
        return kid_signature;
    }

    private void setKid_signature(String kid_signature) {
        UnionPaySingleton.kid_signature = kid_signature;
    }

}
