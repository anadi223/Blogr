package com.blogr.payloads;

import com.blogr.entities.Category;
import com.blogr.entities.Comments;
import com.blogr.entities.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String imageURL;
    private Date postAdded;
    private CategoryDto category; //if you add category instead of categoryDto and UserDto you will get stack overflow error
    //as you have declared a mapping in category and user class related to posts for creating a new list
    private UserDTO user;
}

