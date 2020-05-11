/*
 * Created by joenggyu0@gmail.com on 5/11/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit.inqueryitem;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class InquiryItemId {

    private final String value;

    public static InquiryItemId of(String value) {
        InquiryItemId inquiryItemId = new InquiryItemId(value);
        return inquiryItemId;
    }

    InquiryItemId(String value) {
        this.value = value;
    }
}
