/*
 * Created by joenggyu0@gmail.com on 6/1/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller.recruit;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatusChangeControllerCommonTest extends RecruitsCommonTestSupport {

    @Test
    public void _200_when_creator_and_title_and_deadline_date_is_existed() throws Exception {
        mockMvc.perform(get("/recruits/recruit/{recruitId}/status", 1)
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isOk());
    }

    @Test
    public void _200_when_starting_recruit_status_is_begin_to_start() throws Exception {
        // TODO need business logic
    }


}
