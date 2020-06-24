package net.slipp.moim.domain.model.recruit;


import org.apache.commons.lang3.StringUtils;

public class Content {
    public static final Content NO_CONTENT = new Content(StringUtils.EMPTY);
    private final String text;

    public Content(String aText) {
        this.text = aText;
    }

    public String text() {
        return text;
    }
}
