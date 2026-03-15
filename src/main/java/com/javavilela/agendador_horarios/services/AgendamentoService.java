package com.javavilela.agendador_horarios.services;


import com.javavilela.agendador_horarios.infrastructure.entity.AgendamenteEntity;
import com.javavilela.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamenteEntity salvarAgendamento(AgendamenteEntity agendamenteEntity){

        LocalDateTime horaAgendamento = agendamenteEntity.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamenteEntity.getDataHoraAgendamento().plusHours(1); // VERIFICA SE NÂO TEM HORA MARCADA

      AgendamenteEntity reservas =  agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamenteEntity.getServico(),
                horaAgendamento, horaFim);

      if (Objects.nonNull(agendamenteEntity)){
          throw new RuntimeException("Horário já está preenchido!");
      }
      return agendamentoRepository.save(agendamenteEntity);

    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente){
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public AgendamenteEntity buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiroHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

       return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiroHoraDia, horaFinalDia);
    }

    public AgendamenteEntity alterarAgendamento(AgendamenteEntity agendamenteEntity, String cliente, LocalDateTime dataHoraAgendamento){
    AgendamenteEntity agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if (Objects.isNull(agendamenteEntity)){
            throw new RuntimeException("Horário não está preenchido!");
        }

        agendamenteEntity.setId(agenda.getId());
       return agendamentoRepository.save(agendamenteEntity);
    }

}
