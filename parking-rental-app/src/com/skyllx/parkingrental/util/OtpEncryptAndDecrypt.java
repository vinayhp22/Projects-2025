package com.skyllx.parkingrental.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OtpEncryptAndDecrypt {

	// static String encryptedOtp = null;
	 static byte[] bytes=null;
	
	public static byte[] getByte() {
		return bytes;
	}
	
	public static String enryptOtp(int otp) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(otp);
		bytes = baos.toByteArray();
		StringBuilder s = new StringBuilder();  
        for(int i=0; i< bytes.length ;i++)  
        {  
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)); 
        }  
        /* Complete hashed password in hexadecimal format */  
        String encryptedOtp = s.toString(); 
		return encryptedOtp;
	}
	
	public static String decryptOtp(byte[] encryptedOtp) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		String decryptedOtp=dis.toString();
		return decryptedOtp;
	}
	
	
}
