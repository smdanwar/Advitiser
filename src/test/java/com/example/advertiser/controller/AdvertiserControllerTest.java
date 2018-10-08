package com.example.advertiser.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.advertiser.dto.domain.Advertiser;
import com.example.advertiser.exception.InvalidInputException;
import com.example.advertiser.service.AdvertiserService;

public class AdvertiserControllerTest {

	@InjectMocks
	private AdvertiserController advertiserController;

	@Mock
	private AdvertiserService advertiserService;

	@Before
	public void setUp() {
        MockitoAnnotations.initMocks(this);

	}

	@After
	public void tearDown() {

	}

	@Test
	public void testSave() {
       when(advertiserService.save(any(Advertiser.class))).thenReturn(prepareAdvertiser("myApi", "91-970", 20000f));
		Advertiser advertiser = advertiserController.save(prepareAdvertiser("myApi", "91-970", 20000f));
		assertThat(advertiser.getName()).isEqualTo("myApi");

	}

	
	@Test(expected=InvalidInputException.class)
	public void testSave_when_name_null_should_throw_InvalidRequestException() {
	advertiserController.save(prepareAdvertiser(null, "91-970", 20000f));
	}
	

	@Test(expected=InvalidInputException.class)
	public void testSave_when_advertiser_null_should_throw_InvalidRequestException() {
	advertiserController.save(null);
	}
	
	@Test
	public void testUpdate() {
	   Advertiser adv  = prepareAdvertiser("myApi", "91-970", 110000f);
	   when(advertiserService.findByAdvertiserName("myApi")).thenReturn(adv);
       when(advertiserService.update(adv)).thenReturn(adv);
	   Advertiser advertiser = advertiserController.update("myApi",adv);
	   assertThat(advertiser.getName()).isEqualTo("myApi");
	}
	
	@Test
	public void testDelete() {
	   Advertiser adv  = prepareAdvertiser("myApi", "91-970", 110000f);
	   when(advertiserService.findByAdvertiserName("myApi")).thenReturn(adv);
	   doAnswer((i) -> {
			assertThat("myApi".equals(i.getArgument(0)));
			return null;
		}).when(advertiserService).delete("myApi");;
	   advertiserController.delete("myApi");
	}
	
	@Test(expected=InvalidInputException.class)
	public void testUpdate_when_advertiser_null_should_throw_InvalidRequestException() {
	advertiserController.update("myApi",null);
	}
	
	@Test
	public void testFindAll() {
     Advertiser adv1  = prepareAdvertiser("myApi", "91-970", 110000f);
	 Advertiser adv  = prepareAdvertiser("myApi1", "91-970", 110000f);
	 
	 List<Advertiser> advertisers = new ArrayList<>();
	 advertisers.add(adv);
	 advertisers.add(adv1);

     when(advertiserService.findAllAdvertisers()).thenReturn(advertisers);
     advertisers = advertiserController.findAll();
     assertThat(advertisers.size()).isEqualTo(2);
	}
	
	@Test
	public void testFind() {
	     Advertiser adv1  = prepareAdvertiser("myApi", "91-970", 110000f);
	     when(advertiserService.findByAdvertiserName("myApi")).thenReturn(adv1);
	     Advertiser adv = advertiserController.find("myApi");
	     //assertThat(adv).isNotNull();
	     assertThat(adv.getContactNumber()).isEqualTo("91-970");
	     assertThat(adv.getCraditLimit()).isEqualTo(110000f);
	     assertThat(adv.getName()).isEqualTo("myApi");
		}
	@Test
	public void validateAdvertiserCraditLimit() {
	     Advertiser adv1  = prepareAdvertiser("myApi", "91-970", 110000f);
	     when(advertiserService.findByAdvertiserName("myApi")).thenReturn(adv1);
	     when(advertiserService.validateAdvertiserCraditLimit("myApi")).thenReturn(true);
	     Boolean flag = advertiserController.validateAdvertiserCraditLimit("myApi");
	     assertThat(flag).isEqualTo(true);
	}
	
	public void validateAdvertiserCraditLimit_should_false_when_cradit_limit_lessthan_1000() {
	     Advertiser adv1  = prepareAdvertiser("myApi", "91-970", 110f);
	     when(advertiserService.findByAdvertiserName("myApi")).thenReturn(adv1);
	     when(advertiserService.validateAdvertiserCraditLimit("myApi")).thenReturn(false);
	     Boolean flag = advertiserController.validateAdvertiserCraditLimit("myApi");
	     assertThat(flag).isEqualTo(false);
	}

	private Advertiser prepareAdvertiser(final String name, final String contactNumber, final Float craditLimit) {
		Advertiser advertiser = new Advertiser();
		advertiser.setName(name);
		advertiser.setContactNumber(contactNumber);
		advertiser.setCraditLimit(craditLimit);
		return advertiser;
	}
}
