package net.slipp.franchise.controller.recruit;

import net.slipp.franchise.apis.RecruitsApi;
import net.slipp.franchise.models.RecruitCreateModel;
import net.slipp.franchise.models.RecruitModel;
import net.slipp.franchise.models.RecruitUpdateModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RecruitController implements RecruitsApi {


    @Override
    public ResponseEntity<RecruitModel> createRecruit(@Valid RecruitCreateModel body) {
        RecruitModel model = new RecruitModel();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecruitModel> getRecruit(String recruitId, @Valid RecruitUpdateModel body) {
        RecruitModel model = new RecruitModel();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecruitModel> statusChange(String recruitId, @Valid RecruitUpdateModel body) {
        RecruitModel model = new RecruitModel();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecruitModel> updateRecruitContents(String recruitId, @Valid RecruitUpdateModel body) {
        RecruitModel model = new RecruitModel();
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
