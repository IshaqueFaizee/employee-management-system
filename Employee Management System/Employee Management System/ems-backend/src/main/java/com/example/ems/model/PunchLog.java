
package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "punch_logs")
@Data
public class PunchLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private LocalDateTime punchIn;
    private LocalDateTime punchOut;
}
