package br.com.zup.handora.cadastrandoreclamacoes.reclamacao;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(ReclamacaoController.BASE_URI)
public class ReclamacaoController {

    public final static String BASE_URI = "/reclamacoes";

    private final ReclamacaoRepository reclamacaoRepository;

    public ReclamacaoController(ReclamacaoRepository reclamacaoRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ReclamacaoRequest reclamacaoRequest,
                                    UriComponentsBuilder ucb) {
        byte[] hash = new Telefone(reclamacaoRequest.getNumeroTelefone()).getHash();

        if (reclamacaoRepository.existsByTextoReclamacaoAndTelefoneCelular_Hash(
            reclamacaoRequest.getTextoReclamacao(), hash
        )) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Já existe uma reclamação com esse texto cadastrada para esse telefone."
            );
        }

        Reclamacao reclamacao = reclamacaoRepository.save(reclamacaoRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(reclamacao.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
