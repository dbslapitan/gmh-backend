package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.CredentialNotMatchException;
import com.example.getmesocialservice.exception.InvalidTokenException;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.model.Photo;
import com.example.getmesocialservice.service.FirebaseService;
import com.example.getmesocialservice.service.PhotoService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/photos")
public class PhotoResource {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public Photo createPhoto(@RequestBody @Valid Photo photo, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            photo.setCreatedBy(firebaseUser.getEmail());
            return photoService.createPhoto(photo);
        }
        return null;
    }

    @GetMapping
    public List<Photo> getAllPhoto(){
        return photoService.getAllPhoto();
    }

    @GetMapping("/{photoId}")
    public Photo getPhotoById(@PathVariable("photoId") String photoId){
        return photoService.getPhotoById(photoId).get();
    }

    @PutMapping
    public Photo updatePhoto(@RequestBody @Valid Photo photo, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException, CredentialNotMatchException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser.getEmail().matches(photo.getCreatedBy())){
            return photoService.updatePhoto(photo);
        }
        throw new CredentialNotMatchException();
    }

    @DeleteMapping
    public void deletePhoto(@RequestParam(name = "photoId") String photoId, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException, CredentialNotMatchException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Photo photoToDelete = photoService.getPhotoById(photoId).get();
        if(photoToDelete.getCreatedBy().matches(firebaseUser.getEmail())){
            photoService.deletePhoto(photoId);
        }
        else {
            throw new CredentialNotMatchException();
        }
    }
}
