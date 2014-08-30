package org.ictact.webproceedings.service;

import java.util.List;

import org.ictact.webproceedings.model.Author;
import org.springframework.data.domain.Sort;

public interface AuthorService extends BaseEntityCrudService<Author> {

	Author findByFirstNameAndLastName(String firstName, String lastName);

	List<Author> findAll(Sort sort);

}
