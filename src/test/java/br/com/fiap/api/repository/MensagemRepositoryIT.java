package br.com.fiap.api.repository;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional

public class MensagemRepositoryIT {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Test
    void devePermitirCriarTabela(){
        var totalDeRegistros = mensagemRepository.count();
        assertThat(totalDeRegistros).isNotNegative();
    }

    @Test
    void devePermitirRegistrarMensagem(){
        fail("Teste nao implementados");
    }

    @Test
    void devePermitirBuscarMensagem(){
        fail("Teste nao implementados");
    }

    @Test
    void devePermitirRemoverMensagem(){
        fail("Teste nao implementados");
    }

    @Test
    void devePermitirListarMensagens(){
        fail("Teste nao implementados");
    }

}
