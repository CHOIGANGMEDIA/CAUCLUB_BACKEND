package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.repository.ArchiveRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    public void registerNewArchive(Archive archive) throws Exception{
        archiveRepository.registerNewArchive(archive);
    }

    public boolean deleteArchive(int archiveId) throws Exception{
        return archiveRepository.deleteArchive(archiveId);
    }

    public boolean modifyArchive(String title, ArrayList<String> pictureUrls, int archiveId, String contents) throws Exception{
        return archiveRepository.modifyArchive(title,pictureUrls,archiveId,contents);
    }

    public Archive getArchiveObject(int archiveId) throws Exception{
        return archiveRepository.getArchiveObject(archiveId);
    }

    public String getClubName(int clubId) throws Exception{
        return archiveRepository.getClubName(clubId);
    }
}
