package com.example.registration_api.service;

import com.example.registration_api.entity.Query;
import com.example.registration_api.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    public List<Query> getAllQueries() {
        return queryRepository.findAll();
    }

    public Optional<Query> getQueryById(Long id) {
        return queryRepository.findById(id);
    }

    public Query createQuery(Query query) {
        return queryRepository.save(query);
    }

    public Query updateQuery(Long id, Query queryDetails) {
        Query query = queryRepository.findById(id).orElseThrow();
        query.setOwnerName(queryDetails.getOwnerName());
        query.setContactNumber(queryDetails.getContactNumber());
        query.setQueries(queryDetails.getQueries());
        query.setStatus(queryDetails.getStatus());
        return queryRepository.save(query);
    }

    public void deleteQuery(Long id) {
        Query query = queryRepository.findById(id).orElseThrow();
        queryRepository.delete(query);
    }
}
