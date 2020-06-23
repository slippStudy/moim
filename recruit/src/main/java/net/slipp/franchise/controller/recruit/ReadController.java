/*
 * Created by joenggyu0@gmail.com on 6/8/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller.recruit;

import net.slipp.franchise.apis.RecruitGetApi;
import net.slipp.franchise.models.RecruitModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadController implements RecruitGetApi {

    @Override
    public ResponseEntity<RecruitModel> getRecruitById(String recruitId) {

        if (isNotNumberic(recruitId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RecruitModel model = new RecruitModel();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    private boolean isNotNumberic(String recruitId) {
        return !StringUtils.isNumeric(recruitId);
    }
}
