package com.agiantagravic.cms.controller;

import com.agiantagravic.cms.data.Post;
import com.agiantagravic.cms.data.PostRepository;
import com.agiantagravic.cms.data.Tag;
import com.agiantagravic.cms.data.TagRepository;
import com.agiantagravic.cms.http.*;
import com.agiantagravic.cms.service.MediaCloud;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping(value = "/api/tag")
@Controller
@Component
public class TagController {

  @Autowired
  TagResponseFactory tagResponseFactory;

  @Autowired
  PostRepository  postRepo;

  @Autowired
  TagRepository repo;


}