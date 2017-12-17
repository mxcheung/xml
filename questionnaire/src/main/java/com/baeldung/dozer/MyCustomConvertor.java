package com.baeldung.dozer;

import org.dozer.ConfigurableCustomConverter;
import org.dozer.CustomConverter;

import xml.dest.questionnaire.Question;
import xml.source.questionnaire.RFIQuestion;

public class MyCustomConvertor implements ConfigurableCustomConverter {

	private String parameter;
	
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		System.out.println("MyCustomConvertor:" + sourceFieldValue );
        return "xxxx" + sourceFieldValue;
	}

	public void setParameter(String parameter) {
		 this.parameter = parameter;
		
	}


}