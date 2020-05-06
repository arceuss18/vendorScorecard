package com.vendor.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Hashtable;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendor.constants.StatusCode;
import com.vendor.response.ErrorObject;
import com.vendor.response.Response;

public class CommonUtils {
	static ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	
	public static String encriptString(String strToEncript)
	{
		String returnString = null;
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strToEncript.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) 
				{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				}
			returnString = sb.toString();
			return returnString;
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return returnString;
		}
	}

	public static String encriptURL(String urlString) 
	{
		Hashtable<String, String> map = new Hashtable<String, String>();
		map.put("url", urlString);
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(map.toString().getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
	}

	public static String generateRandomId() 
	{
		  
		  return UUID.randomUUID().toString();
	}

	public static String GenerateRandomID()
	{
		  UUID uuid = UUID.randomUUID();
		  int l =(int) ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
		  return Long.toString(l, Character.MAX_RADIX);
	}
	
	public static String getJson(Object obj) 
	{
		try 
		{
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return mapper.writeValueAsString(obj);
		} 
		catch (JsonProcessingException e)
		{
			logger.error("getJsonResponse:Error in json processing: ", e);
		}
		return "";
	}

	public static Object getObject(String str) throws IOException 
	{
		try 
		{
			return mapper.readValue(str, Object.class);
		} 
		catch (JsonProcessingException e) 
		{
			logger.error("getJsonResponse:Error in json processing: ", e);
		}
		return "";
	}

	public static Response getResponseObject(String message) 
	{
		Response response = new Response();
		response.setStatus(StatusCode.SUCCESS.name());
		response.setMessage(message);
		return response;
	}

	public static ErrorObject getErrorResponse(String title, String detail) 
	{
		ErrorObject errorObject = new ErrorObject();
		errorObject.setTitle(title);
		errorObject.setDetail(detail);
		return errorObject;
	}

}
