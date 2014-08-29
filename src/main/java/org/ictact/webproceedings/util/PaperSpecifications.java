package org.ictact.webproceedings.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.ictact.webproceedings.model.Paper;
import org.ictact.webproceedings.model.Paper_;
import org.springframework.data.jpa.domain.Specification;

public class PaperSpecifications {

	public static Specification<Paper> titleLike(final String searchTerm) {
		return new Specification<Paper>() {

			@Override
			public Predicate toPredicate(Root<Paper> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likePattern = String.format("%%%s%%",
						searchTerm.toLowerCase());
				return cb.like(cb.lower(root.<String> get(Paper_.title)),
						likePattern);
			}

		};
	}
}
