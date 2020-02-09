package ar.com.tbf.gson;

import java.util.Date;

public class TbfGson {

	public static synchronized com.google.gson.GsonBuilder GsonBuilder(){
		
		return new com.google.gson.GsonBuilder().setPrettyPrinting().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	}

	public com.google.gson.Gson Gson(){
		
		return GsonBuilder().create();
	}

	public com.google.gson.Gson Gson( boolean includeNulls ){
		
		return includeNulls ? GsonBuilder().create() : GsonBuilder().serializeNulls().create();
	}

	public com.google.gson.Gson Gson( String fieldsToAdd ){

		return Gson(fieldsToAdd, false);
	}

	public com.google.gson.Gson Gson(String fieldsToAdd, boolean serializeNulls) {

		com.google.gson.GsonBuilder builder = GsonBuilder();
		
		builder.serializeNulls();
		
		if( fieldsToAdd != null && ! fieldsToAdd.isEmpty() ) {
			
			SkipAttrbutesByNameExclusionStrategy sharedExclusion = new SkipAttrbutesByNameExclusionStrategy();
			
			sharedExclusion.add( fieldsToAdd );
			
			builder.addSerializationExclusionStrategy( sharedExclusion );
		}
		
		return builder.create();

	}
}
