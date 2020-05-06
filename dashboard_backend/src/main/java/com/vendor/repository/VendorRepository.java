package com.vendor.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vendor.model.Vendor;
import com.vendor.response.Response;

public interface VendorRepository extends CrudRepository<Vendor, String>
{

	    	List<Vendor> findByUsername(String username);

			List<Vendor> findByVendorScoreMonth(String vendorScoreMonth);

			List<Vendor> findByVendorname(String vendorname);



}
