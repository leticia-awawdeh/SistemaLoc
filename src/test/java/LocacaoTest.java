/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.sistemalocacao.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;


/**
 *
 * @author leticia
 */
public class LocacaoTest {
    
    @Test
    public void testCalcularMultaSemAtraso() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);

        Locacao locacao = new Locacao(cliente, equipamento, LocalDate.now(), LocalDate.now().plusDays(5), 0.1);

        locacao.setDataDevolucao(LocalDate.now().plusDays(5));
        assertEquals(0.0, locacao.calcularMulta());
    }

    @Test
    public void testCalcularMultaComAtraso() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);

        Locacao locacao = new Locacao(cliente, equipamento, LocalDate.now(), LocalDate.now().plusDays(5), 0.1);

        locacao.setDataDevolucao(LocalDate.now().plusDays(7));
        assertEquals(10.0, locacao.calcularMulta());
    }

    @Test
    public void testDiasAtraso() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);

        Locacao locacao = new Locacao(cliente, equipamento, LocalDate.now(), LocalDate.now().plusDays(5), 0.1);

        locacao.setDataDevolucao(LocalDate.now().plusDays(8));
        assertEquals(3, locacao.getDiasAtraso());
    }

    @Test
    public void testConstrutorInvalidoEquipamentoNulo() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Locacao(cliente, null, LocalDate.now(), LocalDate.now().plusDays(5), 0.1));
        assertEquals("O equipamento não pode ser nulo.", exception.getMessage());
    }
}
