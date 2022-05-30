package CHOIGANGMEDIA.CAUCLUB.repository;

public interface ReportRepository {
    Boolean reportArchive(String memberId, int archiveId, int clubId) throws Exception;
    Boolean reportPost(String memberId, int postId, int clubId) throws Exception;
    void reportClub(int clubId) throws Exception;
}
