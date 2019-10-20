package ar.com.tbf.common.share;

import org.springframework.ui.Model;


public class CommonDataAccesibility {
	
	public static final String ATTRIBUTE_NAME__FIELDS_FILTER = "fieldsFilter";
	
	private static ThreadLocal<ModelContainer> commonDataContainer = new ThreadLocal<ModelContainer>() {
		
		protected ModelContainer initialValue() {
			return new CommonDataAccesibility().new ModelContainer();
		}
	};

	public static void setModel( Model model ) {
		
		commonDataContainer.get().setModel( model );
	}

	/**
	 * 
	 * @return true si en la petición se recibió un parámetro fields con un csv con la lista de atributos de devolver.
	 */
	public static boolean hasFielsFilter() {

		return commonDataContainer.get().hasFieldFilter();
	}

	/**
	 * 
	 * @return lista separada por comas de los atributos que se esperan recibir a partir de la petición por ej:?fields=attr1,attr2
	 */
	public static String getFieldFilter() {
		
		return commonDataContainer.get().getFieldFilter();
	}
	
	public class ModelContainer {
		
		private Model model;

		public Model getModel() {
			return model;
		}

		public void setModel(Model model) {
			this.model = model;
		}
		
		public boolean hasFieldFilter() {
			
			return this.model.containsAttribute(ATTRIBUTE_NAME__FIELDS_FILTER);
		}
		
		public String getFieldFilter() {
			
			return (String) this.model.asMap().get(ATTRIBUTE_NAME__FIELDS_FILTER);
		}
	}

}

