/*
 * Created by joenggyu0@gmail.com on 6/8/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.controller.recruit;

import net.slipp.moim.apis.RecruitUpdateApi;
import net.slipp.moim.models.RecruitModel;
import net.slipp.moim.models.RecruitUpdateModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class UpdateController implements RecruitUpdateApi {

    @Override
    public ResponseEntity<RecruitModel> recruitStatusChange(String recruitId) {
        RecruitModel model = new RecruitModel();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecruitModel> updateRecruit(String recruitId, @Valid RecruitUpdateModel body) {

        if (body.getDeadlineDate().isBefore(LocalDateTime.now())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
