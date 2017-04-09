package momo.validation;


/**
 * Created by ercan on 08.04.2017.
 */
public class QuestionableValidator extends Validator{

    public boolean validate(String sentence) {
        System.out.println("questionable validation passed.");

        if(true) {
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
