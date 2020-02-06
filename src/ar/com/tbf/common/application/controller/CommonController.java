package ar.com.tbf.common.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;

import ar.com.tbf.common.application.helper.IMessageManager;
import ar.com.tbf.common.application.helper.ITextProvider;
import ar.com.tbf.common.application.helper.MessageManager;
import ar.com.tbf.common.share.CommonDataAccesibility;
import ar.com.tbf.gson.TbfGson;
import ar.com.tbf.web.generic.filter.RequestResponseAccessibility;
import jodd.util.Base64;

public abstract class CommonController extends CommonDataController implements ITextProvider {

	@Autowired
	MessageSource messageSource;

	protected ModelAndView modelAndView;

	protected IMessageManager messageManager = new MessageManager();
		
	public CommonController() {
		
	}
	
	protected void init() {
		
		this.messageManager.cleanAll();
		
		modelAndView = new ModelAndView();
		
		modelAndView.addObject("applicationURL", RequestResponseAccessibility.getApplicationURL() );

		modelAndView.getModel().put("messageManager", this.messageManager);
	}
	
	protected abstract String getNamespace();
	
	protected void addView( String resourceName, String viewName ) {
		
		modelAndView.setViewName( getNamespace() +"/"+ resourceName +"/"+ viewName);
	}

	public String toBase64(byte[] byteArray){
		
		return byteArray != null ? Base64.encodeToString(byteArray) : "";
	}
	
	protected Object parseApiErrorResponse( String body, Class classRef ) {
		
		return this.Gson().fromJson(body, classRef);
	}
	
	public com.google.gson.Gson Gson( ){

		return this.Gson(false);
	}
	
	public com.google.gson.Gson Gson( boolean serializeNulls ){
		
		com.google.gson.Gson gson = null;
		
		if( CommonDataAccesibility.hasFielsFilter() ) {
			
			gson = this.Gson( CommonDataAccesibility.getFieldFilter(), serializeNulls );
		}else {
			
			gson = new TbfGson().Gson(serializeNulls);
		}
		
		return gson;
	}

	public com.google.gson.Gson Gson( String fields ){

		return this.Gson(fields, false);
	}
	
	public com.google.gson.Gson Gson( String fields, boolean serializeNulls ){
				
		return new TbfGson().Gson(fields, serializeNulls);
	}
	
	@Override
	public String toText(String key) {
		
		String msg = key;
		
		try {
			msg = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
		}catch( NoSuchMessageException e) {
			// nada, devolvemos la key
		}
		return msg;
	}

	@Override
	public String toText(String key, Object... values) {
		return messageSource.getMessage(key, values, LocaleContextHolder.getLocale());
	}
}
