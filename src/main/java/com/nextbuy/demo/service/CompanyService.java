package com.nextbuy.demo.service;



import com.nextbuy.demo.model.Company;
import com.nextbuy.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

}