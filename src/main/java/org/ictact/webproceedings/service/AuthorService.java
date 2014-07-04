package org.ictact.webproceedings.service;

import org.ictact.webproceedings.model.Author;

public interface AuthorService extends BaseEntityCrudService<Author> {

	Author findByFirstNameAndLastName(String firstName, String lastName);


}
