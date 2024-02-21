package com.task.weeklytaskspringboot.mappings;

import com.task.weeklytaskspringboot.entities.Book;
import com.task.weeklytaskspringboot.payloads.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookDTO bookDTO);

    BookDTO toDTO(Book book);
}
