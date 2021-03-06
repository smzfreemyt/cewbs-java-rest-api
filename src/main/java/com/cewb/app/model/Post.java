package com.cewb.app.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "title")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @Column(name = "body")
    @NotEmpty(message = "Body must not be empty")
    private String body;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    @NotNull(message = "Category id must not be empty")
    private Category category;

    @Transient
    @NotNull(message = "Category id must not be empty")
    private long category_id;
    
    @JsonIgnore
    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "created_by", updatable = false)
    @NotNull(message = "User id must not be empty")
    private User user;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Date date_created;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}
