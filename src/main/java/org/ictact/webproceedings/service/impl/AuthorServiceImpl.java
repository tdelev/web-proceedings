package org.ictact.webproceedings.service.impl;

import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.repository.AuthorRepository;
import org.ictact.webproceedings.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends
		BaseEntityCrudServiceImpl<Author, AuthorRepository> implements AuthorService {


	@Autowired
	private AuthorRepository repository;

	@Override
	protected AuthorRepository getRepository() {
		return repository;
	}


}
