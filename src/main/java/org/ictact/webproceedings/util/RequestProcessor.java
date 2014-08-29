package org.ictact.webproceedings.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.ictact.webproceedings.model.Paper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class RequestProcessor {
	public static Sort sorting(HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("sorting")) {
				String field = key.substring(key.indexOf("[") + 1,
						key.indexOf("]"));
				String direction = request.getParameter(key);
				Sort sort = new Sort(Sort.Direction.fromString(direction),
						field);
				return sort;
			}
		}
		return new Sort("id");
	}

	public static Specification<Paper> getSpecification(
			HttpServletRequest request) {
		Enumeration<String> keys = request.getParameterNames();
		Specification<Paper> result = null;
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			if (key.startsWith("filter")) {
				String field = key.substring(key.indexOf("[") + 1,
						key.indexOf("]"));
				String value = request.getParameter(key);
				if (field.equals("title")) {
					result = Specifications.where(result).and(
							PaperSpecifications.titleLike(value));
				}
				if (field.equals("conference")) {
					Long id = Long.parseLong(value);
					result = Specifications.where(result).and(
							PaperSpecifications.conference(id));
				}
				if (field.equals("type")) {
					Long id = Long.parseLong(value);
					result = Specifications.where(result).and(
							PaperSpecifications.paperType(id));
				}
			}
		}
		return result;
	}

}
