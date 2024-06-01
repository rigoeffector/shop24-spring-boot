package com.shop24.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

    public static Map<String, Object> buildResponse(String message, boolean success, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("success", success);
        response.put("data", data);
        return response;
    }
    
}