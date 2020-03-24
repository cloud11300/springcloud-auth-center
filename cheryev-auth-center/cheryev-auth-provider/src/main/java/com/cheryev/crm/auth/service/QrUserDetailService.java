package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.pojo.BaseUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class QrUserDetailService extends BaseUserDetailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected BaseUserVO getUser(String qrCode) {
        return null;
    }
}
