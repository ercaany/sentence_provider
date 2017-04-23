package module.processor.model;

import java.util.List;
import java.util.Set;

/**
 * Created by ercan on 09.04.2017.
 */
public class Sentence {
    private String originalSentence;
    private Set<String> questions;
    private List<String> stemmedWordsList;
    private Set<String> tags;

    public Sentence(String sentence){
        this.originalSentence = sentence;
    }

    public Sentence(){

    }


    // getters and setters

    public String getOriginalSentence() {
        return originalSentence;
    }

    public void setOriginalSentence(String originalSentence) {
        this.originalSentence = originalSentence;
    }

    public Set<String> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<String> questions) {
        this.questions = questions;
    }

    public List<String> getStemmedWordsList() {
        return stemmedWordsList;
    }

    public void setStemmedWordsList(List<String> stemmedWordsList) {
        this.stemmedWordsList = stemmedWordsList;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
