package com.rakshamitra.RakshaMitra.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rakshamitra.RakshaMitra.model.Notice;
import com.rakshamitra.RakshaMitra.repository.NoticeRepository;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    public List<Notice> getNoticesByAgencyId(Long agencyId) {
        return noticeRepository.findByAgencyId(agencyId);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }
}
