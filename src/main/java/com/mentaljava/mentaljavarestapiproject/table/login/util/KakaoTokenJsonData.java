package com.mentaljava.mentaljavarestapiproject.table.login.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.mentaljava.mentaljavarestapiproject.table.login.dto.KakaoTokenResponse;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class KakaoTokenJsonData {
    private final WebClient webClient;
    private static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";

    private static final String GRANT_TYPE = "authorization_code";
    private static final String CLIENT_ID = "3506fa179cbeaf488ef2be30f802135d";

    public KakaoTokenResponse getToken(String code) {
        String uri = TOKEN_URI + "?grant_type=" + GRANT_TYPE + "&client_id=" + CLIENT_ID + "&code=" + code;
        System.out.println(uri);

        Flux<KakaoTokenResponse> response = webClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(KakaoTokenResponse.class);

        return response.blockFirst();
    }
}
