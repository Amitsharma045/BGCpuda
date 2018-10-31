package com.bitGallon.complaintMgmt.user.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitGallon.complaintMgmt.entity.Aspect;
import com.bitGallon.complaintMgmt.entity.AspectRating;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.Feedback;
import com.bitGallon.complaintMgmt.json.JsonResponse;
import com.bitGallon.complaintMgmt.manager.AspectManager;
import com.bitGallon.complaintMgmt.manager.AspectRatingManager;
import com.bitGallon.complaintMgmt.manager.ComplaintManager;
import com.bitGallon.complaintMgmt.manager.FeedbackManager;
import com.bitGallon.complaintMgmt.property.ConstantProperty;
import com.bitGallon.complaintMgmt.rest.RestResource;


@Controller
@RequestMapping(value = "/bitGallon/api/feedback")
public class FeedbackServices extends RestResource {
	
	private Class clazz = FeedbackServices.class;


	@Autowired
	private  ComplaintManager complaintManager;
	
	@Autowired
	private  AspectManager aspectManager;
	
	@Autowired
	private  AspectRatingManager aspectRatingManager;
	
	@Autowired
	private  FeedbackManager feedbackManager;
	
	private JsonResponse jsonResponse;

	
	@RequestMapping(value = "/v1.0/submitFeedback", produces = { "application/json" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> submitFeedBack(HttpServletRequest request, @RequestParam("aspectRating")  HashMap<String, Object> aspectRating) throws Exception {
		String complaintNumber = request.getParameter(ConstantProperty.COMPLAINT_NUMBER);
		String serviceRating = request.getParameter(ConstantProperty.SERVICE_RATING);
		String serviceComment = request.getParameter(ConstantProperty.SERVICE_COMMENT);
		String recommendedPoint = request.getParameter(ConstantProperty.RECOMMENDATION_POINT);

		jsonResponse = new JsonResponse();

		try {
			ComplaintRegistration complaintRegistration = complaintManager.getComplaintByComplaintNumber(complaintNumber);
			if(complaintRegistration != null && 
					complaintRegistration.getStatus().equals(ConstantProperty.STATUS_RESOLVED))
			{
				Feedback feedback = getFeedBack(complaintRegistration, serviceRating, serviceComment, recommendedPoint);
				List<AspectRating> aspectRatingList = getAspectRating(complaintRegistration,aspectRating);
				Long id =feedbackManager.saveFeedback(feedback);
				if(id != null) {
					aspectRatingManager.saveAspectRating(aspectRatingList);
				} else {
					jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
					jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
					log(clazz, "NOT ABLE TO SAVE FEEDBACK", ConstantProperty.LOG_TRACE);
				}
			} else {
				jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
				jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
				log(clazz, "EITHER COMPLAINT NOT EXIST OR NOT IN RESOLVED STATUS", ConstantProperty.LOG_TRACE);
			}
		} catch(Exception ex) {
			jsonResponse.setStatusCode(ConstantProperty.SERVER_ERROR);
			jsonResponse.setMessage(ConstantProperty.INTERNAL_SERVER_ERROR);
			log(clazz, ex.getMessage(), ConstantProperty.LOG_ERROR);
			return sendResponse(jsonResponse);
		}

		return sendResponse(jsonResponse);
	}
	
	public Feedback getFeedBack(ComplaintRegistration complaint, String serviceRating, String serviceComment, 
			String recommendedPoint )
	{
		Feedback feedback = new Feedback();
		feedback.setServiceRating(serviceRating);
		feedback.setServiceComment(serviceComment);
		feedback.setRecommendedPoints(recommendedPoint);
		feedback.setComplaintRegistration(complaint);
		
		return feedback;
	}
	
	public List<AspectRating> getAspectRating(ComplaintRegistration complaintRegistration, HashMap<String, Object> aspectRating) {
		List<AspectRating> aspectList = new ArrayList<>();
		Iterator<Map.Entry<String, Object>> itr = aspectRating.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next(); 
			Aspect aspect = aspectManager.getAspect(entry.getKey());
			AspectRating rating = new AspectRating();
			rating.setComplaintRegistration(complaintRegistration);
			rating.setAspect(aspect);
			rating.setRatingValue(entry.getValue().toString());
			aspectList.add(rating);
		}
		return aspectList;
	}

}
