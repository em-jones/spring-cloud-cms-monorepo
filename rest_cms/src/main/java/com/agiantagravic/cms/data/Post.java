package com.agiantagravic.cms.data;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
//for bi-directional deserialization
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Post implements Serializable, CmsItem{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  private String title;

  private String subtitle;

  private int wordCount;

  @NotBlank
  @Type(type="text")
  private String content;

  @Type(type="boolean")
  private boolean draft;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "post_tag",
      joinColumns = { @JoinColumn(name = "post_id") },
      inverseJoinColumns = { @JoinColumn(name = "tag_id") }
  )
  private Set<Tag> tags = new HashSet<>();

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Set<Tag> getTags() {
    return tags;
  }

  public void setTags(Set<Tag> tags) {
    this.tags = tags;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public boolean isDraft() {
    return draft;
  }

  public void setDraft(boolean draft) {
    this.draft = draft;
  }

  public int getWordCount() {
    return wordCount;
  }

  public void setWordCount(int wordCount) {
    this.wordCount = wordCount;
  }
}
