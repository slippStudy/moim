/*
 * Created by joenggyu0@gmail.com on 5/19/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller;

import net.slipp.franchise.apis.RecruitsApi;
import net.slipp.franchise.models.RecruitCreateModel;
import net.slipp.franchise.models.RecruitModel;
import net.slipp.franchise.models.RecruitUpdateModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class RecruitController implements RecruitsApi {

    @Override
    public ResponseEntity<RecruitModel> createRecruit(@Valid RecruitCreateModel body) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> getRecruit(String recruitId, @Valid RecruitUpdateModel body) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> statusChange(String recruitId, @Valid RecruitUpdateModel body) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> updateRecruitContents(String recruitId, @Valid RecruitUpdateModel body) {
        return null;
    }
}
