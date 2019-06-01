package com.agiantagravic.cms.controller;

import com.agiantagravic.cms.data.*;
import com.agiantagravic.cms.data.Content;
import com.agiantagravic.cms.http.ContentResponseFactory;
import com.agiantagravic.cms.http.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/content")
@RestController
public class ContentController {

    @Autowired
    ContentRepository repo;

    @Autowired
    ContentResponseFactory contentResponseFactory;

    @RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<Content> createContent(@Valid @RequestBody Content req){
        repo.save(req);
        return new ResponseEntity<Content>(req, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Paginator> list(Pageable pageable) {
        Page<Content> page = repo.findAll(pageable);
        Paginator paginator = contentResponseFactory.paginator(page);
        return new ResponseEntity<Paginator>(paginator, HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Content>> list() {
        List<Content> contents = repo.findAll();
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Content> getContent(@PathVariable(value = "id") Long contentId){
        Content content = repo.findById(contentId).get();
        return ResponseEntity.ok().body(content);
    }
}
