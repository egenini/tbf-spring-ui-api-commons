package ar.com.tbf.gson;

public class TbfGsonWithHibernateAdapter extends TbfGson{

	public static synchronized com.google.gson.GsonBuilder GsonBuilder(){
		
		return TbfGson.GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
	}

}
