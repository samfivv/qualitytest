package com.midai.miya.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	public static String getRandom(){
		 Random random = new Random();
		 String  num = (int)(random.nextDouble()*(100000 - 10000) + 10000)+"";
		 return num;
	}
}
