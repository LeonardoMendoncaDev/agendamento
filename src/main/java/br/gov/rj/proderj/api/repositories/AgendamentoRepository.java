package br.gov.rj.proderj.api.repositories;

import br.gov.rj.proderj.api.models.Agendamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AgendamentoRepository implements PanacheRepository<Agendamento> {

    public List<Agendamento> list() {
        return listAll();
    }

    @Transactional
    public Agendamento save(Agendamento agendamento) {
        persist(agendamento);
        return agendamento;
    }

    @Transactional
    public Agendamento update(Long id, Agendamento agendamento) {
        Agendamento agendamentoEntity = findById(id);
        agendamentoEntity.setAssunto(agendamento.getAssunto());
        agendamentoEntity.setCargo(agendamento.getCargo());
        agendamentoEntity.setData(agendamento.getData());
        agendamentoEntity.setCategoria(agendamento.getCategoria());
        agendamentoEntity.setDescricao(agendamento.getDescricao());
        agendamentoEntity.setEmail(agendamento.getEmail());
        agendamentoEntity.setHorario(agendamento.getHorario());
        agendamentoEntity.setLocal(agendamento.getLocal());
        agendamentoEntity.setPrevisaoPublico(agendamento.getPrevisaoPublico());
        agendamentoEntity.setSolicitante(agendamento.getSolicitante());
        agendamentoEntity.setSecretaria(agendamento.getSecretaria());
        agendamentoEntity.setTelefone(agendamento.getTelefone());
        persist(agendamentoEntity);
        return agendamentoEntity;
    }

    @Transactional
    public void remove(Long id) {
        Agendamento agendamentoEntity = findById(id);
        delete(agendamentoEntity);
    }

}
