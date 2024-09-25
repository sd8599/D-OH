package com.DOH.DOH.controller.contest;

import com.DOH.DOH.dto.contest.ContestUploadDTO;
import com.DOH.DOH.service.contest.ContestUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ContestResultController {

    private final ContestUploadService contestUploadService;

    public ContestResultController(ContestUploadService contestUploadService) {
        this.contestUploadService = contestUploadService;
    }


    // 수상자 선정 페이지 경로
    @GetMapping("/contest/award")
    public String contestAward(@RequestParam Long contestNum, Model model) {
        // 콘테스트 정보를 가져와 모델에 추가
        ContestUploadDTO contestUploadDTO = contestUploadService.findContestById(contestNum);

        // 모델에 콘테스트 정보와 참여자 목록 추가
        model.addAttribute("contestUploadDTO", contestUploadDTO);
        model.addAttribute("applicantList", contestUploadService.getContestApplicants(contestNum));

        return "contest/contestAward";  // 뷰 템플릿
    }


}
