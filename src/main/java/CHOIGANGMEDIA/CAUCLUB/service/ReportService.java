package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public boolean reportArchive(String memberId, int archiveId, int clubId) throws Exception{
        return reportRepository.reportArchive(memberId, archiveId, clubId);
    }

    public boolean reportPost(String memberId, int postId, int clubId) throws Exception{
        return reportRepository.reportPost(memberId, postId, clubId);
    }
}
