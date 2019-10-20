package ar.com.tbf.common.application.helper;

public interface ITextProvider {

	public String getText( String key );
	public String getText( String key, Object ... values);
}
