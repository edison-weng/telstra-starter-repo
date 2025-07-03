package au.com.telstra.simcardactivator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.com.telstra.simcardactivator.entity.Record;
import au.com.telstra.simcardactivator.repository.RecordRepository;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    
    @Autowired
    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
    
    public Record save(Record newRecord) {
        return recordRepository.save(newRecord);
    }

    public Record findById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }
} 