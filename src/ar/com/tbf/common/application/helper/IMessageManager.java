package ar.com.tbf.common.application.helper;

import java.util.List;
import java.util.Map;

public interface IMessageManager {

	void cleanAll();

	void addMessage(String message);

	void addMessage(String message, Object... params);

	boolean hasMessages();

	List<String> getMessages();

	boolean hasFieldErrors();

	void addFieldError(String field, String message);

	void addFieldError(String field, String message, Object... params);

	boolean hasFieldError(String field);

	List<String> getFieldErrors(String field);

	Map<String, List<String>> getFieldErrors();

	void setFieldErrors(Map<String, List<String>> fieldErrors);

	boolean hasGenericErrors();

	void addGenericError(String message);

	/**
	 * Recibe una plantilla de mensaje e inserta los valores recibidos en los n parametros siguientes.
	 * @param message: una cadena con al menos 1 {0}
	 * @param params: valor/es para cada {0}
	 */
	void addGenericError(String message, Object... params);

	List<String> getGenericErrors();

	void setGenericErrors(List<String> genericErrors);

	boolean hasErrors();

	void addAllFieldErrors(Map<String, List<String>> fieldErrors);
	
	void addAllGenericErrors( List<String> genericErrors );
	
	void addAllMessages(List<String> messages);
	
}