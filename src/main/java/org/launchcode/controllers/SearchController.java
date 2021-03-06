package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        model.addAttribute("columns", ListController.columnChoices);
        //ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);

        if (searchType.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "Jobs containing '" + searchTerm + "' in Any Column" );
            model.addAttribute("jobs", jobs);
        }
        else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs containing '" + searchTerm + "' in " + searchType);
            model.addAttribute("jobs", jobs);
        }
        return "search";
    }
}