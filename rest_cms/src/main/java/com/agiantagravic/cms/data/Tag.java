package com.agiantagravic.cms.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tag")
public class Tag implements Serializable{

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(mappedBy = "tags")
  @JsonIgnore
  private Set<Post> posts = new HashSet<>();

  @Column
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
