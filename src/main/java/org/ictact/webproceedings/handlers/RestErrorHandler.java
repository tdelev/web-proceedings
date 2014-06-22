package org.ictact.webproceedings.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.ictact.webproceedings.exceptions.AttachmentBlobCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.snt.hr.web.validation.UniqueFieldValidator;

@ControllerAdvice
public class RestErrorHandler {

	private final Logger log = LoggerFactory.getLogger(RestErrorHandler.class);

	private MessageSource messageSource;

	private UniqueFieldValidator uniqueFieldValidator;

	@Autowired
	public RestErrorHandler(MessageSource messageSource,
			UniqueFieldValidator uniqueFieldValidator) {
		this.messageSource = messageSource;
		this.uniqueFieldValidator = uniqueFieldValidator;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(uniqueFieldValidator);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> processValidationError(
			MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		List<ObjectError> globalErrors = result.getGlobalErrors();

		Map<String, Object> errors = processFieldErrors(fieldErrors);

		errors.put("_objectErrors", processObjectErrors(globalErrors));

		return errors;
	}

	@ExceptionHandler(AttachmentBlobCreationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> invalidAttachment(
			AttachmentBlobCreationException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("_objectErrors", ex.getClass().getSimpleName());
		return errors;
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> constraintViolation(
			DataIntegrityViolationException ex) {
		ex.printStackTrace();
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("_objectErrors", ex.getClass().getSimpleName());
		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void handle(HttpMessageNotReadableException e) {
		log.warn("Returning HTTP 400 Bad Request", e);
	}

	private Map<String, Object> processFieldErrors(List<FieldError> fieldErrors) {
		Map<String, Object> errors = new HashMap<String, Object>();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			errors.put(fieldError.getField(), localizedErrorMessage);
		}

		return errors;
	}

	private Map<String, Object> processObjectErrors(
			List<ObjectError> fieldErrors) {
		Map<String, Object> errors = new HashMap<String, Object>();

		for (ObjectError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			errors.put(fieldError.getObjectName(), localizedErrorMessage);
		}

		return errors;
	}

	private String resolveLocalizedErrorMessage(ObjectError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError,
				currentLocale);

		// If the message was not found, return the most accurate field error
		// code instead.
		// You can remove this check if you prefer to get the default error
		// message.
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}

		return localizedErrorMessage;
	}
}
