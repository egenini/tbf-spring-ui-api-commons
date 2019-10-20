package ar.com.tbf.gson;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class SkipAttrbutesByNameExclusionStrategy implements ExclusionStrategy {

	private static final String FIELDS_SEPARATOR = ",";
	private static final String FIELDS_TO_EXCLUDE_CHAR = "-";
	
	protected List<String> attributesSkip = new ArrayList<String>();
	protected List<String> attributesAdd = new ArrayList<String>();
	protected List<Class<?>> classesSkip = new ArrayList<Class<?>>();
	
	public SkipAttrbutesByNameExclusionStrategy addFieldToExclude( String fieldName ){
		
		attributesSkip.add(fieldName);
		
		return this;
	}

	public SkipAttrbutesByNameExclusionStrategy addClass( Class<?> clazz ){
		
		classesSkip.add(clazz);
		
		return this;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return classesSkip.contains(clazz);
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		
		return attributesSkip.contains( f.getName() ) || ( ! attributesAdd.isEmpty() && ! attributesAdd.contains( f.getName() ) );
	}

	/**
	 * Incluye atributos por el nombre, excluye atributos si al nombre lo antecede el signo menos.
	 * Importante: no se pueden usar al mismo tiempo dado que la inclusión implica que sólo lista los incluidos por lo tanto no es necesario excluir
	 * y por el contrario si se excluye uno o más el resto se expone.
	 * 
	 * Ejemplo: 
	 * { "attr1": "", "attr2": "", "attr3": "",}
	 * 
	 * /?fieldsFilter=attr1,attr2 -expone sólo estos 2 atributos.
	 * /?fieldsFilter=-attr3      -expone attr1,attr2-
	 * 
	 * @param fieldsTo: lista de nombres de atributos separadas por coma ",".
	 */
	public void add(String fieldsTo) {
	
		if( fieldsTo != null && ! fieldsTo.trim().isEmpty() ) {
			
			String[] fields = fieldsTo.split(FIELDS_SEPARATOR);
			
			for( String field : fields ) {
				
				if( field.startsWith(FIELDS_TO_EXCLUDE_CHAR) ) {
					
					attributesSkip.add(field.substring(1));
				}
				else {
					
					attributesAdd.add(field);
				}
			}
		}
	}
	
	/**
	 * Agrega los nombres de los atributos que serán expuestos, el resto no lo será.
	 * 
	 * El método add es similar a este, salvo que acepta inclusiones y exclusiones.
	 * 
	 * @param fieldsToInclude: lista de nombres de atributos separada por comas.
	 */
	public void addToInclude(String fieldsToInclude) {

		if( fieldsToInclude != null && ! fieldsToInclude.trim().isEmpty() ) {
			
			String[] fields = fieldsToInclude.split(FIELDS_SEPARATOR);
			
			for( String field : fields ) {
				
				attributesAdd.add(field);
			}
		}
	}

	/**
	 * Agrega los nombres de los atributos que NO serán expuestos, el resto si lo será.
	 * 
	 * El método add es similar a este salvo que para excluir deberá anteponer un signo menos al nombre del atributo.
	 * 
	 * @param fieldsToExclude: lista de nombres de atributos separada por comas.
	 */
	public void addToExclude(String fieldsToExclude) {

		String[] fields = fieldsToExclude.split(FIELDS_SEPARATOR);
		
		for( String field : fields ) {
			
			this.addFieldToExclude(field);
		}
	}
}
