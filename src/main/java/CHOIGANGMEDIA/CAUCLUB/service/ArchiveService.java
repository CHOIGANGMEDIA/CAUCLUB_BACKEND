package CHOIGANGMEDIA.CAUCLUB.service;

import CHOIGANGMEDIA.CAUCLUB.domain.Archive;
import CHOIGANGMEDIA.CAUCLUB.repository.ArchiveRepository;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    public void registerNewArchive(Archive archive) throws Exception{
        archiveRepository.registerNewArchive(archive);
    }


}
