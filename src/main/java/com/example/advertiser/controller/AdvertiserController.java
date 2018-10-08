package com.example.advertiser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.advertiser.dto.domain.Advertiser;
import com.example.advertiser.exception.InvalidInputException;
import com.example.advertiser.service.AdvertiserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdvertiserService advertiserService;

	@ApiOperation(value = "Add Advertiser")
	@RequestMapping(method = RequestMethod.POST, consumes="application/json",produces = "application/json")
	public Advertiser save(@RequestBody Advertiser advertiser) {
		logger.info("******************received request for save adversier**********************");
		if(null == advertiser) {
			throw new InvalidInputException("null request received to save advertiser.");
		}
		if(StringUtils.isEmpty(advertiser.getName())) {
			throw new InvalidInputException("null or empty advertiser name received.");

		}
		return advertiserService.save(advertiser);
	}

	@ApiOperation(value = "Update advertiser contact number and cradit limit")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public Advertiser update(@PathVariable String advertiserName,
			@RequestBody Advertiser advertiser) {
		if(null == advertiser) {
			throw new InvalidInputException("null request received to update advertiser.");
		}
		if(StringUtils.isEmpty(advertiser.getName())) {
			advertiser.setName(advertiserName);
		}
		return advertiserService.update(advertiser);

	}

	@ApiOperation(value = "Delete advertiser")
	@RequestMapping(value = "/{advertiserName}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String advertiserName) {
		advertiserService.delete(advertiserName);
	}

	@ApiOperation(value = "Find All advertisers", response = Iterable.class)
	@ApiResponses(value =
       { @ApiResponse(code = 200, message = "Successfully retrieved all advertisers"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/findAllAdvertisers", method = RequestMethod.GET, produces = "application/json")
	public final List<Advertiser> findAll() {
		return advertiserService.findAllAdvertisers();
	}

	
	@ApiOperation(value = "Find advertiser", response = Advertiser.class)
	@ApiResponses(value =
       { @ApiResponse(code = 200, message = "Successfully retrieved all advertisers"),
         @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public Advertiser find(@RequestParam("advertiserName") String advertiserName) {
		Advertiser advertiser =  advertiserService.findByAdvertiserName(advertiserName);
		return advertiser;
	}

	@ApiOperation(value = "Validate creadit limit of advertiser.If advertiser cradit limit is <1000 then he is not a valid advertiser for transaction.")
	@RequestMapping(value = "/{advertiserName}/isValid", method = RequestMethod.GET, produces = "application/json")
	public Boolean validateAdvertiserCraditLimit(@PathVariable String advertiserName) {
		return advertiserService.validateAdvertiserCraditLimit(advertiserName);
	}

}
