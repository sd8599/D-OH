package com.DOH.DOH.service.notifications;

import com.DOH.DOH.dto.list.PagingDTO;
import com.DOH.DOH.dto.notifications.NoticeDTO;
import com.DOH.DOH.mapper.notifications.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private static final int PAGE_SIZE = 1; // 페이지당 공지사항 수

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 공지사항 목록을 가져오는 메서드
     * @param dto 페이징 처리 정보를 포함하는 DTO (현재 페이지, 페이지 크기 등)
     * @return 공지사항 목록
     */
    @Override
    public List<NoticeDTO> getNoticeList(PagingDTO dto) {
        // 현재 페이지가 1보다 작은 경우 1로 설정
        int currentPage = dto.getCurrentPage() < 1 ? 1 : dto.getCurrentPage();

        // 페이지 크기를 DTO에서 가져오되, 값이 없으면 기본값을 사용
//        int pageSize = dto.getPageSize() > 0 ? dto.getPageSize() : PAGE_SIZE;

        // offset 계산 (offset = (현재 페이지 - 1) * 페이지 크기)
        int offset = (dto.getCurrentPage() - 1) * dto.getPageSize();

        // 공지사항 목록을 데이터베이스에서 가져옴
        List<NoticeDTO> noticeList = noticeMapper.getNoticeList(offset, dto.getPageSize());

        return (noticeList != null) ? noticeList : new ArrayList<>();
    }

    /**
     * 임시 저장된 공지사항 전체 목록을 가져오는 메서드
     * @return 임시 저장된 공지사항 목록
     */
    @Override
    public List<NoticeDTO> getTempNoticeList() {
        List<NoticeDTO> tempNotices = noticeMapper.findTempNotices(); // 페이징 없이 전체 임시 저장 목록 조회
        return (tempNotices != null) ? tempNotices : new ArrayList<>();
    }

    /**
     * 전체 공지사항 수를 기반으로 총 페이지 수를 계산하는 메서드
     * @return 총 페이지 수
     */
    @Override
    public int getTotalPages() {
        int totalNotices = noticeMapper.getTotalNotices(); // 공지사항 총 개수 가져오기
        return (int) Math.ceil((double) totalNotices / PAGE_SIZE); // 총 페이지 수 계산
    }

    /**
     * 공지사항 번호로 공지사항을 조회하는 메서드
     * @param noticeNum 공지사항 번호
     * @return 공지사항 DTO 객체
     */
    @Override
    public NoticeDTO getNoticeById(Long noticeNum) {
        return noticeMapper.selectNoticeById(noticeNum);
    }

    /**
     * 새로운 공지사항을 작성하는 메서드
     * @param noticeDTO 공지사항 DTO 객체 (제목, 내용, 작성자 등)
     */
    @Override
    public void noticeRegister(NoticeDTO noticeDTO) {
        // 공지사항 제목이 빈 값이 아닐 경우 설정
        if (noticeDTO.getNoticeTitle() == null || noticeDTO.getNoticeTitle().isEmpty()) {
            noticeDTO.setNoticeTitle("제목 없음"); // 기본 제목 설정
        }

        noticeMapper.insertNotice(noticeDTO); // 공지사항 저장
    }

    /**
     * 공지사항 임시 저장 메서드
     * @param noticeDTO 임시 저장할 공지사항 객체
     */
    @Override
    public void saveTempNotice(NoticeDTO noticeDTO) {
        // 현재 임시 저장된 공지사항 수 확인
        List<NoticeDTO> tempNotices = noticeMapper.findTempNotices();

        if (tempNotices.size() >= 3) {
            throw new IllegalStateException("임시 저장된 공지사항은 최대 3개까지만 저장할 수 있습니다.");
        }

        noticeDTO.setNoticeTempSave(true); // 임시 저장으로 설정
        noticeMapper.insertNotice(noticeDTO);
    }

    /**
     * 기존 공지사항을 수정하는 메서드
     * @param noticeDTO 수정된 공지사항 DTO 객체
     */
    @Override
    public void noticeUpdate(NoticeDTO noticeDTO) {
        noticeMapper.updateNotice(noticeDTO);
    }

    /**
     * 공지사항을 삭제하는 메서드
     * @param noticeNum 삭제할 공지사항 번호
     */
    @Override
    public void deleteNotice(Long noticeNum) {
        noticeMapper.deleteNotice(noticeNum);
    }

    @Override
    public void updateTempNotice(NoticeDTO noticeDTO) {
        noticeMapper.updateTempNotice(noticeDTO);
    }
}
