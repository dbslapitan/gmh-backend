package com.example.getmesocialservice;

import com.example.getmesocialservice.model.Album;
import com.example.getmesocialservice.service.AlbumService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @Before
    @Test
    public void saveUser(){
        Album sampleAlbum = new Album("New Album", "New album.", "uri", "dbslapitan@gmail.com");
        sampleAlbum.setId("1");
        albumService.createAlbum(sampleAlbum);
    }

    @Test
    public void getUser(){
        Album album = albumService.getAlbumById("1").get();
        Assert.assertEquals("New Album", album.getName());
        Assert.assertEquals("New album.", album.getDescription());
        Assert.assertEquals("uri", album.getCoverPhotoUrl());
        Assert.assertEquals("dbslapitan@gmail.com",album.getCreatedBy());
    }

    @Test
    public void updateUser(){
        Album album = albumService.getAlbumById("1").get();
        album.setDescription("Description Changed");
        albumService.updateAlbum(album);
        Assert.assertEquals("New Album", album.getName());
        Assert.assertEquals("Description Changed", album.getDescription());
        Assert.assertEquals("uri", album.getCoverPhotoUrl());
        Assert.assertEquals("dbslapitan@gmail.com",album.getCreatedBy());
    }

    @After
    public void delete(){
        albumService.deleteAlbum("1");
    }
}
