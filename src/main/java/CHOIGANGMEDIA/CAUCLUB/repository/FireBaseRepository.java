package CHOIGANGMEDIA.CAUCLUB.repository;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FireBaseRepository {
    public String uploadFile(@RequestParam("file") MultipartFile file, String nameFile) throws IOException, FirebaseAuthException;
}
