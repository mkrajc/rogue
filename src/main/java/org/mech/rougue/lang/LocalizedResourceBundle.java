package org.mech.rougue.lang;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalizedResourceBundle {

	private final static Logger LOG = LoggerFactory.getLogger(LocalizedResourceBundle.class);

	private final static String LANG_PROPERTIES_PATH = "lang.messages";
	private ResourceBundle current;

	@PostConstruct
	public void load() {
		final Locale lcl = getLocale();
		LOG.info("Loading resource bundle [name=" + LANG_PROPERTIES_PATH + ",locale=" + lcl + "]");
		current = ResourceBundle.getBundle(LANG_PROPERTIES_PATH, lcl);
	}

	private Locale getLocale() {
		 return new Locale("sk");
//		return Locale.ENGLISH;
	}

	public String getMessage(String key) {
		if(key == null){
			return "null";
		}
		return current == null ? key : current.containsKey(key) ? current.getString(key) : "% " + key + " %";
	}
}
