package momo.validation;

/**
 * Created by ercan on 08.04.2017.
 */
public class LanguageValidator extends Validator {

    public boolean validate(String sentence) {
        if(true) {
            System.out.println("language validation passed.");
            //kendi işlemi doğru sonuç verdi
            if(getNextValidator() != null) {
                return getNextValidator().validate(sentence);
            }
            else {
                return true;
            }
        } else {
            return false;
        }
    }
}
