package com.mentaljava.mentaljavarestapiproject.table.notice.controller;

import com.mentaljava.mentaljavarestapiproject.common.ResponseDTO;
import com.mentaljava.mentaljavarestapiproject.table.admin.entity.Admin;
import com.mentaljava.mentaljavarestapiproject.table.admin.service.AdminService;
import com.mentaljava.mentaljavarestapiproject.table.notice.dto.NoticeDTO;
import com.mentaljava.mentaljavarestapiproject.table.notice.entity.Notice;
import com.mentaljava.mentaljavarestapiproject.table.notice.service.NoticeService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final AdminService adminService;

    @GetMapping("")
    public String noticeNav() {
        return "notice/noticeForm";
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO> noticeList() {
        List<NoticeDTO> noticeList = noticeService.findNotice();
        System.out.println("noticeList = " + noticeList);
//        model.addAttribute("notices", noticeList);
//        return "notice/noticeList";

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", noticeList));
    }

    @GetMapping("/list/{noticeId}/edit")
    public String updateNotice(@PathVariable("noticeId") Integer noticeId, Model model) {
        Notice one = noticeService.findOne(noticeId);

        if (one != null) {
            NoticeDTO form = new NoticeDTO();
            form.setNoticeId(one.getNoticeId());
            form.setNoticeTitle(one.getNoticeTitle());
            form.setNoticeContent(one.getNoticeContent());
            form.setAdminId(one.getAdminId());
            form.setNoticeDate(one.getNoticeDate());

            model.addAttribute("form", form);
            return "notice/update";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/list/{noticeId}/edit")
    public String registNotice(@PathVariable("noticeId") Integer noticeId, @ModelAttribute("form") NoticeDTO form) {
        noticeService.updateNotice(noticeId, form.getNoticeTitle(), form.getNoticeContent());
        return "redirect:/notice";
    }

    @GetMapping("/list/{noticeId}/delete")
    public String deleteNotice(@PathVariable("noticeId") Integer noticeId){
        noticeService.deleteNotice(noticeId);
        return "redirect:/notice";
    }

    @GetMapping("/regist")
    public String noticeAdd(Model model) {
        return "notice/noticeRegistForm";
    }


    // 이부분이 작동되지 않음.
    @PostMapping("/regist")
    public String Create(@RequestParam("adminId") String adminId,
                         @RequestParam("noticeTitle") String noticeTitle,
                         @RequestParam("noticeContent") String noticeContent) {
        noticeService.registNotice(adminId,noticeTitle,noticeContent);
        return "redirect:/notice";
    }

}
