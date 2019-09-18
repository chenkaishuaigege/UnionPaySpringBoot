package com.rrtx.service;

import com.rrtx.dataobject.MsgInfo;

import java.util.Map;

public interface IUnionPayService {
    /**
     *
     * @param msgInfo
     * @param trxInfo
     * @return
     */
    Map unionPayService(MsgInfo msgInfo, Object trxInfo) throws Exception;


}

