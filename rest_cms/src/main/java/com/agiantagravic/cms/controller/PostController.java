package com.agiantagravic.cms.controller;

import com.agiantagravic.cms.data.*;
import com.agiantagravic.cms.http.Paginator;
import com.agiantagravic.cms.http.PostRequest;
import com.agiantagravic.cms.http.PostResponseFactory;
import com.agiantagravic.cms.http.Response;
import com.agiantagravic.cms.service.MediaCloud;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping(value = "/post")
@RestController
public class PostController {

  @Autowired
  PostResponseFactory postResponseFactory;

  @Autowired
  PostRepository repo;

  @Autowired
  TagRepository tagRepo;

  @RequestMapping(value = "/photo", method = RequestMethod.POST)
  @ResponseBody
  public Map createPhotos(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, MediaCloud cloud) {
    Map response = new HashMap();
    try {
      String tmpPath = System.getProperty("java.io.tmpdir");
      File newFile = new File(String.format("%s\\%s", tmpPath, file.getOriginalFilename()));
      if(newFile.exists()){
        if(newFile.delete()){
          file.transferTo(newFile);
        }
      } else {
        file.transferTo(newFile);
      }
      response = cloud.photoUpload(newFile);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Post> getPost(@PathVariable(value = "id") Long postId){
    Post post = repo.findById(postId).get();
    return ResponseEntity.ok().body(post);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Post> deletePost(@PathVariable(value = "id") Long postId){
    Post post = repo.findById(postId).get();
    repo.delete(post);
    return ResponseEntity.ok().body(post);
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ResponseEntity<PostRequest> createPost(@Valid @RequestBody PostRequest req){
    Post post = req.getPost();
  Set<Tag> tags = req.getTags();
    post.setTags(tags);
    repo.save(post);
    return new ResponseEntity<PostRequest>(req, HttpStatus.OK);
  }


  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public @ResponseBody ResponseEntity<PostRequest> updatePost(@Valid @RequestBody PostRequest req){
    return createPost(req);
  }


  @RequestMapping(value = "/initialize", method = RequestMethod.GET)
  public Response<List<Post>> initData() {
    List<Post> posts = new ArrayList<>();
    Lorem lorem = LoremIpsum.getInstance();
    try {
      for (int i = 0; i < 10; i++) {
        Post post = new Post();
        post.setContent(lorem.getParagraphs(2, 4));
        post.setTitle(lorem.getTitle(2));
        post.setSubtitle(lorem.getTitle(5));
        List<Tag> tags =
            new ArrayList<Tag>(Arrays.asList(new Tag(), new Tag(), new Tag()))
                .stream().map(tag -> tag.getId() != null ? tagRepo.findById(tag.getId()).get() : tag)
                .collect(Collectors.toList());
        repo.save(post);
        tags.forEach(tag -> {
              tag.setName(lorem.getTitle(1, 2));
              tagRepo.save(tag);
              post.getTags().add(tag);
            }
        );
        posts.add(post);
      }

    } catch (Exception e){
    }

    return new Response<List<Post>>("200", "Success", posts);
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseEntity<Paginator> list(Pageable pageable) {
    Page<Post> page = repo.findAll(pageable);
    Paginator paginator = postResponseFactory.paginator(page);
    return new ResponseEntity<Paginator>(paginator, HttpStatus.OK);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public ResponseEntity<List<Post>> list() {
    List<Post> posts = repo.findAll();
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

}