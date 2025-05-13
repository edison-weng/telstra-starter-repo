package au.com.telstra.simcardactivator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import au.com.telstra.simcardactivator.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    
} 