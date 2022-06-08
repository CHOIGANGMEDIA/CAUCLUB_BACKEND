package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.repository.ArchiveRepository;
import com.google.firebase.database.utilities.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public boolean modifyArchive(String title, ArrayList<String> pictureUrls, int archiveId, String contents, int isMutual) throws Exception{
        return archiveRepository.modifyArchive(title,pictureUrls,archiveId,contents, isMutual);
    }

    public Archive getArchiveObject(int archiveId) throws Exception{
        return archiveRepository.getArchiveObject(archiveId);
    }

    public String getClubName(int clubId) throws Exception{
        return archiveRepository.getClubName(clubId);
    }

    public boolean likeArchive(int archiveId, String memberId) throws Exception{
        return archiveRepository.likeArchive(archiveId,memberId);
    }

    public List<HashMap<String, Object>> viewAllArchive() throws Exception{
        return archiveRepository.viewAllArchive();
    }

    public List<Pair<Integer, String>> viewMyClubArchiveList(int clubId) throws Exception{
        return archiveRepository.viewMyClubArchive(clubId);
    }

    public int setArchivePk() throws Exception{
        return archiveRepository.setArchivePk();
    }

    public void getScoreByArchive(int clubId, int isMutual) throws Exception{
        archiveRepository.plusScoreByArchive(clubId, isMutual);
    }
}
