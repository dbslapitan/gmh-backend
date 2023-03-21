package com.example.getmesocialservice.resource;

import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.S3Object;
import com.example.getmesocialservice.exception.InvalidTokenException;
import com.example.getmesocialservice.service.FileService;
import com.example.getmesocialservice.service.FirebaseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("api/files")
public class FileResource {

    @Autowired
    private FileService fileService;
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping(consumes = "multipart/form-data")
    public boolean upload(@Parameter(
            description = "Files to be uploaded",
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
    ) @RequestPart (value = "files", required = true) MultipartFile file) throws InvalidTokenException, IOException {
        return fileService.upload(file);
    }

    @GetMapping("/view")
    public void view(@RequestParam(name = "key") String key, HttpServletResponse response) throws IOException {
        S3Object object = fileService.getFile(key);
        response.setContentType(object.getObjectMetadata().getContentType());
        response.getOutputStream().write(object.getObjectContent().readAllBytes());
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam(name = "key") String key) throws IOException {
        S3Object object = fileService.getFile(key);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(object.getObjectMetadata().getContentType()))
                .header(Headers.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .body(new ByteArrayResource(object.getObjectContent().readAllBytes()));
    }

    @DeleteMapping
    public void delete(@RequestParam(name = "key") String key) throws InvalidTokenException, IOException {
        fileService.deleteFile(key);
    }
}
