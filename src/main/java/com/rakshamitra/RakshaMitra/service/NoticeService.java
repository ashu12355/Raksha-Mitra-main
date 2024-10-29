package com.rakshamitra.RakshaMitra.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rakshamitra.RakshaMitra.model.Notice;
import com.rakshamitra.RakshaMitra.model.Volunteer;
import com.rakshamitra.RakshaMitra.repository.NoticeRepository;
import com.rakshamitra.RakshaMitra.repository.VolunteerRepository;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private MailService mailService;

    public Notice saveNotice(Notice notice) {
        Notice savedNotice = noticeRepository.save(notice);

        // Get all approved volunteers' email addresses
        List<String> volunteerEmails = volunteerRepository.findAll().stream()
                .filter(Volunteer::isApproved)
                .map(Volunteer::getEmail)
                .collect(Collectors.toList());

        // Send email to all approved volunteers
        String subject = "New Notice: " + notice.getTitle();
        String content = "A new notice has been posted:\n\n" + notice.getDescription();
        mailService.sendMailToVolunteers(subject, content, volunteerEmails);

        return savedNotice;
    }

    public List<Notice> getNoticesByAgencyId(Long agencyId) {
        return noticeRepository.findByAgencyId(agencyId);
    }

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }
}
