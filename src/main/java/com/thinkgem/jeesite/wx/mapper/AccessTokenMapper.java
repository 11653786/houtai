package com.thinkgem.jeesite.wx.mapper;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.vo.AccessToken;
import com.thinkgem.jeesite.wx.vo.AccessTokenExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface AccessTokenMapper {
    int countByExample(AccessTokenExample example);

    int deleteByExample(AccessTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);

    List<AccessToken> selectByExample(AccessTokenExample example);

    AccessToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccessToken record, @Param("example") AccessTokenExample example);

    int updateByExample(@Param("record") AccessToken record, @Param("example") AccessTokenExample example);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
}