package com.DOH.DOH.mapper.list;

import com.DOH.DOH.dto.list.PortFolioDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PortFolioMapper {
    ArrayList<PortFolioDTO> getPortFolioList();
    void hitUp(int id);//조회수 증가 메서드
}
