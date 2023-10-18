package com.blogr.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int id;

    @NotBlank
    @Size(min = 4)
    private String title;
    @NotBlank
    @Size(min = 5)
    private String description;
}
