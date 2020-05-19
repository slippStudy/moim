/*
 * Created by joenggyu0@gmail.com on 5/19/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.controller;

import net.slipp.franchise.apis.RecruitApi;
import net.slipp.franchise.models.InquiryModel;
import net.slipp.franchise.models.RecruitCreateModel;
import net.slipp.franchise.models.RecruitModel;
import net.slipp.franchise.models.RecruitUpdateModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecruitController implements RecruitApi {

    @Override
    public ResponseEntity<RecruitModel> addInquiriesToRecruit(String recruitId, @Valid List<InquiryModel> body) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> createRecruit(@Valid RecruitCreateModel body) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> finishRecruit(String recruitId) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> startRecruit(String recruitId) {
        return null;
    }

    @Override
    public ResponseEntity<RecruitModel> updateRecruitContents(String recruitId, @Valid RecruitUpdateModel body) {
        return null;
    }
}
