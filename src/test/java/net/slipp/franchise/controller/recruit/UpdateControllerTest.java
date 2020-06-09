package net.slipp.franchise.controller.recruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateControllerTest extends RecruitsCommonTestSupport {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    public void _400_when_no_change_anything() throws Exception {
        mockMvc.perform(put("recruits/recruit/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_no_existed_in_title_or_deadline_date() throws Exception {
        updateModel.title(null);
        mockMvc.perform(put("recruits/recruit/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());

        updateModel.title(TITLE_TEXT);
        updateModel.deadlineDate(null);
        mockMvc.perform(put("recruits/recruit/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_deadline_date_is_not_behind_from_now() throws Exception {
        updateModel.deadlineDate(LocalDateTime.now().minusMonths(1));
        mockMvc.perform(put("recruits/recruit/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _200_when_creator_and_title_deadline_date_is_existed() throws Exception {
        mockMvc.perform(put("recruits/recruit/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());
    }

    @Test
    public void _200_when_creator_and_title_and_deadline_date_is_existed() throws Exception {
        mockMvc.perform(put("recruits/{recruitId}/status", 1)
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isOk());
    }

    @Test
    public void _200_when_starting_recruit_status_is_begin_to_start() throws Exception {
        // TODO need business logic
    }
}
