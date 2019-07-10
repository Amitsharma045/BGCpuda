package com.bitGallon.complaintMgmt.user.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.util.AmazonS3FilesManager;

@Controller
@RequestMapping(value = "/bitGallon/")
public class TestImg {

	static AmazonS3FilesManager fileManager = new AmazonS3FilesManager();
	
	@RequestMapping(value = "api/uploadedContents/{fileName:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<InputStreamResource> uploadedContents(@PathVariable("fileName") String fileName) throws Exception 
	{
		if (fileName != null && !fileName.isEmpty()) {
			S3ObjectInputStream s3is = fileManager.getUploadedFile(fileName);
			if (s3is != null) {
				return ResponseEntity.ok().contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
						.cacheControl(CacheControl.noCache())
						.header("Content-Disposition", "attachment; filename=" + fileName)
						.body(new InputStreamResource(s3is));
			}
		}
		return ResponseEntity.status(Integer.parseInt(ConstantProperty.SERVER_ERROR)).build();
		
	}
}
