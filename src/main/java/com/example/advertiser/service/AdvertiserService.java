package com.example.advertiser.service;

import java.util.List;

import com.example.advertiser.dto.domain.Advertiser;

public interface AdvertiserService {

	Advertiser save(Advertiser advertiser);

	Advertiser update(Advertiser advertiser);

	void delete(String advertiserName);

	List<Advertiser> findAllAdvertisers();

	Boolean validateAdvertiserCraditLimit(String advertiserName);

	Advertiser findByAdvertiserName(String advertiserName);

}
