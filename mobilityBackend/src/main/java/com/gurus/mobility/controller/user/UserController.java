package com.gurus.mobility.controller.user;

import com.gurus.mobility.dto.UserList;
import com.gurus.mobility.entity.user.FileDB;
import com.gurus.mobility.entity.user.User;
import com.gurus.mobility.payload.response.MessageResponse;
import com.gurus.mobility.repository.AccomodationRepository.AccomodationRepository;
import com.gurus.mobility.repository.User.FileDBRepository;
import com.gurus.mobility.repository.User.UserRepository;
import com.gurus.mobility.service.User.IFileStorageService;
import com.gurus.mobility.service.User.IUserService;
import com.gurus.mobility.service.User.ImageService;
import com.gurus.mobility.service.User.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api")
@NoArgsConstructor
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileDBRepository ImageRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;


    private ImageService imageService;

    @Autowired
    private IFileStorageService storageService;

    @Autowired
    public UserController(UserServiceImpl userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }


    @GetMapping("users")
    public UserList getAllUsers() {
        return new UserList(userService.getAllUsers());
    }


    @GetMapping("user/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("updateUser/{idUser}")
    public User updateUser(@PathVariable Long idUser, @RequestBody User updateuser) {
        return userService.updateUser(updateuser, idUser);
    }

    @PutMapping("Activate/{idUser}")
    public String Activate(@PathVariable Long idUser)
    {
     return   userService.Verified(idUser);
    }

    @PostMapping("/{idUser}/uploadimage")
    public ResponseEntity<MessageResponse> uploadFile(@PathVariable Long idUser, @RequestParam("file") MultipartFile file) {


        String message = "";
        try {
            storageService.store(file);
            FileDB image = ImageRepository.findByName(file.getOriginalFilename());
            User user = getUserById(idUser);
            user.setImage(image);
            userRepository.save(user);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }
    @GetMapping("/getUserByIdAndRole/{id}")
    public User getUserByIdAndRole(@PathVariable("id") Long id){
        return userService.getUserByIdAndRole(id);
    }

    @GetMapping("Getlastclaimsbyuser/{userid}")
    public int GetNBClaimsLastDate (@PathVariable Long userid)
    {
        LocalDateTime date = LocalDateTime. of(2022, 11, 26, 13, 55, 36, 123);
       return  userService.NBClaimsLastDate(date, userid);
    }
}
