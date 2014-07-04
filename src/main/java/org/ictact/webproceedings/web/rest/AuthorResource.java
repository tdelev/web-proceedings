package org.ictact.webproceedings.web.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.ictact.webproceedings.model.Author;
import org.ictact.webproceedings.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/data/rest/authors")
public class AuthorResource {

	@Autowired
	private AuthorService service;

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public Author create(@RequestBody @Valid Author entity) {
		service.save(entity);
		return entity;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAll() {
		Collection<Author> authors = service.findAll();
		return new ArrayList<Author>(authors);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Author get(@PathVariable Long id, HttpServletResponse response) {
		Author author = service.findById(id);
		if (author == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return author;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		service.delete(id);
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<Author> importCSV(MultipartFile file) throws IOException {
		List<Author> result = new ArrayList<Author>();
		if (file != null) {
			Scanner scanner = new Scanner(file.getInputStream());
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(";");
				String name = parts[1];
				String[] namep = name.split(" ");
				String firstName = namep[0];
				String lastName = namep[1];
				String email = parts[2];
				String country = parts[3];
				String affiliation = parts[4];
				Author author = service.findByFirstNameAndLastName(firstName,
						lastName);
				if (author == null) {
					author = new Author();
				}
				author.setFirstName(firstName);
				author.setLastName(lastName);
				author.setEmail(email);
				author.setCountry(country);
				author.setAffiliation(affiliation);
				service.save(author);
				result.add(author);
			}
		} else {
			return null;
		}
		return result;
	}

}
