package com.phoenix.nirvana.common.util;

import java.security.MessageDigest;

public class MD5Util {

    public static String encryption(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println("13307117777".substring(6));
        System.out.println(encryption("13307117777".substring(6)));
    }
}
