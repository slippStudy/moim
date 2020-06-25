/*
 * Created by joenggyu0@gmail.com on 6/8/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.controller.recruit;

import net.slipp.moim.apis.RecruitCreateApi;
import net.slipp.moim.models.RecruitCreateModel;
import net.slipp.moim.models.RecruitModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class CreateController implements RecruitCreateApi {

    @Override
    public ResponseEntity<RecruitModel> createRecruit(@Valid RecruitCreateModel body) {

        RecruitModel model = new RecruitModel();
        if (body.getDeadlineDate().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);

    }
}
