package com.smartage.smartadjuster.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.smartage.smartadjuster.dto.AnnotationDto;

@Service
public class VisionAPIService {

	@Autowired
	private ImageAnnotatorClient imageAnnotatorClient;

	public List<AnnotationDto> getImageFeatures(Image image) {
		
		List<AnnotateImageRequest> requests = new ArrayList<>();
		Feature feature = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image).build();
		BatchAnnotateImagesResponse response = imageAnnotatorClient.batchAnnotateImages(Collections.singletonList(request));
	
		List<AnnotateImageResponse> responses = response.getResponsesList();
		List<AnnotationDto> annotationDtoList = new ArrayList<AnnotationDto>();

		for (AnnotateImageResponse res : responses) {
			if (res.hasError()) {
				String error = res.getError().getMessage();
				System.out.println(error);
				return  null;
			}

			for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
				
				AnnotationDto annotationDto =  new AnnotationDto();
				BeanUtils.copyProperties(annotation, annotationDto);
				annotationDtoList.add(annotationDto);
				annotation.getAllFields().forEach((k, v) ->
	              System.out.printf("%s : %s\n", k, v.toString()));
			}

		}
		return annotationDtoList;
	}
}
