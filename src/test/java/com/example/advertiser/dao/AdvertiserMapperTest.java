package com.example.advertiser.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.advertiser.dto.domain.Advertiser;

@RunWith(SpringRunner.class)
@MybatisTest
public class AdvertiserMapperTest {

	@Autowired
	private AdvertiserMapper advertiserMapper;

	@Before
	public void setUp() {

	}

	@Test
	public void testInsert() {
		Advertiser advertiser = new Advertiser("test", "91919191", 30000f);
		int i = advertiserMapper.insert(advertiser);
		assertThat(i).isEqualTo(1);
	}

	@Test
	public void testUpdate_contactNumber() {
		Advertiser advertiser = new Advertiser("test", "91919191", 30000f);
		advertiserMapper.insert(advertiser);
		Advertiser updatedAdvertesr = new Advertiser("test", "222222", 30000f);
		advertiserMapper.update(updatedAdvertesr);
		assertThat(advertiserMapper.findByName("test").getContactNumber()).isEqualTo("222222");
	}

	@Test
	public void testDelete() {
		Advertiser advertiser = new Advertiser("test", "91919191", 30000f);
		advertiserMapper.insert(advertiser);
		advertiserMapper.deleteByName("test");
		assertNull("null", advertiserMapper.findByName("test"));
	}

	@Test
	public void testFindAll() {
		Advertiser advertiser = new Advertiser("test", "91919191", 30000f);
		Advertiser advertiser1 = new Advertiser("test1", "91919191", 30000f);
		advertiserMapper.insert(advertiser);
		advertiserMapper.insert(advertiser1);
		List<Advertiser> list = advertiserMapper.findAll();
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	public void testFind() {
		Advertiser advertiser = new Advertiser("test", "91919191", 10000f);
		Advertiser advertiser1 = new Advertiser("test1", "91919191", 30000f);
		advertiserMapper.insert(advertiser);
		advertiserMapper.insert(advertiser1);
		Advertiser actualAdvertiser = advertiserMapper.findByName("test1");
		assertThat(actualAdvertiser.getName()).isEqualToIgnoringCase("test1");
		assertThat(actualAdvertiser.getContactNumber()).isEqualTo("91919191");
		assertThat(actualAdvertiser.getCraditLimit()).isEqualTo(30000f);

	}
}
