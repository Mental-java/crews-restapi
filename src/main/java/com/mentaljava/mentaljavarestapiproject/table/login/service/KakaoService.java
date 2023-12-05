package com.mentaljava.mentaljavarestapiproject.table.login.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.entity.User;
import com.mentaljava.mentaljavarestapiproject.table.user.repository.UserRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public HashMap<String, Object> getUserInfo(String access_Token) throws IOException {
        // 클라이언트 요청 정보
        HashMap<String, Object> userInfo = new HashMap<String, Object>();


        //------kakao GET 요청------
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + access_Token);

        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }

        log.info("Response Body : " + result);

        // jackson objectmapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        // JSON String -> Map
        Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
        });


        //사용자 정보 추출
        Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
        Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");


        String nickname = properties.get("nickname").toString();
        String email = kakao_account.get("email").toString();

        //userInfo에 넣기

        userInfo.put("nickname", nickname);
        userInfo.put("email", email);
        userInfo.put("token",access_Token);


        return userInfo;
    }


    @Transactional
    public UserDTO createUser(HashMap<String, Object> userInfo) {

        String email = (String) userInfo.get("email");
        String nickname = (String) userInfo.get("nickname");
        String token = (String) userInfo.get("token");

        User user = userRepository.findByUserId(email);

        // 동일한 이메일을 가진 사용자가 이미 있다면 로그인, 처음 로그인이라면 회원가입
        if(user == null){
            log.info("[KakaoLogin] signup start ======== ");
            User newUser = User.builder()
                    .email(email)
                    .nickname(nickname)
                    .daimondCount(50)
                    .joinDate(LocalDate.now())
                    .permissionType("0")
                    .build();

            UserDTO userDTO = modelMapper.map(newUser, UserDTO.class);
            userDTO.setToken(token);

            userRepository.save(newUser);

            return userDTO;

        }

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setToken(token);
        log.info("기존 유저 ======="+userDTO);

        return userDTO;
    }
}
