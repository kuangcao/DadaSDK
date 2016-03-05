package com.jiabangou.dadasdk.api;


import com.jiabangou.dadasdk.model.DadaOrderCallbackMessage;

public interface DadaCallbackHandler {
    void handler(DadaOrderCallbackMessage callbackMessage);
}
