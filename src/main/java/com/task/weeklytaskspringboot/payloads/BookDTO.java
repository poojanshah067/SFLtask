package com.task.weeklytaskspringboot.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

    private Long bookId;

    @NotEmpty(message = "BookName should not be empty!!")
    private String bookName;

    @NotEmpty(message = "Book description should not be empty!!")
    @Size(min = 10, max = 500, message = "Description should be of minimum 10 and maximum 500 characters!!")
    private String bookDescription;

}
