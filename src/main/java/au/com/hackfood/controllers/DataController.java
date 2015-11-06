package au.com.hackfood.controllers;

import au.com.hackfood.model.WeeklyProfit;
import au.com.hackfood.service.DataService;
import au.com.hackfood.util.AppUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService dataService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getFormView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("sampleForm");
        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getListView() {
        ModelAndView model = new ModelAndView();
        model.setViewName("list");
        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody String json) {
        System.out.println("############### " + json);
        return AppUtils.SUCCESS;
    }

    @RequestMapping(value = "/getWeeklyProfits", method = RequestMethod.POST)
    public ResponseEntity<String> getData() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            List<WeeklyProfit> resultList = dataService.findAll();
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(resultList);
//            json.put("status", AppUtils.SUCCESS);
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } catch (Exception e) {
            json.put("status", AppUtils.FAIL);
            json.put("errorMsg", e.getMessage());
            return new ResponseEntity<>(json.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/getPaginationData/{pageSize}/{page}")
    public @ResponseBody
    List<Map<String, Object>> getPaginationData(@PathVariable String pageSize, @PathVariable String page) {

        List<Map<String, Object>> activeTeamMap = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 1000; i++) {
            Map<String, Object> dropDownData = new HashMap<String, Object>();
            dropDownData.put("Name", "Java Honk");
            dropDownData.put("Positon", "Architect");
            dropDownData.put("Salary", "$200,800");
            dropDownData.put("Office", "NY");
            dropDownData.put("Start_Date", "05/05/2010");
            activeTeamMap.add(dropDownData);

            dropDownData = new HashMap<String, Object>();
            dropDownData.put("Name", "Igor Vornovitsky");
            dropDownData.put("Positon", "Sr. Architect");
            dropDownData.put("Salary", "$200,800");
            dropDownData.put("Office", "NY");
            dropDownData.put("Start_Date", "05/05/2011");
            activeTeamMap.add(dropDownData);

            dropDownData = new HashMap<String, Object>();
            dropDownData.put("Name", "Ramesh Arrepu");
            dropDownData.put("Positon", "Architect");
            dropDownData.put("Salary", "$200,400");
            dropDownData.put("Office", "NY");
            dropDownData.put("Start_Date", "05/05/2009");
            activeTeamMap.add(dropDownData);
        }

        return activeTeamMap;
    }

}
