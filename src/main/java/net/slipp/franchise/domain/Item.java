package net.slipp.franchise.domain;

import java.time.LocalDate;

public class Item {

    //property 를 모르니까? 예를 들어.
    private String content;
    private LocalDate date;

    public Item(String content, LocalDate date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
