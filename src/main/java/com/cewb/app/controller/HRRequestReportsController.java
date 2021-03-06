package com.cewb.app.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cewb.app.config.RequestStatus;
import com.cewb.app.dto.request.HrRequestDto;
import com.cewb.app.model.HRRequest;
import com.cewb.app.service.HRRequestService;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Log4j2
public class HRRequestReportsController {
	
	@Autowired
	HRRequestService requestService;
	
	// Save reports by users
	@PostMapping("/reports")
	public HRRequest createRequest(@RequestBody HRRequest request) {
		log.info("Create report endpoint");
		request.setStatus(RequestStatus.PENDING.getDescription());
		
		return requestService.save(request);
	}
	
	// Get all reports
	@GetMapping("/reports")
	public Page<HRRequest> getReports(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
		log.info("Get all reports");
		
		return requestService.findAll(pageNum);
	}
	
	// Update report status
	@PutMapping("/reports/status")
	public HRRequest updateRequest(@RequestBody HRRequest request) {
		log.info("Update report status endpoint");
		
		HRRequest item = requestService.findById(request.getId());
		item.setStatus(request.getStatus());
		
		return requestService.update(item);
	}

	//Delete request
	@DeleteMapping("/reports/{id}")
	public HRRequest deleteCompany(@PathVariable Long id) {
		log.info("Delete request with id " + id);
		return requestService.delete(id);
	}
	
	@PostMapping("/reports/generate")
	public ResponseEntity<byte[]> generateReport(@RequestBody HrRequestDto hrRequest) throws JRException, IOException, URISyntaxException {
		log.info("Reports generation endpoint");
		byte[] pdf = requestService.generateReport(hrRequest);
		return new ResponseEntity<byte[]>(pdf, HttpStatus.OK);
	}

}
