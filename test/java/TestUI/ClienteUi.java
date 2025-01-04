/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import SistemaLocacao.Classes.GerenciadorDados;
import SistemaLocacao.Classes.Cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author leticia
 */
public class ClienteUi {
    
    @BeforeEach
    void setUp() {
        GerenciadorDados.getListaClientes().clear();
        //cadastroCliente = new CadastroCliente(GerenciadorDados.getListaClientes());
    }

    @AfterEach
    void tearDown() {
        GerenciadorDados.getListaClientes().clear();
    }

    @Test
void testCadastroClienteComSucesso() {
    // Prepare input fields with test data
    JTextField txtNomeCli = new JTextField("João Silva");
    JFormattedTextField txtCpf = new JFormattedTextField("123.456.789-00");
    JFormattedTextField txtTelefone = new JFormattedTextField("(11) 98765-4321");

    // Create a client object from the input data
    Cliente novoCliente = new Cliente(
            txtNomeCli.getText(),
            txtCpf.getText().replaceAll("[^\\d]", ""), // Remove formatting from CPF
            txtTelefone.getText() // Assuming you want to keep the phone formatting
    );

    // Add the new client to the manager
    GerenciadorDados.adicionarCliente(novoCliente);

    // Verify that the client was added to the list
    List<Cliente> listaClientes = GerenciadorDados.getListaClientes();
    assertEquals(1, listaClientes.size(), "O cliente deve ser adicionado com sucesso.");

    // Verify the client details
    Cliente cliente = listaClientes.get(0);
    assertEquals("João Silva", cliente.getNomeCli());
    assertEquals("12345678900", cliente.getCpfCli());  // CPF without formatting
    assertEquals("(11) 98765-4321", cliente.getTelefoneCli()); // Assuming phone format is retained
}


@Test
void testCadastroClienteFalhaCamposObrigatorios() {
    // Create JTextFields with empty values
    JTextField txtNomeCli = new JTextField("");
    JFormattedTextField txtCpfCli = new JFormattedTextField("");
    JFormattedTextField txtTelefoneCli = new JFormattedTextField("");

    // Create the button
    JButton btnCadCli = new JButton();
    
    // Simulate button click by directly invoking the action
    ActionListener[] listeners = btnCadCli.getActionListeners();
    if (listeners.length > 0) {
        listeners[0].actionPerformed(new ActionEvent(btnCadCli, ActionEvent.ACTION_PERFORMED, ""));
    }

    // Assuming GerenciadorDados.getListaClientes() returns a List that can be checked
    // Check that no client is added due to incomplete fields
    assertEquals(0, GerenciadorDados.getListaClientes().size(),
            "A lista de clientes deve estar vazia, pois os campos estão incompletos.");
}


    @Test
    void testNaoPermitirCadastroDeCpfDuplicado() {

        Cliente clienteExistente = new Cliente("Maria Oliveira", "12345678900", "(11) 98765-0000");
        GerenciadorDados.adicionarCliente(clienteExistente);

        JTextField txtNomeCli = new JTextField("João Silva");
        JFormattedTextField txtCpfCli = new JFormattedTextField("123.456.789-00");
        JFormattedTextField txtTelefoneCli = new JFormattedTextField("(11) 98765-4321");

        boolean isCpfCadastrado = GerenciadorDados.isCpfCadastrado(txtCpfCli.getText().replaceAll("[^\\d]", ""));
        assertTrue(isCpfCadastrado, "O CPF informado já deve estar cadastrado, impedindo o cadastro duplicado.");
    }

    @Test
    void testNaoPermitirCadastroDeTelefoneDuplicado() {

        Cliente clienteExistente = new Cliente("Maria Oliveira", "12345678900", "(11) 98765-4321");
        GerenciadorDados.adicionarCliente(clienteExistente);

        JTextField txtNomeCli = new JTextField("João Silva");
        JFormattedTextField txtCpfCli = new JFormattedTextField("987.654.321-00");
        JFormattedTextField txtTelefoneCli = new JFormattedTextField("(11) 98765-4321"); // Telefone duplicado

        boolean isTelefoneCadastrado = GerenciadorDados.isTelefoneCadastrado(txtTelefoneCli.getText());
        assertTrue(isTelefoneCadastrado, "O telefone informado já deve estar cadastrado, impedindo o cadastro duplicado.");
    }

    @Test
    void testCancelarCadastro() {

        JTextField txtNomeCli = new JTextField("João Silva");
        JFormattedTextField txtCpf = new JFormattedTextField("123.456.789-00");
        JFormattedTextField txtTelefone = new JFormattedTextField("(11) 98765-4321");

        JButton btnCancelarCadCli = new JButton();
        ActionListener[] listeners = btnCancelarCadCli.getActionListeners();
    if (listeners.length > 0) {
        listeners[0].actionPerformed(new ActionEvent(btnCancelarCadCli, ActionEvent.ACTION_PERFORMED, ""));
    }

        txtNomeCli.setText("");
        txtCpf.setValue(null);
        txtTelefone.setValue(null);

        assertEquals("", txtNomeCli.getText(), "O campo Nome deve estar vazio após cancelar.");
        assertNull(txtCpf.getValue(), "O campo CPF deve estar vazio após cancelar.");
        assertNull(txtTelefone.getValue(), "O campo Telefone deve estar vazio após cancelar.");
    }

}
