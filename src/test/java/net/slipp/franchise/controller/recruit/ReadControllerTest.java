/*
 * Created by joenggyu0@gmail.com on 6/1/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller.recruit;

import net.slipp.franchise.domain.model.recruit.RecruitId;
import net.slipp.franchise.domain.model.recruit.RecruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReadControllerTest extends RecruitsCommonTestSupport {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @MockBean
    RecruitRepository repository;

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
    void _405_if_not_support_method() throws Exception {

        mockMvc.perform(get("/recruits/{recruitId}", "")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void _200_when_all_data_is_existed() throws Exception {

        given(repository.findById(RecruitId.of("1"))).willReturn(recruit);

        mockMvc.perform(get("/recruits/{recruitId}", 1)
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isOk());

    }
}
