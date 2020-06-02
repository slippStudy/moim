/*
 * Created by joenggyu0@gmail.com on 6/1/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller.recruit;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReadControllerCommonTest extends RecruitsCommonTestSupport {

    @Test
    public void _200_when_all_data_is_existed() throws Exception {
        mockMvc.perform(get("/recruits/recruit/{recruitId}", 1)
                .contentType(CONTENT_TYPE)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());
    }
}
