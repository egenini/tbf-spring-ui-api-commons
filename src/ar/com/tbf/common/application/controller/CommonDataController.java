package ar.com.tbf.common.application.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.tbf.common.application.Constants;
import ar.com.tbf.common.share.CommonDataAccesibility;

@ControllerAdvice
@Controller
public class CommonDataController {

	@ModelAttribute
	public void getCommonData( 
			Model model, 
			@PathVariable( name = "tenant", required=false ) String tenantCode, 
			@RequestParam(  required = false, defaultValue = "") String fieldsFilter,
			@RequestHeader( required = false, defaultValue = "") String ifModifiedSince
			) {
	
		if( ! ifModifiedSince.isEmpty() ) {
			try {
				model.addAttribute("ifModifiedSince", Constants.DATE_ISO.parse( ifModifiedSince ));
			} catch (ParseException e) {
			}
		}
		
		if( ! fieldsFilter.isEmpty() ) {
			
			model.addAttribute("fieldsFilter", fieldsFilter);
		}

		model.addAttribute("tenantCode"  , tenantCode == null ? "" : tenantCode);
		
		// transporta el model sin que se requiera ser recibido en un método por parámetro.
		CommonDataAccesibility.setModel(model);
		
	}
}
