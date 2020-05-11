package net.slipp.franchise.domain.model.recruit.inqueryitem;

import lombok.Getter;

@Getter
public class InquiryItem {

    //TODO 어떤 방식으로 질의?

    private InquiryItemId id;
    private String question;

    protected InquiryItem(InquiryItemId id, String question) {
        this.id = id;
        this.question = question;
    }

    public void changeQuestion(String question) {
        this.question = question;
    }
}
