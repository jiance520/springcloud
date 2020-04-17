package com.action;

import com.service.MongodbService;
import com.utils.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IncomeController {
    @Autowired
    private MongodbService mongodbService;

    @PostMapping("/mongo/save")
    public String saveObj(@RequestBody Income income) {
        return mongodbService.saveObj(income);
    }

    @GetMapping("/mongo/findAll")
    public List<Income> findAll() {
        return mongodbService.findAll();
    }

    @GetMapping("/mongo/findOne")
    public Income findOne(@RequestParam String id) {
        return mongodbService.getIncomeById(id);
    }

    @GetMapping("/mongo/findOneByName")
    public Income findOneByName(@RequestParam String name) {
        return mongodbService.getIncomeByName(name);
    }

    @PostMapping("/mongo/update")
    public String update(@RequestBody Income income) {
        return mongodbService.updateIncome(income);
    }

    @PostMapping("/mongo/delOne")
    public String delOne(@RequestBody Income income) {
        return mongodbService.deleteIncome(income);
    }

    @GetMapping("/mongo/delById")
    public String delById(@RequestParam String id) {
        return mongodbService.deleteIncomeById(id);
    }

    @GetMapping("/mongo/findlikes")
    public List<Income> findByLikes(@RequestParam String search) {
        return mongodbService.findByLikes(search);
    }
}
