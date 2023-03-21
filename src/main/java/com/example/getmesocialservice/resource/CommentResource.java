package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.exception.CredentialNotMatchException;
import com.example.getmesocialservice.exception.InvalidTokenException;
import com.example.getmesocialservice.model.Comment;
import com.example.getmesocialservice.model.FirebaseUser;
import com.example.getmesocialservice.service.CommentService;
import com.example.getmesocialservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/comments")
public class CommentResource {

    @Autowired
    private CommentService commentService;
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public Comment createComment(@RequestBody @Valid Comment comment, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            comment.setCreatedBy(firebaseUser.getEmail());
            return commentService.createComment(comment);
        }
        return null;
    }

    @GetMapping
    public List<Comment> getAllComment(){
        return commentService.getAllComment();
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable("commentId") String commentId){
        return commentService.getCommentById(commentId).get();
    }

    @PutMapping
    public Comment updateComment(@RequestBody @Valid Comment comment, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException, CredentialNotMatchException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(comment.getCreatedBy().matches(firebaseUser.getEmail())){
            return commentService.updateComment(comment);
        }
        throw new CredentialNotMatchException();
    }

    @DeleteMapping
    public void deleteComment(@RequestParam(name = "commentId") String commentId, @RequestHeader(name = "idToken") String idToken) throws InvalidTokenException, IOException, FirebaseAuthException, CredentialNotMatchException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        Comment commentToDelete = commentService.getCommentById(commentId).get();
        if(commentToDelete.getCreatedBy().matches(firebaseUser.getEmail())){
            commentService.deleteComment(commentId);
        }
        else {
            throw new CredentialNotMatchException();
        }
    }
}
