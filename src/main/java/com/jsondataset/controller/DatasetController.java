package com.jsondataset.controller;

import com.jsondataset.model.DatasetRecord;
import com.jsondataset.service.DatasetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dataset")
@RequiredArgsConstructor
public class DatasetController {
    @Autowired
    DatasetService service;

    @PostMapping("/{datasetName}/record")
    public ResponseEntity<?> insertRecord(@PathVariable String datasetName, @RequestBody Map<String, Object> record) {
        DatasetRecord saved = service.insertRecord(datasetName, record);
        return ResponseEntity.ok(Map.of(
                "message", "Record added successfully",
                "dataset", datasetName,
                "recordId", saved.getId()
        ));
    }

    @GetMapping("/{datasetName}/query")
    public ResponseEntity<?> queryDataset(
            @PathVariable String datasetName,
            @RequestParam(required = false) String groupBy,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        return ResponseEntity.ok(service.queryDataset(datasetName, groupBy, sortBy, order));
    }
}

