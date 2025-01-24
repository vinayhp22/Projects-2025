package com.skyllx.parkingrental.util;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GenerateOTP {

	public static int generateOtp() {
		log.info("running generateOtp()");
		int otp= (int) (Math.random()*9000)+1000;
		return otp;
	}
}
