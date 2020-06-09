package net.slipp.franchise.controller.recruit;

import net.slipp.franchise.domain.model.recruit.RecruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateControllerTest extends RecruitsCommonTestSupport {

    @MockBean(name = "recruitRepository")
    RecruitRepository repository;

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    public void _400_when_path_parameter_is_wrong() throws Exception {

        mockMvc.perform(get("/recruits/{recruitId}", "하하")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());

        mockMvc.perform(get("/recruits/{recruitId}", "@#!$%")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void _400_when_no_existed_in_title_or_deadline_date() throws Exception {
        updateModel.title(null);

        mockMvc.perform(put("/recruits/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());

        updateModel.title(TITLE_TEXT);
        updateModel.deadlineDate(null);
        mockMvc.perform(put("/recruits/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_deadline_date_is_not_behind_from_now() throws Exception {
        updateModel.deadlineDate(LocalDateTime.now().minusMonths(1));

        mockMvc.perform(put("/recruits/{recruitId}", "1")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isBadRequest());
    }
}
