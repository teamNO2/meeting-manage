package com.suixingpay.controller;

import com.suixingpay.utils.QrCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
public class QRCodeController {
    /**
     * @Author:柴宇航
     */
    @RequestMapping("/createQrCode")
    public void createQrCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream os = response.getOutputStream();
            QrCodeUtils.encode("http://localhost:8080/sign/signuser", "/static/images/1.png", os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
