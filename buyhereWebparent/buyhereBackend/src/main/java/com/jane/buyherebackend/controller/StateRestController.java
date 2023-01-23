/**
 * FileName: StateRestController
 * Author: jane
 * Date: 2023/1/16 23:19
 * Description:
 * Version:
 */

package com.jane.buyherebackend.controller;

import com.jane.buyherebackend.repository.StateRepository;
import com.jane.buyherecommon.entity.Country;
import com.jane.buyherecommon.entity.State;
import com.jane.buyherecommon.entity.StateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StateRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateRestController.class);

    @Autowired
    private StateRepository repo;

    @GetMapping("/states/list_by_country/{id}")
    public List<StateDTO> listByCountry(@PathVariable("id") Integer countryId) {

        LOGGER.info("StateRestController | listByCountry is called");

        LOGGER.info("StateRestController | listByCountry | countryId : " + countryId);

        List<State> listStates = repo.findByCountryOrderByNameAsc(new Country(countryId));

        LOGGER.info("StateRestController | listByCountry | listStates.size() : " + listStates.size());

        List<StateDTO> result = new ArrayList<>();

        for (State state : listStates) {
            result.add(new StateDTO(state.getId(), state.getName()));
        }

        LOGGER.info("StateRestController | listByCountry | result : " + result.toString());

        return result;
    }

    @PostMapping("/states/save")
    public String save(@RequestBody State state) {
        State savedState = repo.save(state);
        return String.valueOf(savedState.getId());
    }

    @DeleteMapping("/states/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }

    @PostMapping("/states/check_unique")
    @ResponseBody
    public String checkUnique(@RequestBody Map<String,String> data) {

        String name = data.get("name");

        LOGGER.info("StateRestController | checkUnique is called");

        LOGGER.info("StateRestController | checkUnique | name : " + name);

        State stateByName = repo.findByName(name);
        boolean isCreatingNew = stateByName == null;

        if (isCreatingNew) {
            if (stateByName != null) return "Duplicate";
        } else {
            if (stateByName != null && stateByName.getId() != null) {
                return "Duplicate";
            }
        }
        LOGGER.info("StateRestController | unique == true");
        return "OK";
    }
}
