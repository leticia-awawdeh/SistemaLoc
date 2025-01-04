/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.sistemalocacao.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author leticia
 */
public class GerenciadorDadosTest {
    
    @BeforeEach
    public void setUp() {
        GerenciadorDados.getListaClientes().clear();
        GerenciadorDados.getListaEquipamentos().clear();
    }

    @Test
    public void testAdicionarCliente() {
        Cliente cliente = new Cliente("Ave Maria", "00000000000", "000000000");
        GerenciadorDados.adicionarCliente(cliente);

        assertTrue(GerenciadorDados.getListaClientes().contains(cliente));
    }

    @Test
    public void testAdicionarClienteDuplicado() {
        Cliente cliente1 = new Cliente("Maria", "12345678901", "999999999");
        Cliente cliente2 = new Cliente("Joana", "12345678901", "888888888");

        GerenciadorDados.adicionarCliente(cliente1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> GerenciadorDados.adicionarCliente(cliente2));
        assertEquals("Cliente com CPF já cadastrado.", exception.getMessage());
    }

    @Test
    public void testIsCpfCadastrado() {
        Cliente cliente = new Cliente("Maria", "12345678901", "999999999");
        GerenciadorDados.adicionarCliente(cliente);

        assertTrue(GerenciadorDados.isCpfCadastrado("12345678901"));
        assertFalse(GerenciadorDados.isCpfCadastrado("98765432100"));
    }

    @Test
    public void testRegistrarLocacao() {
        Cliente cliente = new Cliente("João", "12345678901", "999999999");
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);
        GerenciadorDados.adicionarCliente(cliente);

        GerenciadorDados.registrarLocacao(cliente, equipamento);

        assertEquals(cliente, equipamento.getCliente());
        assertEquals(com.sistemalocacao.Status.ALUGADO, equipamento.getStatus());
    }
}
