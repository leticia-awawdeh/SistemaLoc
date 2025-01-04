/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import SistemaLocacao.Classes.GerenciadorDados;
import SistemaLocacao.Classes.Status;
import SistemaLocacao.Classes.Cliente;
import SistemaLocacao.Classes.Equipamento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author leticia
 */
public class ClienteTest {
    
    @Test
    public static void adicionarEquipamento(Equipamento equipamento) {
        if (equipamento.getStatus() != Status.DISPONIVEL) {
            throw new IllegalArgumentException("Equipamento não está disponível para locação.");
        }
        GerenciadorDados.adicionarEquipamento(equipamento);
    }
        
        
    @Test
    public void testAdicionarEquipamentoDisponivel() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);

        equipamento.setStatus(Status.DISPONIVEL);
        GerenciadorDados.adicionarEquipamento(equipamento);
        assertEquals(Status.DISPONIVEL, equipamento.getStatus());
    }
    
    @Test
    public void testAdicionarEquipamentoNaoDisponivel() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);

        equipamento.setStatus(Status.ALUGADO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ClienteTest.adicionarEquipamento(equipamento);
        });
        assertEquals("Equipamento não está disponível para locação.", exception.getMessage());
    }
    
    @Test
    public void testModificarNomeCliente() {
        Cliente cliente = new Cliente("Maria", "12345678901", "999999999");
        cliente.setNomeCli("João");

        assertEquals("João", cliente.getNomeCli());
    }
}
