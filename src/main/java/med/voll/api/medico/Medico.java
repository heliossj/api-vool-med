package med.voll.api.medico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.crm();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCrm() {
        return this.crm;
    }

    public Especialidade getEspecialidade() {
        return this.especialidade;
    }

    public Long getId() {
        return this.id;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Endereco getEndereco(){
        return this.endereco;
    }

    public void atualizaInformacoes(@Valid DadosAtualizaMedico dados) {
        if (dados.nome() !=null)
            this.nome = dados.nome();
        if (dados.telefone() != null)
            this.telefone = dados.telefone();
        if (dados.endereco() != null)
            this.endereco.atualizaInformacoes(dados.endereco());
    }

    public void excluir(Long id) {
        this.ativo = false;
    }
}
