package com.javavilela.agendador_horarios.infrastructure.repository;


import com.javavilela.agendador_horarios.infrastructure.entity.AgendamenteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<AgendamenteEntity, Long> {

    AgendamenteEntity findByServicoAndDataHoraAgendamentoBetween
            (String servico,LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal);

    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

   List<AgendamenteEntity>  findByDataHoraAgendamentoBetween(LocalDateTime dataHoraIncial, LocalDateTime dataHoraFinal);

    AgendamenteEntity findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

}
