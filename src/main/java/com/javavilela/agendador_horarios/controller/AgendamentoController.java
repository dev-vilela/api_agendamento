package com.javavilela.agendador_horarios.controller;

import com.javavilela.agendador_horarios.infrastructure.entity.AgendamenteEntity;
import com.javavilela.agendador_horarios.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<AgendamenteEntity> salvarAgendamento(@RequestBody AgendamenteEntity agendamenteEntity){
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamenteEntity));
    }

    @GetMapping
    public ResponseEntity<List<AgendamenteEntity>> buscarAgendamentoDia(@RequestParam LocalDate data){
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<AgendamenteEntity> atualizarAgendamento(@RequestBody AgendamenteEntity agendamenteEntity,
                                                                  @RequestParam String cliente,
                                                                  @RequestParam LocalDateTime dataHoraAgendamento){
        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamenteEntity,cliente, dataHoraAgendamento));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletetarAgendamento(@RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

}
