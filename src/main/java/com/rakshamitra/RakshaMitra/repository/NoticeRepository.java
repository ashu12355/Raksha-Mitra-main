package com.rakshamitra.RakshaMitra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rakshamitra.RakshaMitra.model.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByAgencyId(Long agencyId);  // Fetch notices by agency
}
