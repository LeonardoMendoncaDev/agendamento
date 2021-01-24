package br.gov.rj.proderj.api.resources;

import br.gov.rj.proderj.api.models.Agendamento;
import br.gov.rj.proderj.api.repositories.AgendamentoRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/agendamentos")
@Produces(MediaType.APPLICATION_JSON)
public class AgendamentoResource {

    @Inject
    AgendamentoRepository agendamentoRepository;

    @GET
    public List<Agendamento> list() {
        return agendamentoRepository.listAll();
    }

    @POST
    public Response create(@Valid Agendamento agendamento) {
        Agendamento agendamentoEntity = agendamentoRepository.save(agendamento);
        return Response.ok(agendamentoEntity).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Agendamento agendamento) {
        Agendamento agendamentoUpdated = agendamentoRepository.update(id, agendamento);
        return Response.ok(agendamentoUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        agendamentoRepository.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}