package com.mentaljava.mentaljavarestapiproject.table.usercalendar.Controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.dto.UsercalendarDTO;
import com.mentaljava.mentaljavarestapiproject.table.usercalendar.service.UserCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usercalendar")
@Slf4j
public class UserCalendarController {

    private final UserCalendarService userCalendarService;

    public UserCalendarController(UserCalendarService userCalendarService) { this.userCalendarService = userCalendarService; }

    //전체 유저 캘린더 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> selectUserCalendarList(){
        List<UsercalendarDTO> usercalendarList = userCalendarService.findAllUserCalendarList();
        System.out.println("usercalendarList = " + usercalendarList);

        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"전체 유저 캘린더 조회 성공",usercalendarList));
    }
}
