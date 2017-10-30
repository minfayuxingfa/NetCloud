package com.lanou.mapper;

import com.lanou.bean.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Service record);

    int insertSelective(Service service);

    Service selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);

    List<Service> selectAllService();

    List<Service> selectServiceSelected(@Param("osUsername") String osUsername,
                                        @Param("unixHost") String unixHost,
                                        @Param("idcardNo") String idcardNo,
                                        @Param("status") String status);
}