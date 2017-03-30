package momo.content;

import zemberek.tokenizer.SentenceBoundaryDetector;
import zemberek.tokenizer.SimpleSentenceBoundaryDetector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ercan on 23.03.2017.
 */
public class ContentHandler {
    private String sourceName;
    private Set<String> sentences;
    private Content content;
    private String contentText;

    public ContentHandler(String sourceName, Content content){
        this.sourceName = sourceName;
        this.content = content;
    }

    public String fetchContent(){
        if(contentText == null){
           if(!refreshContent()){
               contentText = null;
           }
        }

        return contentText;
    }

    public boolean refreshContent(){
        try{
            contentText = content.fetchContent(sourceName);
            splitParagraphIntoSentences();
            return true;
        } catch(FileNotFoundException e){
            System.out.println("##ERROR## Couldn't open the file..##ERROR##");
        } catch (IOException e) {
            System.out.println("##ERROR## Couldn't read the text in content. ##ERROR##");
        }
        return false;
    }

    private void splitParagraphIntoSentences(){
        sentences = new HashSet<String>();
        SentenceBoundaryDetector detector = new SimpleSentenceBoundaryDetector();

        List<String> sentencesFromDetector = detector.getSentences(contentText);
        sentences.addAll(sentencesFromDetector);
    }



    //getter - setter
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
