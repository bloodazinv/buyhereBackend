package com.jane.buyherebackend.controller;


import com.jane.buyherebackend.repository.CountryRepository;
import com.jane.buyherecommon.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CountryRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryRestController.class);

	@Autowired 
	private CountryRepository repo;

	@GetMapping("/countries/list")
	public List<Country> listAll() {
		return repo.findAllByOrderByNameAsc();
	}

	@PostMapping("/countries/save")
	public String save(@RequestBody Country country) {
		Country savedCountry = repo.save(country);
		return String.valueOf(savedCountry.getId());
	}

	@DeleteMapping("/countries/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	
	@PostMapping("/countries/check_unique")
	@ResponseBody
	public String checkUnique(@RequestBody Map<String,String> data) {
		
		String name = data.get("name");
		
		LOGGER.info("CountryRestController | checkUnique is called");
		
		LOGGER.info("CountryRestController | checkUnique | name : " + name);
		
		Country countryByName = repo.findByName(name);
		boolean isCreatingNew = countryByName == null;
		
		if (isCreatingNew) {
			if (countryByName != null) return "Duplicate";
		} else {
			if (countryByName != null && countryByName.getId() != null) {
				return "Duplicate";
			}
		}
		LOGGER.info("CountryRestController | Unique == true");
		return "OK";
	}
	
}
