package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ArchiveRepository {
    String registerNewArchive(Archive archive) throws Exception;
    Boolean deleteArchive(int archiveId) throws Exception;
    Boolean modifyArchive(String title, ArrayList<String> pictureUrls, int archiveId, String contents, int isMutual) throws Exception;
    Archive getArchiveObject(int archiveId) throws Exception;
    String getClubName(int clubId) throws Exception;
    Boolean likeArchive(int archiveId, String memberId) throws Exception;
    List<HashMap<String, Object>> viewAllArchive() throws Exception;
    List<Archive> viewMyClubArchive(int clubId) throws Exception;
    int setArchivePk() throws Exception;
    void plusArchivePk() throws Exception;
    void plusScoreByArchive(int clubId, int isMutual) throws Exception;
}
