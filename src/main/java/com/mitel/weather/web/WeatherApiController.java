package com.mitel.weather.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mitel.weather.exception.WeatherAppRestException;
import com.mitel.weather.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(method = { RequestMethod.GET }, value = "/now")
		public @ResponseBody ResponseEntity<?> getPresentWeatherReport(@RequestParam(value = "city", required = true) String city) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(weatherService.getPresentReport(city));
		} catch (WeatherAppRestException e) {			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		} 
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/weekly")
		public @ResponseBody ResponseEntity<?> getWeeklyWeatherReport(@RequestParam(value = "city", required = true) String city)  {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(weatherService.getWeeklyReport(city));
			} catch (WeatherAppRestException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = "/search")
		public @ResponseBody ResponseEntity<?> getAutoSuggestResults(@RequestParam(value = "city", required = true) String city)   {
			try {
				return ResponseEntity.status(HttpStatus.OK).body(weatherService.getSearchResults(city));
			} catch (WeatherAppRestException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
	}
}

