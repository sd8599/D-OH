package com.DOH.DOH.service.contest;

import com.DOH.DOH.dto.contest.AwardDTO;
import com.DOH.DOH.mapper.contest.ContestAwardMapper;
import com.DOH.DOH.service.user.UserSessionService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ContestAwardService {

    private final ContestAwardMapper contestAwardMapper;
    private final UserSessionService userSessionService;
    private final ContestUploadService contestUploadService;

    public ContestAwardService(ContestAwardMapper contestAwardMapper, UserSessionService userSessionService, ContestUploadService contestUploadService) {
        this.contestAwardMapper = contestAwardMapper;
        this.userSessionService = userSessionService;
        this.contestUploadService = contestUploadService;
    }


    public void saveAwardResult(Map<String, String> formData) {

        AwardDTO awardDTO = new AwardDTO();

        awardDTO.setUserEmail(userSessionService.userEmail());

        String contestNumString = formData.get("contestNum");
        Long contestNum = Long.valueOf(contestNumString);
        awardDTO.setConNum(contestNum);

        formData.forEach((email, ranking) -> {

            if (!ranking.equals("none")){
                if (ranking.equals("1")){
                    awardDTO.setFirstPlace(email);
                }
                if (ranking.equals("2")){
                    awardDTO.setSecondPlace(email);
                }
                if (ranking.equals("3")){
                    awardDTO.setThirdPlace(email);
                }
            }

        });

        contestAwardMapper.saveAwardResult(awardDTO);
        contestUploadService.finishContest(contestNum);

    }


    public AwardDTO viewResult(Long conNum){
        return contestAwardMapper.viewResult(conNum);
    }
}
