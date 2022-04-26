package br.com.zup.handora.cadastrandoreclamacoes.reclamacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long> {

    boolean existsByTextoReclamacaoAndTelefoneCelular_Hash(String texto, byte[] hash);

}
