package br.com.fiap.pessoa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Table(name = "TB_NR", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NR_CPF", columnNames = "NR_CPF")
})

public class PessoaFisica extends Pessoa {
    private String CPF;
    private Sexo sexo;
    private Set<PessoaFisica> filhos = new LinkedHashSet<>(); //Os meus filhos


    public PessoaFisica() {
    }
    public PessoaFisica(Long id, String nome, LocalDate nascimento, String CPF, Sexo sexo, Set<PessoaFisica> filhos) {
        super(id, nome, nascimento);
        this.CPF = CPF;
        this.sexo = sexo;
        this.filhos = filhos;
    }

    /**
     * Método para adicionar um filho para a pessoa
     * <p>
     * Aqui eu, ou seja, this (PessoaFisica) é pai ou mãe.
     * O atributo "filhos" é o conjunto de filhos que eu tenho.
     *
     * @param filho
     * @return PessoaFisica
     */

    @ManyToMany
    public PessoaFisica addFilho(PessoaFisica filho) {
        if (filho.equals(this)) throw new RuntimeException("Eu não posso ser ao mesmo tempo pai e filho");
        //Adiciono um filho meu
        this.filhos.add(filho);
        return this;
    }

    /**
     * Método para remover um filho da pessoa
     *
     * @param filho
     * @return PessoaFisica
     */
    public PessoaFisica removeFilho(PessoaFisica filho) {
        this.filhos.remove(filho);
        return this;
    }

    public String getCPF() {
        return CPF;
    }

    public PessoaFisica setCPF(String CPF) {
        this.CPF = CPF;
        return this;
    }


    public Sexo getSexo() {
        return sexo;
    }

    public PessoaFisica setSexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    /**
     * Getter imutável para a listagem de filhos
     *
     * @return
     */
    public Set<PessoaFisica> getFilhos() {
        return Collections.unmodifiableSet(filhos);
    }


    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ",  nome='" + nome + '\'' +
                ",  nascimento=" + nascimento + '\'' +
                ",  CPF='" + CPF + '\'' +
                ",  sexo=" + sexo +
                ",  filhos=" + filhos +
                " } ";
    }
}
