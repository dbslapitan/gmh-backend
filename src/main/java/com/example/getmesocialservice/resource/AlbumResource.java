package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.CredentialNotMatchException;
import com.example.getmesocialservice.exception.InvalidTokenException;
import com.example.getmesocialservice.model.Album;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.service.AlbumService;
import com.example.getmesocialservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
@CrossOrigin
public class AlbumResource {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public Album createAlbum(@RequestBody @Valid Album album, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            album.setCreatedBy(firebaseUser.getEmail());
            return albumService.createAlbum(album);
        }
        return null;
    }

    @GetMapping
    public List<Album> getAllAlbum(@RequestHeader(name = "idToken") String idToken){
       System.out.println(idToken);
        return albumService.getAllAlbum();
    }

    @GetMapping("/{albumId}")
    public Album getAlbumById(@PathVariable("albumId") String albumId){
        return albumService.getAlbumById(albumId).get();
    }

    @PutMapping
    public Album updateAlbum(@RequestBody @Valid Album album, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException, CredentialNotMatchException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser.getEmail().matches(album.getCreatedBy())){
            return albumService.updateAlbum(album);
        }
        throw new CredentialNotMatchException();
    }

    @DeleteMapping
    public void deleteAlbum(@RequestParam(name = "albumId") String albumId, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException, CredentialNotMatchException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Album albumToDelete = albumService.getAlbumById(albumId).get();

        if(albumToDelete.getCreatedBy().matches(firebaseUser.getEmail())){
            albumService.deleteAlbum(albumId);
        }
        else {
            throw new CredentialNotMatchException();
        }
    }
}
