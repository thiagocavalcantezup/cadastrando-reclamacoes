package br.com.zup.handora.cadastrandoreclamacoes.reclamacao;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "reclamacoes", uniqueConstraints = {
        @UniqueConstraint(name = "UK_RECLAMACAO_TELEFONE_TEXTO", columnNames = {
                "hash_telefone", "textoReclamacao"})})
public class Reclamacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    private String emailUsuario;

    @Column(nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "numero", column = @Column(name = "numero_telefone", nullable = false, length = 17)),
            @AttributeOverride(name = "hash", column = @Column(name = "hash_telefone", nullable = false, length = 32))})
    private Telefone telefoneCelular;

    @Column(nullable = false, length = 4000)
    private String textoReclamacao;

    @Column(nullable = false)
    private LocalDateTime dataRegistro = LocalDateTime.now();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Reclamacao() {}

    public Reclamacao(String nomeUsuario, String emailUsuario, Telefone telefoneCelular,
                      String textoReclamacao) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.telefoneCelular = telefoneCelular;
        this.textoReclamacao = textoReclamacao;
    }

    public Long getId() {
        return id;
    }

}
