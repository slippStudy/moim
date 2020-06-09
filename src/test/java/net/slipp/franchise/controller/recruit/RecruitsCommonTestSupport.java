package net.slipp.franchise.controller.recruit;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.slipp.franchise.domain.model.recruit.*;
import net.slipp.franchise.domain.model.user.UserId;
import net.slipp.franchise.models.RecruitCreateModel;
import net.slipp.franchise.models.RecruitUpdateModel;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;

@WebMvcTest(controllers = {ReadController.class, CreateController.class, UpdateController.class})
abstract class RecruitsCommonTestSupport {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    Recruit recruit;

    RecruitCreateModel createModel;
    RecruitUpdateModel updateModel;

    static final String TITLE_TEXT = "title";
    static final String CONTENT_TEXT = "content";
    static final String SENDER_EMAIL = "sender@gmail.com";
    static final String NOT_VALID_SENDER_EMAIL = "sender";
    static final String RECEIVER_EMAIL = "receiver@gmail.com";
    static final DeadLineDateTime DEADLINE_DATE = new DeadLineDateTime(LocalDateTime.now().plusDays(10));
    static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            UTF_8);

    @BeforeEach
    void setUp() {
        recruit = getRecruit();
        createModel = getCreateModel();
        updateModel = getUpdateModel();
    }

    private RecruitUpdateModel getUpdateModel() {
        Recruit recruit = getRecruit();
        updateModel = new RecruitUpdateModel();
        updateModel.creator(recruit.owner().toString());
        updateModel.title(recruit.title().text());
        updateModel.contents(recruit.content().text());
        updateModel.deadlineDate(DEADLINE_DATE.dateTime());
        return updateModel;
    }

    private RecruitCreateModel getCreateModel() {
        Recruit recruit = getRecruit();
        createModel = new RecruitCreateModel();
        createModel.creator(recruit.owner().toString());
        createModel.title(recruit.title().text());
        createModel.deadlineDate(DEADLINE_DATE.dateTime());
        createModel.contents(recruit.content().text());
        return  createModel;
    }

    private Recruit getRecruit() {
        RecruitId id = RecruitId.of("1");
        UserId userId = UserId.of(SENDER_EMAIL);
        recruit = Recruit.Recruit(id, userId);
        recruit.setTitle(new Title(TITLE_TEXT));
        recruit.setContent(new Content(CONTENT_TEXT));
        recruit.setDeadLineDateTime(DEADLINE_DATE);
        return recruit;
    }
}