package CHOIGANGMEDIA.CAUCLUB.controller;

import CHOIGANGMEDIA.CAUCLUB.service.FireBaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FireBaseController {

    private final FireBaseService fireBaseService;
    public FireBaseController(FireBaseService fireBaseService) {this.fireBaseService = fireBaseService;}

    @ResponseBody
    @PostMapping("/files")
    public String uploadFile(@RequestParam("file") MultipartFile file, String nameFile) throws IOException, FirebaseAuthException {
        if (file.isEmpty()) {
            return "is empty";
        }
        return fireBaseService.uploadFiles(file, nameFile);
    }
}
