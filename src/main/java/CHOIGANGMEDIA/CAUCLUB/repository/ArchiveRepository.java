package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;

import java.util.ArrayList;

public interface ArchiveRepository {
    String registerNewArchive(Archive archive) throws Exception;
    Boolean deleteArchive(int archiveId) throws Exception;
    Boolean modifyArchive(String title, ArrayList<String> pictureUrls, int archiveId, String contents) throws Exception;
}
