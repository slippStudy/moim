/*
 * Created by joenggyu0@gmail.com on 6/1/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.controller.recruit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReadControllerTest extends RecruitsCommonTestSupport {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    public void _400_when_path_parameter_is_not_numeric() throws Exception {

        mockMvc.perform(get("/v1/recruits/{recruitId}", "하하")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());

        mockMvc.perform(get("/v1/recruits/{recruitId}", "@#!$%")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isBadRequest());

    }
}
