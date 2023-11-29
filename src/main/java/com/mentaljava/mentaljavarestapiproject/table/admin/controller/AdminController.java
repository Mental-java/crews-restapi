package com.mentaljava.mentaljavarestapiproject.table.admin.controller;

import com.mentaljava.mentaljavarestapiproject.common.Criteria;
import com.mentaljava.mentaljavarestapiproject.common.PagingDTO;
import com.mentaljava.mentaljavarestapiproject.common.PagingResponseDTO;
import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.service.AdminService;
import com.mentaljava.mentaljavarestapiproject.table.crew.dto.CrewDTO;
import com.mentaljava.mentaljavarestapiproject.table.crew.service.CrewService;
import com.mentaljava.mentaljavarestapiproject.table.report.dto.ReportDTO;
import com.mentaljava.mentaljavarestapiproject.table.report.service.ReportService;
import com.mentaljava.mentaljavarestapiproject.table.user.dto.UserDTO;
import com.mentaljava.mentaljavarestapiproject.table.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final UserService userService;
    private final CrewService crewService;
    private final ReportService reportService;

    // 크루원 조회
    @GetMapping("/crewMemberList")
    public ResponseEntity<ResponseDTO> crewMemberList(){
        List<UserDTO> crewMemberList = userService.findAllUserList();
        log.info("[AdminController] crewMemberList ========"+crewMemberList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루원 조회 성공" ,crewMemberList));
    }

    // 크루 조회
    @GetMapping("/crewList")
    public ResponseEntity<ResponseDTO> crewList(
            @RequestParam(value = "offset", defaultValue = "1") String offset) {

        int total = crewService.seletTotalCrew();

        Criteria cri = new Criteria(Integer.valueOf(offset), 10);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        pagingResponseDTO.setData(crewService.selectCrewListWithPaging(cri));
        pagingResponseDTO.setPageInfo(new PagingDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 크루 리스트 조회 성공", pagingResponseDTO));
    }

    // 크루 상세 조회
    @GetMapping("/detail/{crewId}")
    public ResponseEntity<ResponseDTO> crewDetail(@PathVariable Integer crewId){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "크루 상세정보 조회 성공", crewService.selectCrew(crewId)));
    }

    // 크루원 삭제
    @DeleteMapping("/list/{userId}/delete")
    public ResponseEntity<ResponseDTO> deleteCrewMember(@PathVariable("userId") String userId){
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"공지사항 삭제 성공",userService.deleteUser(userId)));
    }

    // 크루 삭제
    @DeleteMapping("/list/{crewId}/crewdelete")
    public ResponseEntity<ResponseDTO> deleteCrew(@PathVariable("crewId") Integer crewId){
        System.out.println("crewId = " + crewId);
        return ResponseEntity.ok().body(
                new ResponseDTO(HttpStatus.OK,"크루 삭제 성공",crewService.deleteCrew(crewId)));
    }

    // 신고 목록
    @GetMapping("/report")
    public ResponseEntity<ResponseDTO> reportList(){
        List<ReportDTO> reportList = reportService.findAllReportList();


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "전체 크루 리스트 조회 성공", reportList));
    }
}
