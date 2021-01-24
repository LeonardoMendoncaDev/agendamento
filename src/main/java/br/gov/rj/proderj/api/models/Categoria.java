package br.gov.rj.proderj.api.models;

public enum Categoria {

    OBRA("obra"),
    REUNIAO("reunião"),
    EVENTO("evento"),
    INAUGURACAO("inauguração"),
    VISITA("visita"),
    GOVERNO("governo");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
