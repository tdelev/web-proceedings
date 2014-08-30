package org.ictact.webproceedings.service.impl;

import java.util.List;

import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.repository.AuthorRepository;
import org.ictact.webproceedings.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends
		BaseEntityCrudServiceImpl<Author, AuthorRepository> implements
		AuthorService {

	@Autowired
	private AuthorRepository repository;

	@Override
	protected AuthorRepository getRepository() {
		return repository;
	}

	@Override
	public Author findByFirstNameAndLastName(String firstName, String lastName) {
		return repository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Author> findAll(Sort sort) {
		return repository.findAll(sort);
	}

}
