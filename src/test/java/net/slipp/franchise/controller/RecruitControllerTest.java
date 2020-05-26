package net.slipp.franchise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.slipp.franchise.controller.recruit.RecruitController;
import net.slipp.franchise.domain.model.recruit.*;
import net.slipp.franchise.domain.model.user.UserId;
import net.slipp.franchise.models.RecruitCreateModel;
import net.slipp.franchise.models.RecruitUpdateModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecruitController.class)
class RecruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    RecruitRepository recruitRepository;

    Recruit recruit;

    RecruitCreateModel createModel;
    RecruitUpdateModel updateModel;

    private static final String TEST_TITLE = "title";
    private static final String TEST_CONTENT = "content";
    private static final String TEST_SENDER_EMAIL = "sender@gmail.com";
    private static final String TEST_NOT_VALID_SENDER_EMAIL = "sender";
    private static final String TEST_RECEIVER_EMAIL = "receiver@gmail.com";
    private static final DeadLineDateTime TEST_LOCAL_DATE_TIME = new DeadLineDateTime(LocalDateTime.now().plusDays(10));
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            UTF_8);

    @BeforeEach
    void setUp() {
        RecruitId id = RecruitId.of("1");
        UserId userId = UserId.of(TEST_SENDER_EMAIL);
        recruit = Recruit.Recruit(id, userId);
        recruit.setTitle(new Title(TEST_TITLE));
        recruit.setContent(new Content(TEST_CONTENT));
        recruit.setDeadLineDateTime(TEST_LOCAL_DATE_TIME);
        createModel = new RecruitCreateModel();
        updateModel = new RecruitUpdateModel();
        recruitRepository.save(recruit);

        given(recruitRepository.findById(RecruitId.of("1"))).willReturn(recruit);
    }

    @Test
    public void 오직_유효한_특정URL에_200를_응답한다() throws Exception {

        mockMvc.perform(post("/recruits")
                .contentType(contentType)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isOk());

        mockMvc.perform(put("/recruits/{recruitId}", "recruitId", "1")
                .contentType(contentType)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());

        mockMvc.perform(get("/recruits/{recruitId}", "recruitId", "1")
                .contentType(contentType)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());

        mockMvc.perform(put("/recruits/{recruitId}/status", "recruitId", "1")
                .contentType(contentType)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());

    }

    @Test
    public void 유효하지않는_HTTP메서드은_405를_응답한다() throws Exception {

        mockMvc.perform(delete("/recruits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isMethodNotAllowed());

        mockMvc.perform(delete("/recruits")
                .contentType(MediaType.APPLICATION_JSON)
                .param("recruitId", "1")
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void CONTENTS_TYPE이_JSON_이_아니라면_Status415을_반환한다() throws Exception {

        mockMvc.perform(post("/recruits")
                .contentType(MediaType.APPLICATION_XML)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isUnsupportedMediaType());

        mockMvc.perform(put("/recruits/{recruitId}", "recruitId", "1")
                .contentType(MediaType.TEXT_PLAIN)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isUnsupportedMediaType());

        mockMvc.perform(get("/recruits/{recruitId}", "recruitId", "1")
                .contentType(MediaType.APPLICATION_PDF)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isUnsupportedMediaType());

        mockMvc.perform(put("/recruits/{recruitId}/status", "recruitId", "1")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void recruits의_요청_메서드는_은_오직_recruitId를_파라미터로_가진다() throws Exception {

        mockMvc.perform(post("/recruits")
                .contentType(contentType)
                .content(mapper.writeValueAsString(createModel))
        ).andExpect(status().isOk());

        mockMvc.perform(put("/recruits/{recruitId}", "recruitId", "1")
                .contentType(contentType)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());

        mockMvc.perform(get("/recruits/{recruitId}", "recruitId", "1")
                .contentType(contentType)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());

        mockMvc.perform(put("/recruits/{recruitId}/status", "recruitId", "1")
                .contentType(contentType)
                .content(mapper.writeValueAsString(updateModel))
        ).andExpect(status().isOk());

    }

    @Test
    public void 마감일_입력시_현재보다_앞이_아니라면_에러를_일으킨다() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            DeadLineDateTime previousDateTime = new DeadLineDateTime(LocalDateTime.now().minusDays(1));
            recruit.setDeadLineDateTime(previousDateTime);
        });
    }

    @Test
    public void 제목은_최소_2글자_이상이_아니면_에러를_일으킨다() throws Exception {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Title testTitle = new Title("하");
            recruit.setTitle(testTitle);
        });
    }

    @Test
    public void 생성시점에는_빈_질의문이_존재해야_한다() throws Exception {
        Assertions.assertEquals(recruit.allInquiryDefinitions(), Collections.EMPTY_SET);
    }

    @Test
    void Recruit를_생성_한다() throws Exception {
        mockMvc.perform(post("/recruits").contentType(contentType).content(mapper.writeValueAsString(createModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recruitId").hasJsonPath())
                .andExpect(jsonPath("$.creatorEmail").hasJsonPath())
                .andExpect(jsonPath("$.title").hasJsonPath())
                .andExpect(jsonPath("$.recruitContents").hasJsonPath())
                .andExpect(jsonPath("$.inquires").hasJsonPath())
                .andExpect(jsonPath("$.deadlineDate").hasJsonPath())
                .andExpect(jsonPath("$.status").hasJsonPath());
    }
}