package helpers;

import com.github.jknack.handlebars.Options;
import controllers.OERWorldMap;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author fo
 */
public class HandlebarsHelpers {

  private static OERWorldMap mController;

  public static void setController(OERWorldMap aController) {
    mController = aController;
  }

  public CharSequence i18n(String key, Options options) {
    return _i18n(key, (String) options.hash.get("bundle"));
  }

  public static CharSequence _i18n(String key, String bundle) {
    if (bundle != null) {
      try {
        return ResourceBundle.getBundle(bundle).getString(key);
      } catch (MissingResourceException notInBundle) {
        return mController.getLabel(key);
      }
    }
    try {
      return ResourceBundle.getBundle("messages").getString(key);
    } catch (MissingResourceException notMessage) {
      try {
        return ResourceBundle.getBundle("languages").getString(key);
      } catch (MissingResourceException notLanguage) {
        try {
          return ResourceBundle.getBundle("countries").getString(key);
        } catch (MissingResourceException notCountry) {
          try {
            return ResourceBundle.getBundle("labels").getString(key);
          } catch (MissingResourceException notLabel) {
            return mController.getLabel(key);
          }
        }
      }
    }
  }

}
