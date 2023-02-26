package com.gurus.mobility.controller.user;

import com.gurus.mobility.dto.UserCreationDto;
import com.gurus.mobility.dto.UserList;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.mail.RequestMail;
import com.gurus.mobility.mail.SendMailService;
import com.gurus.mobility.payload.response.MessageResponse;
import com.gurus.mobility.service.User.IUserService;
import com.gurus.mobility.service.User.ImageService;
import com.gurus.mobility.service.User.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class UserController {


    private IUserService userService;

    @Autowired
    private SendMailService sendMailService;

    private ImageService imageService;


    @Autowired
    public UserController(UserServiceImpl userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/home")
    public String greeting() {
        return "Hello nadine";
    }

    @GetMapping("users")
    public UserList getAllUsers() {
        return new UserList(userService.getAllUsers());
    }


    @GetMapping("user/{id}")
    public User getUserById(@PathVariable(value = "id") Integer id) {
        return userService.getUserByID(id);
    }

    @PutMapping("updateUser/{ident}")
    public User updateUser(@PathVariable(name = "ident") String identifiant, @RequestBody User updateuser) {
        return userService.updateUser(updateuser, identifiant);
    }

    @PutMapping("/{identifiant}/activate-account")
    public User activateAccount(@PathVariable String identifiant, @RequestParam Boolean active) {
        User user = userService.ActivateAccount(active, identifiant);
        RequestMail requestMail = new RequestMail();
        requestMail.setSendFrom("nadine.mili@esprit.tn");
        requestMail.setSendTo(user.getEmail());
        requestMail.setSubject("Activation de compte");
        if (active) {
            requestMail.setContent("Votre compte est maintenant activé, vous pouvez connecter à tous moment");
        }
        else {
            requestMail.setContent("Votre compte est maintenant désactivé, merci pour votre fidélisation!");
        }
        sendMailService.sendMail(requestMail);
        return user;
    }

/*
    @GetMapping(value = "/image")
    public @ResponseBody byte[] getImage() throws IOException {
        FileInputStream in = new FileInputStream("file:///C://Users//Nadine//Downloads//ERPCurentAjourdemo//ERPCurentAjour//ERPCurent//ERP//images//manel.PNG");
        return IOUtils.toByteArray(in);
    }

*/
    /*
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<UserCreationDto> addUser(@ModelAttribute User user, MultipartFile file) {
        String fileName = imageService.storeFile(file);
        return new ResponseEntity(new UserCreationDto(userService.addUser(user), "user succsusfully added to database"), HttpStatus.CREATED);
    }
    */

/*
    @GetMapping("downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        // Load file as Resource
        Resource resource = imageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;

        contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

*/


}
