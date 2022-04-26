package br.com.zup.handora.cadastrandoreclamacoes.reclamacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReclamacaoRequest {

    @NotBlank
    private String nomeUsuario;

    @NotBlank
    @Email
    private String emailUsuario;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$")
    private String numeroTelefone;

    @NotBlank
    @Size(max = 4000)
    private String textoReclamacao;

    public ReclamacaoRequest() {}

    public ReclamacaoRequest(@NotBlank String nomeUsuario, @NotBlank @Email String emailUsuario,
                             @NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}$") String numeroTelefone,
                             @NotBlank @Size(max = 4000) String textoReclamacao) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.numeroTelefone = numeroTelefone;
        this.textoReclamacao = textoReclamacao;
    }

    public Reclamacao toModel() {
        Telefone telefoneCelular = new Telefone(numeroTelefone);

        return new Reclamacao(nomeUsuario, emailUsuario, telefoneCelular, textoReclamacao);
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getTextoReclamacao() {
        return textoReclamacao;
    }

}
