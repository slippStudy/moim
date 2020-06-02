package net.slipp.franchise.controller.recruit;

import net.slipp.franchise.models.RecruitModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateControllerCommonTest extends RecruitsCommonTestSupport {

    @Test
    public void _400_when_no_change_anything() throws Exception {
        // TODO 어떻게 HTTP 응답값에 stub 데이터을 주입 시킬 수 있는지 모르겠음.

        RecruitModel recruitModel = new RecruitModel();
        recruitModel.creator(recruit.owner().toString());
        recruitModel.title(recruit.title().text());
        recruitModel.contents(recruit.content().text());
        recruitModel.deadlineDate(recruit.deadLineDateTime().dateTime());

        mockMvc.perform(put("/recruits/recruit/{recruitId}/status", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))

        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_no_existed_in_title_or_deadline_date() throws Exception {
        updateModel.title(null);
        mockMvc.perform(put("/recruits/recruit/{recruitId}/status", "1")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());

        updateModel.title(TITLE_TEXT);
        updateModel.deadlineDate(null);
        mockMvc.perform(put("/recruits/recruit/{recruitId}/status", "1")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_deadline_date_is_not_behind_from_now() throws Exception {
        updateModel.deadlineDate(LocalDateTime.now().minusMonths(1));
        mockMvc.perform(put("/recruits/recruit/{recruitId}/status", "1")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _200_when_creator_and_title_deadline_date_is_existed() throws Exception {
        mockMvc.perform(put("/recruits/recruit/{recruitId}/status", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());
    }
}
