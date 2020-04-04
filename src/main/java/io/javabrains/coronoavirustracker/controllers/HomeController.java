package io.javabrains.coronoavirustracker.controllers;

import io.javabrains.coronoavirustracker.models.LocationStats;
import io.javabrains.coronoavirustracker.services.CoronoVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronoVirusDataService coronoVirusDataService;

    @GetMapping("/")
    public String home(Model model) {

        List<LocationStats> allStats = coronoVirusDataService.getAllStats();
        int totalCases =  allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases =  allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
    return "home";
    }
}
