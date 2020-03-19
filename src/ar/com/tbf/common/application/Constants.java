package ar.com.tbf.common.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {

	public static final String CREATE = "__create";
	public static final String MODIFY = "__modify";
	public static final String DELETE = "__delete";
	public static final String SEARCH = "__search";
	public static final String VIEW   = "__view";
	
	public static final DateFormat DATE_ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	public static final Locale AR = new Locale("es","AR");
	
	public static Boolean isHttps = false;
}
