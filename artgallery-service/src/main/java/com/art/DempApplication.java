package com.art;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DempApplication {

	public static void main(String[] args) {
		SpringApplication.run(DempApplication.class, args);

	}
	
	@Bean // equivalent to <bean id ..../> in XML file
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setMatchingStrategy(MatchingStrategies.STRICT)
			// only matching property names between source and destination will be
			// transferred during the mapping
			.setPropertyCondition(Conditions.isNotNull());
		// only non-null properties will be transferred from source to destination during mapping
		return modelMapper;
	}
}
