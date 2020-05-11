/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.recruit;

import com.google.common.collect.Sets;
import lombok.Getter;
import net.slipp.common.Assertions;
import net.slipp.common.domain.model.DomainEventPublisher;
import net.slipp.franchise.domain.model.meetup.MeetupId;
import net.slipp.franchise.domain.model.recruit.inqueryitem.InquiryItem;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static net.slipp.franchise.domain.model.recruit.Status.*;

@Getter
public class Recruit {

    @NotNull
    private RecruitId id;

    @NotNull
    private MeetupId meetupId;

    @NotNull
    private Status status;

    private Set<InquiryItem> inquiryItems;

    private Recruit() {
        this.setInquiryItems(Sets.newHashSet());
    }

    Recruit(RecruitId id, MeetupId meetupId, Status status) {
        this();

        this.id = id;
        this.meetupId = meetupId;
        this.status = status;
    }

    public void addInquiryItem(InquiryItem anInquiryItem) {
        Assertions.assertArgumentNotNull(anInquiryItem, "반드시 InquiryItem 는 존재 합니다.");
        Assertions.assertStateTrue(BEGIN == getStatus(), "BEGIN 단계 에 서만 질의문을 추가 가능합니다.");

        inquiryItems().add(anInquiryItem);
    }

    public Set<InquiryItem> allInquiryItems() {
        return Collections.unmodifiableSet(this.inquiryItems());
    }

    public void start() {
        if (BEGIN != getStatus()) {
            throw new IllegalStateException();
        }
        setStatus(START);
    }

    public void finish() {
        // TODO 상태 전이 책임을 다르 곳을 옮겨 주세요
        if (START != getStatus()) {
            throw new IllegalStateException();
        }
        setStatus(FINISH);
    }

    private Set<InquiryItem> inquiryItems() {
        return inquiryItems;
    }

    private void setInquiryItems(HashSet<InquiryItem> inquiryItems) {
        this.inquiryItems = inquiryItems;
    }

    private void setStatus(@NotNull final Status status) {
        this.status = status;
        DomainEventPublisher.instance().publish(new RecruitStatusChangedEvent());
    }
}
