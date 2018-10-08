package com.example.advertiser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.advertiser.dto.domain.Advertiser;

@Mapper
public interface AdvertiserMapper {

	@Select("select * from advertiser")
	@Results({
        @Result(property = "contactNumber", column = "contact_number"),
        @Result(property = "craditLimit", column = "cradit_limit")
    })
	public List<Advertiser> findAll();

	@Select("SELECT * FROM advertiser WHERE name = #{name}")
	@Results({
        @Result(property = "contactNumber", column = "contact_number"),
        @Result(property = "craditLimit", column = "cradit_limit")
    })
	public Advertiser findByName(String name);

	@Delete("DELETE FROM advertiser WHERE name = #{name}")
	public int deleteByName(String name);

	@Insert("INSERT INTO advertiser(name, contact_number,cradit_limit) VALUES (#{name}, #{contactNumber},#{craditLimit})")
	public int insert(Advertiser advertiser);

	@Update("Update advertiser set contact_number=#{contactNumber}, cradit_limit=#{craditLimit} where name=#{name}")
	public int update(Advertiser advertiser);
}
