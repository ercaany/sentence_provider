package momo.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ercan on 09.04.2017.
 */
public class UniqueWord {
    private String word;
    private Set<String> documentSet;

    public UniqueWord(){
        documentSet = new HashSet<String>();
    }

    public UniqueWord(String word){
        this.word = word;
        documentSet = new HashSet<String>();
    }

    // getters and setters

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getDocumentSet() {
        return documentSet;
    }

    public void setDocumentSet(Set<String> documentSet) {
        this.documentSet = documentSet;
    }
}
