package com.example.advertiser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advertiser.dao.AdvertiserMapper;
import com.example.advertiser.dto.domain.Advertiser;
import com.example.advertiser.exception.AdvertiserAlreadyExistedException;
import com.example.advertiser.exception.AdvertiserNotFoundException;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

	@Autowired
	private AdvertiserMapper advertiserRepository;

	@Override
	public Advertiser save(Advertiser advertiser) {
		Advertiser adv = findByAdvertiserName(advertiser.getName());
		if (null!=adv) {
			throw new AdvertiserAlreadyExistedException("advertiser already existed with name:" + advertiser.getName());
		}
		advertiserRepository.insert(advertiser);
		return advertiser;
	}

	@Override
	public Advertiser update(Advertiser advertiser) {
		Advertiser adv = this.findByAdvertiserName(advertiser.getName());
		if (null == adv) {
			throw new AdvertiserNotFoundException("no advertiser found with name or you can not update the name of advertiser." + advertiser.getName());
		}
		advertiserRepository.update(advertiser);
		return advertiser;
	}

	@Override
	public void delete(String advertiserName) {
		Advertiser advertiser = this.findByAdvertiserName(advertiserName);
		if (null == advertiser) {
			throw new AdvertiserNotFoundException("no advertiser found with name:" + advertiserName);
		}
		advertiserRepository.deleteByName(advertiserName);
	}

	@Override
	public Advertiser findByAdvertiserName(String advertiserName) {
		return advertiserRepository.findByName(advertiserName);
	}

	@Override
	public List<Advertiser> findAllAdvertisers() {
		return advertiserRepository.findAll();
	}

	@Override
	public Boolean validateAdvertiserCraditLimit(String advertiserName) {
		Advertiser advertiser = this.findByAdvertiserName(advertiserName);
		if (null == advertiser) {
			throw new AdvertiserNotFoundException("no advertiser found with name:" + advertiserName);
		}
		return advertiser.getCraditLimit() > 1000 ? true : false;
	}

}
