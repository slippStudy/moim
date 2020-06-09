/*
 * Created by joenggyu0@gmail.com on 6/1/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller.recruit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateControllerTest extends RecruitsCommonTestSupport {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    public void _400_when_title_missing() throws Exception {
        createModel.title(null);
        mockMvc.perform(post("/recruits")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_title_length_is_not_2_or_less() throws Exception {
        createModel.title("T");
        mockMvc.perform(post("/recruits")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_no_deadline_date() throws Exception {
        createModel.deadlineDate(null);
        mockMvc.perform(post("/recruits")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_deadline_date_is_behind_from_now() throws Exception {
        createModel.deadlineDate(LocalDateTime.now().minusMonths(1));
        mockMvc.perform(post("/recruits")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _400_when_creator_is_missing() throws Exception {
        createModel.creator(null);
        mockMvc.perform(post("/recruits")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void _200_when_creator_title_deadline_date_is_existed() throws Exception {
        mockMvc.perform(post("/recruits")
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isOk());
    }
}
