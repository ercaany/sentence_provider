package momo.crawler;

import java.util.Set;

/**
 * Created by ercan on 08.04.2017.
 */
public class WebPage {
    private String url;
    private Set<String> sentences;

    public WebPage(String url){
        this.url = url;
    }

    // getters - setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<String> getSentences() {
        return sentences;
    }

    public void setSentences(Set<String> sentences) {
        this.sentences = sentences;
    }
}
