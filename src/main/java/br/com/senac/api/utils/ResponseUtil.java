package br.com.senac.api.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Map<String, Object> responseMap(Object mensagem){
        Map<String,Object> response = new HashMap<>();
        response.put("messages",mensagem);

        return response;
    }
}
