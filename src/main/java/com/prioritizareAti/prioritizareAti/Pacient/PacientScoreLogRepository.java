package com.prioritizareAti.prioritizareAti.Pacient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacientScoreLogRepository extends JpaRepository<PacientScoreLog, Long> {
    List<PacientScoreLog> findByPacient_Id(Long pacientId);

    void deleteByPacientId(Long id);
}
