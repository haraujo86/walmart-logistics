package walmart.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import walmart.delivery.model.CallBack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController {

	private static final String ERROR_SEPARATOR = ": ";
	
	public static final String getErrorMessageAsJson(String message) {
		 try {
			 ObjectMapper mapper = new ObjectMapper();
			 CallBack cb = new CallBack();
			 cb.setStatus(CallBack.NOK_STATUS);
			 cb.getMessages().add(message);
			 return mapper.writeValueAsString(cb);
		 } catch (JsonProcessingException e) {
			 return null;
		 }
	}
	
	public static final String getErrorMessageAsJson(BindingResult errors) {
		 try {
			 ObjectMapper mapper = new ObjectMapper();
			 CallBack cb = new CallBack();
			 cb.setStatus(CallBack.NOK_STATUS);
			 cb.setMessages(getErrorsAsList(errors));
			 return mapper.writeValueAsString(cb);
		 } catch (JsonProcessingException e) {
			 return null;
		 }
	}
	
	private static List<String> getErrorsAsList(BindingResult errors) {
		if (errors != null && errors.hasErrors()) {
			List<String> errList = new ArrayList<String>();
			for (FieldError error : errors.getFieldErrors()) {
				errList.add(error.getField() + ERROR_SEPARATOR + error.getDefaultMessage());
			}
			return errList;
		}
		return null;
	}
	
}
