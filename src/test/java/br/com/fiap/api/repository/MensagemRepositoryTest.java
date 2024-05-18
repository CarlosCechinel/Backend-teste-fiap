package br.com.fiap.api.repository;
import br.com.fiap.api.model.Mensagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MensagemRepositoryTest {

    @Mock
    private MensagemRepository mensagemRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    void devePermitirRegistrarMensagem() {
        var mensagem = gerarMensagem();

        when(mensagemRepository.save(mensagem)).thenReturn(mensagem);
        // act
        var mensagemRecebida = mensagemRepository.save(mensagem);
        //Asset
        assertThat(mensagemRecebida)
                .isNotNull()
                .isEqualTo(mensagem);
        verify(mensagemRepository, times(1)).save(any(Mensagem.class));
    }

    @Test
    void devePermitirRemoverMensagens() {
        var id = UUID.randomUUID();
        doNothing().when(mensagemRepository).deleteById(any(UUID.class));
        //Act
        mensagemRepository.deleteById(id);
        //Asset
        verify(mensagemRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void devePermitirBuscarMensagens() {
        //arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMensagem();
        mensagem.setId(id);

        when(mensagemRepository.findById(any(UUID.class)))
        .thenReturn(java.util.Optional.of(mensagem));

        //Act
        var mensagemRecebidaOpicional = mensagemRepository.findById(id);
        //Asset
        assertThat(mensagemRecebidaOpicional)
               .isPresent()
               .containsSame(mensagem);
        mensagemRecebidaOpicional.ifPresent(mensagemRecebida -> {
            assertThat(mensagemRecebida.getId()).isEqualTo(mensagem.getId());
            assertThat(mensagemRecebida.getConteudo()).isEqualTo(mensagem.getConteudo());
        });
        verify(mensagemRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void devePermitirListarMensagens(){
        //arrange
        var mensagem1 = gerarMensagem();
        var mensagem2 = gerarMensagem();
        var listamensagens = Arrays.asList(
                mensagem1,
                mensagem2);
        when(mensagemRepository.findAll()).thenReturn(listamensagens);
        //act
        var mensagensRecebidas = mensagemRepository.findAll();
        //Assert
        assertThat(mensagensRecebidas).hasSize(2)
                .containsExactlyInAnyOrder(mensagem1,mensagem2);
        verify(mensagemRepository, times(1)).findAll();

    }

    private Mensagem gerarMensagem(){
        return  Mensagem.builder()
                .usuario("Jose")
                .conteudo("Conteudo da mensagem")
                .build();

    }
}
