package com.mentaljava.mentaljavarestapiproject.table.notice.service;

import com.mentaljava.mentaljavarestapiproject.table.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

}