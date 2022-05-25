package CHOIGANGMEDIA.CAUCLUB.repository;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;

public interface ArchiveRepository {
    String registerNewArchive(Archive archive) throws Exception;
    Boolean deleteArchive(int archiveId) throws Exception;
}
