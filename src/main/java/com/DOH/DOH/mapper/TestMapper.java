package com.DOH.DOH.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    public String test (int id);
}
