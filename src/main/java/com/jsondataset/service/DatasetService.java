package com.jsondataset.service;

import com.jsondataset.dao.DatasetRecordRepository;
import com.jsondataset.model.DatasetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatasetService {

    @Autowired
    DatasetRecordRepository repository;


    public DatasetRecord insertRecord(String datasetName, Map<String, Object> record) {
        DatasetRecord entity = new DatasetRecord();
        entity.setDatasetName(datasetName);
        entity.setData(record);
        return repository.save(entity);
    }

    public Map<String, Object> queryDataset(String datasetName, String groupBy, String sortBy, String order) {
        List<DatasetRecord> records = repository.findByDatasetName(datasetName);

        List<Map<String, Object>> dataList = records.stream()
                .map(DatasetRecord::getData)
                .collect(Collectors.toList());

        if (groupBy != null) {
            return Map.of("groupedRecords", dataList.stream()
                    .collect(Collectors.groupingBy(r -> String.valueOf(r.get(groupBy)))));
        }

        if (sortBy != null) {
            Comparator<Map<String, Object>> comparator = Comparator.comparing(r -> (Comparable) r.get(sortBy));
            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            List<Map<String, Object>> sorted = dataList.stream()
                    .sorted(comparator)
                    .toList();
            return Map.of("sortedRecords", sorted);
        }

        return Map.of("records", dataList);
    }
}

