
package com.example.ems.repository;

import com.example.ems.model.PunchLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PunchLogRepository extends JpaRepository<PunchLog, Long> {
}
