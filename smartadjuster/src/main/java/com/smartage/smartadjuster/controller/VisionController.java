package com.smartage.smartadjuster.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.vision.v1.Image;
import com.google.protobuf.ByteString;
import com.smartage.smartadjuster.dto.AnnotationDto;
import com.smartage.smartadjuster.service.VisionAPIService;

@RestController
public class VisionController {
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private VisionAPIService visionAPIService;
	
	Logger logger = LoggerFactory.getLogger(VisionController.class);
	
	@PostMapping("/images/upload")
	public List processImage(String imageUrl) throws Exception{
		
		logger.trace("in the process image method of vision controller service");
		
		byte[] imageBytes = StreamUtils.copyToByteArray(this.resourceLoader.getResource(imageUrl).getInputStream());
		Image image = Image.newBuilder().setContent(ByteString.copyFrom(imageBytes)).build();
		List<AnnotationDto> annotationList = visionAPIService.getImageFeatures(image);
		return annotationList;
	}	

}