/*
 * Created by joenggyu0@gmail.com on 5/11/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.applicationform;

import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.slipp.franchise.domain.model.recruit.inqueryitem.InquiryItem;
import net.slipp.franchise.domain.model.user.User;
import net.slipp.franchise.domain.model.recruit.Recruit;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@EqualsAndHashCode
@ToString
public class ApplicationForm {

    private final Recruit recruit;
    private boolean enable = false;
    private Set<InquiryItem> inquiryItems;

    public static ApplicationForm of(Recruit recruit){
        return new ApplicationForm(recruit);
    }

    ApplicationForm(Recruit recruit) {
        Objects.nonNull(recruit.allInquiryItems());

        this.recruit = recruit;
        setInquiryItems();
    }

    private void setInquiryItems() {
        this.inquiryItems = recruit.getInquiryItems();
    }

    public SubmittedApplicationForm submit(User user, ApplicationFormSubmitEvent event){

        return new SubmittedApplicationForm();
    }
}
