package ar.com.tbf.common.application.helper;

import java.util.List;
import java.util.Map;

public interface IMessageManager {

	IMessageManager cleanAll();

	IMessageManager addMessage(String message);

	IMessageManager addMessage(String message, Object... params);

	boolean hasMessages();

	List<String> getMessages();

	boolean hasFieldErrors();

	IMessageManager addFieldError(String field, String message);

	IMessageManager addFieldError(String field, String message, Object... params);

	boolean hasFieldError(String field);

	List<String> getFieldErrors(String field);

	Map<String, List<String>> getFieldErrors();

	IMessageManager setFieldErrors(Map<String, List<String>> fieldErrors);

	boolean hasGenericErrors();

	IMessageManager addGenericError(String message);

	/**
	 * Recibe una plantilla de mensaje e inserta los valores recibidos en los n parametros siguientes.
	 * @param message: una cadena con al menos 1 {0}
	 * @param params: valor/es para cada {0}
	 */
	IMessageManager addGenericError(String message, Object... params);

	List<String> getGenericErrors();

	void setGenericErrors(List<String> genericErrors);

	boolean hasErrors();

	IMessageManager addAllFieldErrors(Map<String, List<String>> fieldErrors);
	
	IMessageManager addAllGenericErrors( List<String> genericErrors );
	
	IMessageManager addAllMessages(List<String> messages);
	
}