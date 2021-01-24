package br.gov.rj.proderj.api.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Agendamento extends PanacheEntity {

    @NotBlank(message="Solicitante não pode ser vazio")
    private String solicitante;

//    @NotBlank(message="Secretaria não pode ser vazio")
    private String secretaria;

//    @NotBlank(message="Cargo não pode ser vazio")
    private String cargo;

    @NotBlank(message="Telefone não pode ser vazio")
    private String telefone;

    @NotBlank(message="Email não pode ser vazio")
    private String email;

    @NotBlank(message="Assunto não pode ser vazio")
    private String assunto;

//    @NotBlank(message="Categoria não pode ser vazio")
    private Categoria categoria;

//    @NotBlank(message="Descricao não pode ser vazio")
    private String descricao;

    @CreationTimestamp
    @Column(name="data", insertable=true)
    private LocalDate data;

//    @NotBlank(message="Local não pode ser vazio")
    private String local;

    @CreationTimestamp
    @Column(name="horario", insertable=true)
    private LocalTime horario;

//    @NotBlank(message="Participantes não pode ser vazio")
    private String participantes;

//    @Column(name="previsao_publico")
    private Long previsaoPublico;

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(String secretaria) {
        this.secretaria = secretaria;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

    public Long getPrevisaoPublico() {
        return previsaoPublico;
    }

    public void setPrevisaoPublico(Long previsaoPublico) {
        this.previsaoPublico = previsaoPublico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(solicitante, that.solicitante) && Objects.equals(secretaria, that.secretaria) && Objects.equals(cargo, that.cargo) && Objects.equals(telefone, that.telefone) && Objects.equals(email, that.email) && Objects.equals(assunto, that.assunto) && categoria == that.categoria && Objects.equals(descricao, that.descricao) && Objects.equals(data, that.data) && Objects.equals(local, that.local) && Objects.equals(horario, that.horario) && Objects.equals(participantes, that.participantes) && Objects.equals(previsaoPublico, that.previsaoPublico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(solicitante, secretaria, cargo, telefone, email, assunto, categoria, descricao, data, local, horario, participantes, previsaoPublico);
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "solicitante='" + solicitante + '\'' +
                ", secretaria='" + secretaria + '\'' +
                ", cargo='" + cargo + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", assunto='" + assunto + '\'' +
                ", categoria=" + categoria +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", local='" + local + '\'' +
                ", horario=" + horario +
                ", participantes=" + participantes +
                ", previsaoPublico=" + previsaoPublico +
                '}';
    }
}
