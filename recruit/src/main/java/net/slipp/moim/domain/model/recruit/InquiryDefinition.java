package net.slipp.moim.domain.model.recruit;

import lombok.Getter;

@Getter
public class InquiryDefinition {

    private final String text;
    private final boolean required;

    public InquiryDefinition(String text, boolean required) {
        this.text = text;
        this.required = required;
    }
}
