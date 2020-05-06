package com.vendor.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.Utils.CommonUtils;
import com.vendor.model.Vendor;
import com.vendor.response.Response;
import com.vendor.service.VendorService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class VendorController {
	private static final Logger logger=LoggerFactory.getLogger(VendorController.class);
	
	@Autowired
	private VendorService vendorService;

	@RequestMapping(value="/allvendors")
	public List<Vendor> gettopic() {
		return vendorService.getAllVendors();	
	}

	@RequestMapping(method=RequestMethod.GET,value="/vendors/{id}")
	public Optional<Vendor> getvendorDetails(@PathVariable String id){
		
		return vendorService.findById(id);
	}
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Vendor> getVendorByUsername(@PathVariable("username") String username,HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		logger.info("getVendor by Usename: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		
		List<Vendor> vendor = vendorService.findByUsername(username);
		if(vendor==null || vendor.isEmpty()) {
			throw new IllegalArgumentException("Error Occured, No Such Value Found...!!");
		}else {
			return vendorService.findByUsername(username);
		}
	}
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@RequestMapping(method = RequestMethod.GET,value = "/month/{vendorScoreMonth}",produces = "application/json")
	public @ResponseBody List<Vendor> getMonth(@PathVariable("vendorScoreMonth") String vendorScoreMonth,HttpServletRequest request,HttpServletResponse response)throws Exception  {
		logger.info("getVendor by Vendorname: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		
		List<Vendor> vendor = vendorService.findByVendorScoreMonth(vendorScoreMonth);
		if(vendor==null || vendor.isEmpty()) {
			throw new IllegalArgumentException("Error Occured, No Such Value Found...!!");
		}else {
			return vendorService.findByVendorScoreMonth(vendorScoreMonth);
		}
	}
	
	@RequestMapping(value = "/vendor/{vendorname}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Vendor> getVendorByVendorname(@PathVariable("vendorname") String vendorname,HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		logger.info("getVendor by Vendorname: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		
		List<Vendor> vendor = vendorService.findByVendorname(vendorname);
		if(vendor==null || vendor.isEmpty()) {
			throw new IllegalArgumentException("Error Occured, No Such Value Found...!!");
		}else {
			return vendorService.findByVendorname(vendorname);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/postDetails",produces = "application/json")
	public Response addvendor(@RequestBody Vendor vendor,BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response)throws Exception {
		logger.info("addUser: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addUser: Received request: "+ CommonUtils.getJson(vendor));
		
		vendor.setCreatedDate(new Date());
		return vendorService.addVendor(vendor);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/update/{id}",produces = "application/json")
	public 	Response update(@RequestBody Vendor vendor,@PathVariable String id,HttpServletRequest request, HttpServletResponse response)throws Exception{	
		logger.info("Update: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("Update: Received request: "+ CommonUtils.getJson(vendor));
		logger.info("Update:"+id);
		vendor.setId(id);
		vendor.setCreatedDate(new Date());
		vendor.setModifiedDate(new Date());
		return vendorService.updateVendor(vendor,id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteVendor/{id}",produces = "application/json")
	public Response deleteVendor(@PathVariable String id, HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("Delete: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("Delete: Received request: "+ CommonUtils.getJson(id));
		 return vendorService.deleteVendor(id);
	}
	
}
