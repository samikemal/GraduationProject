package com.teklifver.data;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CodeCheckService {

    private HashMap<String, String> otpCodes = new HashMap<>();

    public void addOtpCode(String key, String otpCode)
    {
        otpCodes.put(key, otpCode);
    }

    public String getOtpCode(String key)
    {
        if(otpCodes.containsKey(key))
        {
            return otpCodes.get(key);
        }
        else
        {
            return null;
        }
    }
    public void remove(String key)
    {
        if (otpCodes.containsKey(key))
        {
            otpCodes.remove(key);
        }
    }
}
