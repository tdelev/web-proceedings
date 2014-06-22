package com.snt.hr.web.validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UniqueFieldValidator implements Validator, ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void validate(Object target, Errors errors) {

		try {
			Field[] fields = target.getClass().getDeclaredFields();
			for (Field field : fields) {
				UniqueField annotation = field.getAnnotation(UniqueField.class);
				if (annotation != null) {
					String fieldName = field.getName();
					field.setAccessible(true);
					Object value = field.get(target);

					Class repositoryClass = annotation.repositoryClass();

					Object repository = context.getBean(repositoryClass);

					Method method = repositoryClass.getMethod(
							annotation.methodName(), field.getType());

					List<?> result = (List<?>) method.invoke(repository, value);
					if (result != null && !result.isEmpty()) {
						for (Object o : result) {
							if (!target.equals(o)) {
								errors.rejectValue(fieldName, "UniqueField",
										"UniqueField");
								break;
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			errors.reject(ex.getClass().getName());
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println("Setting context in UniqueFieldValidator!!!");
		this.context = applicationContext;
	}

}
