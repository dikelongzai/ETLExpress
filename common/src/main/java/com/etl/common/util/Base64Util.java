package com.etl.common.util;



import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class Base64Util {
    /**
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String value) {
        if (value == null || value.length() <= 0){
            return "";
        }

        try {
            return new String(Base64.encodeBase64(value.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String value) {
        if (value == null || value.length() <= 0){
            return "";
        }

        try {
            return new String(Base64.decodeBase64(value.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * @param args
     */
    /**
     * @param args
     */
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(decode("dWlkPTEyMyZhcHBrZXk9OTJBQzlENDZFNTk4NkFCRjc4RDZBNzU0NEY1Qzk5RjYmY3VyPWh0dHA6Ly8xOTIuMTY4LjMwLjEzNTo4MDgwL2ZyZWVDYWxsL3JpZ2h0TG9nSW5mby5qc3AmR1VJRD00MzYxNTUxOSZ0aXRsZT3gB5iHYyZiVHlwZT1NaWNyb3NvZnQgSW50ZXJuZXQgRXhwbG9yZXImYlZlcnNpb249IE1TSUUgOS4wJmJKYXZhPTEmYkZsYXNoPTEwLjAmYk9TPSBXaW5kb3dzIE5UIDYuMSZiU2NyPTEyNTd4Nzg1JmJDb2xvcj0yNC1iaXQmYkhsPXpoLWNuJmJQbHVnaW49IFRyaWRlbnQvNS4wLCBTTENDMiwgLk5FVCBDTFIgMi4wLjUwNzI3LCAuTkVUIENMUiAzLjUuMzA3MjksIC5ORVQgQ0xSIDMuMC4zMDcyOSwgTWVkaWEgQ2VudGVyIFBDIDYuMCwgSW5mb1BhdGguMywgLk5FVDQuMEMsIC5ORVQ0LjBFLCBCT0lFOSxaSENOJnN0PTcmZmlyc3Q9MA=="));
    }
}
