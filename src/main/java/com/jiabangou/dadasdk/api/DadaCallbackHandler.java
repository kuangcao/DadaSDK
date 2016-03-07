package com.jiabangou.dadasdk.api;


import com.jiabangou.dadasdk.model.DadaOrderCallbackMessage;

import java.util.Map;

public interface DadaCallbackHandler {
    void handler(DadaOrderCallbackMessage callbackMessage, Map<String, Object> params);
}
