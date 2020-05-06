package com.vendor.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendor.Utils.CommonUtils;
import com.vendor.constants.StatusCode;
import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import com.vendor.response.Response;

@Service
public class VendorService {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(VendorService.class);

			@Autowired
			private VendorRepository vendorRepository;

			public List<Vendor> getAllVendors(){
			List<Vendor> vendors=new ArrayList<>();
			vendorRepository.findAll()
				.forEach(vendors::add);
			    return vendors;
			}
									
			public Response addVendor(Vendor vendor1) {
				Response res=CommonUtils.getResponseObject("Succesfully Added Vendor Details..!!");
				try {
						String pattern ="MM/DD/YY";					
						DateFormat sdf = new SimpleDateFormat(pattern);									
						vendorRepository.save(vendor1);
						res.setStatus(StatusCode.SUCCESS.name());			
					}
				catch(Exception ex){
					logger.info("Exception Service:" + ex.getMessage());
					res.setStatus(StatusCode.ERROR.name());
					res.setMessage("Error Occured,Vendor name and vendor month should be Unique..!!");
				}
				return res;
			}
			
			public Optional<Vendor> findById(String id) {
				return vendorRepository.findById(id);
			}

			public List<Vendor> findByUsername(String username) throws Exception{
				try {
					return vendorRepository.findByUsername(username);
				} 
				catch(Exception e){
					logger.info("Exception in getVendorByUsername:", e.getMessage());
					return null;
				}
			}
			
			public List<Vendor> findByVendorScoreMonth(String vendorScoreMonth) {
				try {
						return vendorRepository.findByVendorScoreMonth(vendorScoreMonth);
					}
				catch(Exception e){
					logger.info("Exception in getVendorByVendorScoreMonth:", e.getMessage());
					return null;
				}
			}

			public List<Vendor> findByVendorname(String vendorname) {
				try {
						return vendorRepository.findByVendorname(vendorname);
					}
				catch(Exception e){
					logger.info("Exception in getVendorByVendorname:", e.getMessage());
					return null;
				}
			}
		
			public Response updateVendor(Vendor vendor1,String id) {
				Response res=CommonUtils.getResponseObject("Succesfully Updated Vendor Details");
				try {
						vendorRepository.save(vendor1);
						res.setStatus(StatusCode.SUCCESS.name());
			        }
				catch(Exception ex){
					logger.info("Exception Service:" + ex.getMessage());
					res.setStatus(StatusCode.ERROR.name());
					res.setMessage("Error Occured, Vendor Details not updated..!!");
				}
				return res;
			}

			public Response deleteVendor(String id) throws Exception {
				Response res=CommonUtils.getResponseObject("Succesfully deleted");
				try {
					  vendorRepository.deleteById(id);
					  res.setStatus(StatusCode.SUCCESS.name());
				    }
				catch (Exception ex) {
					logger.info("Exception deleteUser:", ex);
					logger.info("Exception Service:" + ex.getMessage());
					res.setStatus(StatusCode.ERROR.name());
					res.setMessage("Error Occured, Vendor Details not Deleted..!!");
				}
				return res;
			}		
						
		}
