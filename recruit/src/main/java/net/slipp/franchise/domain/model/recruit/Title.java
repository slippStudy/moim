package net.slipp.franchise.domain.model.recruit;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Title {
    public static final Title UNTITLED = new Title("Untitled");
    private final String text;

    public Title(String aText) {
        this.text = aText;
    }

    public String text() {
        return text;
    }

    @Override
    public String toString() {
        return String.valueOf(text);
    }

}
