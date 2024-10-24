package com.example.registration_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.registration_api.entity.Query;
import com.example.registration_api.payload.ApiResponse;
import com.example.registration_api.service.QueryService;

import java.util.List;

@RestController
@RequestMapping("/api/queries")
@CrossOrigin(origins = "http://localhost:3000")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllQueries() {
        List<Query> queries = queryService.getAllQueries();
        return ResponseEntity.ok(new ApiResponse("Queries retrieved successfully", true, queries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getQueryById(@PathVariable Long id) {
        return queryService.getQueryById(id)
                .map(query -> ResponseEntity.ok(new ApiResponse("Query retrieved successfully", true, query)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Query not found", false, null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createQuery(@RequestBody Query query) {
        Query createdQuery = queryService.createQuery(query);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Query created successfully", true, createdQuery));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateQuery(@PathVariable Long id, @RequestBody Query queryDetails) {
        Query updatedQuery = queryService.updateQuery(id, queryDetails);
        return ResponseEntity.ok(new ApiResponse("Query updated successfully", true, updatedQuery));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteQuery(@PathVariable Long id) {
        queryService.deleteQuery(id);
        return ResponseEntity.ok(new ApiResponse("Query deleted successfully", true, null));
    }
}
