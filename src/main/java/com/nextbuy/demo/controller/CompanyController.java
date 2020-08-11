package com.nextbuy.demo.controller;

import com.nextbuy.demo.model.Company;
import com.nextbuy.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @GetMapping("/{id}")
    public Company findById(@PathVariable long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/")
    @ResponseBody
    public Collection<Company> findCompanies() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company updateCompany(@PathVariable("id") final String id, @RequestBody final Company company) {
        return company;
    }

}