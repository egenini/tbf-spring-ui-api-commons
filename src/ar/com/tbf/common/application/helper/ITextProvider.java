package ar.com.tbf.common.application.helper;

public interface ITextProvider {

	public String toText( String key );
	public String toText( String key, Object ... values);
}
