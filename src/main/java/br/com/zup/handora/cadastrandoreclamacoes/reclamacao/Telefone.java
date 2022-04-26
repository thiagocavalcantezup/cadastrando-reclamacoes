package br.com.zup.handora.cadastrandoreclamacoes.reclamacao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.persistence.Embeddable;

@Embeddable
public class Telefone {

    private String numero;
    private byte[] hash;

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Telefone() {}

    public Telefone(String telefone) {
        this.numero = anonymize(telefone);
        this.hash = hash(telefone);
    }

    @Override
    public String toString() {
        return numero;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(hash);
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Telefone other = (Telefone) obj;
        if (!Arrays.equals(hash, other.hash))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    public String anonymize(String telefone) {
        return telefone.replaceAll(".*([0-9]{4})", "****$1");
    }

    public byte[] hash(String telefone) {
        try {
            return MessageDigest.getInstance("SHA3-256")
                                .digest(telefone.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um telefone: " + telefone);
        }
    }

    public byte[] getHash() {
        return hash;
    }

}
