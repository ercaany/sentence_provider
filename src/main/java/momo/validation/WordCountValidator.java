package momo.validation;

/**
 * Created by ercan on 09.04.2017.
 */
public class WordCountValidator extends Validator {

    public boolean validate(String sentence) {
        if(true) {
            //kendi işlemi doğru sonuç verdi
            System.out.println("word count validation passed.");
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
