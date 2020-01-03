package ar.com.tbf.common.application.helper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager implements IMessageManager {

	private List<String>                messages      = new ArrayList<String>();
	private Map<String, List<String>>   fieldErrors   = new HashMap<String, List<String>>();
	private List<String>                genericErrors = new ArrayList<String>();
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#cleanAll()
	 */
	@Override
	public IMessageManager cleanAll() {
		this.messages.clear();
		this.fieldErrors.clear();
		this.genericErrors.clear();
		
		return this;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#addMessage(java.lang.String)
	 */
	@Override
	public IMessageManager addMessage( String message ){
		this.messages.add(message);
		return this;
	}

	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#addMessage(java.lang.String, java.lang.Object)
	 */
	@Override
	public IMessageManager addMessage( String message, Object...params ){
		this.messages.add( new MessageFormat( message ).format(params) );
		return this;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#hasMessages()
	 */
	@Override
	public boolean hasMessages(){
		return ! messages.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#getMessages()
	 */
	@Override
	public List<String> getMessages(){
		return this.messages;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#hasFieldErrors()
	 */
	@Override
	public boolean hasFieldErrors(){
		return ! this.fieldErrors.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#addFieldError(java.lang.String, java.lang.String)
	 */
	@Override
	public IMessageManager addFieldError( String field, String message ){
		
		if( ! fieldErrors.containsKey(field) ){
			fieldErrors.put(field, new ArrayList<String>());
		}
		
		fieldErrors.get( field ).add(message);
		return this;
	}

	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#addFieldError(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public IMessageManager addFieldError( String field, String message, Object...params ){
		
		if( ! fieldErrors.containsKey(field) ){
			fieldErrors.put(field, new ArrayList<String>());
		}
		
		fieldErrors.get( field ).add( new MessageFormat(message).format(params));
		return this;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#hasFieldError(java.lang.String)
	 */
	@Override
	public boolean hasFieldError( String field ) {
		
		return fieldErrors.containsKey(field);
	}

	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#getFieldErrors(java.lang.String)
	 */
	@Override
	public List<String> getFieldErrors( String field ) {
		return fieldErrors.containsKey(field) ? fieldErrors.get(field) : new ArrayList<String>();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#getFieldErrors()
	 */
	@Override
	public Map<String, List<String>> getFieldErrors() {
		return fieldErrors;
	}

	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#setFieldErrors(java.util.Map)
	 */
	@Override
	public IMessageManager setFieldErrors(Map<String, List<String>> fieldErrors) {
		this.fieldErrors = fieldErrors;
		return this;
	}

	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#hasGenericErrors()
	 */
	@Override
	public boolean hasGenericErrors(){
		return ! this.genericErrors.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#addGenericError(java.lang.String)
	 */
	@Override
	public IMessageManager addGenericError( String message ){
		this.genericErrors.add(message );
		return this;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#addGenericError(java.lang.String, java.lang.Object)
	 */
	@Override
	public IMessageManager addGenericError( String message, Object...params ){
		
		this.genericErrors.add( new MessageFormat(message).format(params) );
		return this;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#getGenericErrors()
	 */
	@Override
	public List<String> getGenericErrors() {
		return genericErrors;
	}

	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#setGenericErrors(java.util.List)
	 */
	@Override
	public void setGenericErrors(List<String> genericErrors) {
		this.genericErrors = genericErrors;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.tbf.common.application.IMessageManager#hasErrors()
	 */
	@Override
	public boolean hasErrors(){
		return hasGenericErrors() || hasFieldErrors();
	}
	
	class GenericError {
		private String message;

		public GenericError( String message ){
			this.message = message;
		}
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	public IMessageManager addAllFieldErrors(Map<String, List<String>> fieldErrors) {
		
		for( String key : fieldErrors.keySet() ) {
			this.fieldErrors.put(key, fieldErrors.get(key));
		}
		return this;
	}

	public IMessageManager addAllGenericErrors( List<String> genericErrors ) {

		this.genericErrors.addAll(genericErrors);
		return this;
	}

	public IMessageManager addAllMessages(List<String> messages) {

		this.messages.addAll(messages);
		return this;
	}

	public static void main(String[] args) {
		
		MessageManager messageManager = new MessageManager();
		
		messageManager.addGenericError("gg {0} ", 1);

		messageManager.addMessage("hola {0} {1}", "logi", 4);
		
		System.out.println(messageManager.messages);
		System.out.println(messageManager.genericErrors);
	}
}
