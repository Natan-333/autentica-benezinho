package br.com.fiap.pessoa.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_NUM", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NUM_CNPJ", columnNames = "NUM_CNPJ")
})
public class PessoaJuridica extends Pessoa {
    private String CNPJ;
    private Set<Pessoa> socios = new LinkedHashSet<>();
    public String getCNPJ() {
        return CNPJ;
    }
    public PessoaJuridica setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        return this;
    }

    @ManyToMany
    public Set<Pessoa> getSocios() {
        return Collections.unmodifiableSet(socios);
    }

    public PessoaJuridica addSocio(Pessoa pessoa) {
        if (Objects.nonNull(pessoa)) {
            if (pessoa.equals(this)) throw new RuntimeException("Eu não posso ser o meu próprio sócio");
            this.socios.add(pessoa);
        }
        return this;
    }

    public PessoaJuridica removerSocio(Pessoa pessoa) {
        this.socios.remove(pessoa);
        return this;
    }

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ",  nome='" + nome + '\'' +
                ",  nascimento=" + nascimento + '\'' +
                ",  CNPJ='" + CNPJ + '\'' +
                ",  socios=" + socios +
                " } ";
    }
}
