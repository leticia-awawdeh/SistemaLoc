/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package SistemaLocacao.UI;

/**
 *
 * @author leticia
 */


//TODO: Corrigir erros
import SistemaLocacao.Classes.Status;
import SistemaLocacao.Classes.Locacao;
import SistemaLocacao.Classes.GerenciadorDados;
import SistemaLocacao.Classes.Equipamento;
import SistemaLocacao.Classes.Cliente;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;



public class LocacaoUI extends javax.swing.JPanel {
    
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    double valorLoc;
    public double calcularValorLocacao(double valorDiario, long dias) {
        valorLoc = valorDiario * dias;
        return valorLoc;
    }

    private static String formatarValorMonetario(double valor) {
        return String.format("R$ %.2f", valor);
    }

    public static long calcularDiasEntreDatas(LocalDate inicio, LocalDate termino) {
        return java.time.temporal.ChronoUnit.DAYS.between(inicio, termino);
    }

    private void atualizarDropdownEquipamentos() {
    if (dropDwnEquip != null) {
        dropDwnEquip.removeAllItems();
        dropDwnEquip.addItem("Selecione o equipamento");

        List<Equipamento> equipamentosDisponiveis = GerenciadorDados.getListaEquipamentos();

        for (Equipamento equipamento : equipamentosDisponiveis) {
            if (equipamento.getStatus() == Status.DISPONIVEL) {
                dropDwnEquip.addItem(equipamento.getNome());
            }
        }

        if (dropDwnEquip.getItemCount() == 1) { // Apenas o placeholder
            dropDwnEquip.addItem("Nenhum equipamento disponível");
        }

        dropDwnEquip.setSelectedIndex(0);
    }
}

    public Cliente buscarClientePorCpf(String cpf) {
        String cpfSemFormatacao = cpf.replaceAll("[^\\d]", "");

        List<Cliente> clientes = GerenciadorDados.getListaClientes();
        return clientes.stream()
                .filter(cli -> cli.getCpfCli().replaceAll("[^\\d]", "").equals(cpfSemFormatacao)) // Compara sem formatação
                .findFirst()
                .orElse(null);
    }

    private void carregarEquipamentos() {
        dropDwnEquip.removeAllItems();

        dropDwnEquip.addItem("Selecione o equipamento");

        List<Equipamento> equipamentos = GerenciadorDados.getListaEquipamentos();

        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getStatus() == Status.DISPONIVEL) {
                dropDwnEquip.addItem(equipamento.getNome());
            }
        }

        if (dropDwnEquip.getItemCount() == 1) { // Apenas o placeholder
            dropDwnEquip.addItem("Nenhum equipamento disponível");
        }

        dropDwnEquip.setSelectedIndex(0);
    }

    private void limparCampos() {

        txtDataInicio.setValue(null);
        txtDataInicio.setText("");
        txtDataTermino.setValue(null);
        txtDataTermino.setText("");
        txtCpfCli.setValue(null);
        txtCpfCli.setText("");
        txtNomeCli.setText("");
        txtTelefoneCli.setText("");

        dropDwnEquip.setSelectedIndex(0);
    }

    public JPanel getPanel() {
        return panelRegistroLocacao;
    }
    public LocacaoUI() {
        initComponents();
        carregarEquipamentos();

        try {
            // Aplicando máscara para CPF
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            txtCpfCli.setFormatterFactory(new DefaultFormatterFactory(cpfMask));

            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');

            txtDataInicio.setFormatterFactory(new DefaultFormatterFactory(dateMask));
            txtDataTermino.setFormatterFactory(new DefaultFormatterFactory(dateMask));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        GerenciadorDados.addAtualizacaoListener(() -> carregarEquipamentos());
        
        

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        panelRegistroLocacao = new javax.swing.JPanel();
        dropDwnEquip = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCpfCli = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNomeCli = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefoneCli = new javax.swing.JFormattedTextField();
        btnRegistrarLocacao = new javax.swing.JButton();
        btnCancelLocacao = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDataInicio = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDataTermino = new javax.swing.JFormattedTextField();

        jLabel8.setText("jLabel8");

        panelRegistroLocacao.setPreferredSize(new java.awt.Dimension(616, 616));

        dropDwnEquip.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropDwnEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropDwnEquipActionPerformed(evt);
            }
        });

        jLabel1.setText("Equipamento:");

        jLabel2.setText("Dados do Cliente");

        jLabel3.setText("CPF:");

        txtCpfCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpfCliKeyTyped(evt);
            }
        });

        jLabel4.setText("Nome Completo:");

        jLabel5.setText("Telefone:");

        btnRegistrarLocacao.setText("Registrar Locação");
        btnRegistrarLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarLocacaoActionPerformed(evt);
            }
        });

        btnCancelLocacao.setText("Cancelar Locação");

        jLabel6.setText("Datas de Locação");

        jLabel7.setText("Início:");

        jLabel9.setText("Término:");

        javax.swing.GroupLayout panelRegistroLocacaoLayout = new javax.swing.GroupLayout(panelRegistroLocacao);
        panelRegistroLocacao.setLayout(panelRegistroLocacaoLayout);
        panelRegistroLocacaoLayout.setHorizontalGroup(
            panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCpfCli, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                .addComponent(txtNomeCli)
                                .addComponent(txtTelefoneCli)
                                .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(jLabel2)))
                            .addComponent(dropDwnEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnRegistrarLocacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDataTermino, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addComponent(btnCancelLocacao))))
                    .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jLabel6)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        panelRegistroLocacaoLayout.setVerticalGroup(
            panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLocacaoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropDwnEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(43, 43, 43)
                .addGroup(panelRegistroLocacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarLocacao)
                    .addComponent(btnCancelLocacao))
                .addContainerGap(206, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegistroLocacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRegistroLocacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dropDwnEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropDwnEquipActionPerformed
         String equipamentoSelecionado = (String) dropDwnEquip.getSelectedItem();
                if (!"Selecione o equipamento".equals(equipamentoSelecionado)) {
                    System.out.println("Equipamento selecionado: " + equipamentoSelecionado);
                }
            
    }//GEN-LAST:event_dropDwnEquipActionPerformed

    private void txtCpfCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpfCliKeyTyped
                String cpfDigitado = txtCpfCli.getText();
                Cliente cliente = buscarClientePorCpf(cpfDigitado);

                if (cliente != null) {
                    txtNomeCli.setText(cliente.getNomeCli());
                    txtTelefoneCli.setText(cliente.getTelefoneCli());
                } else {
                    txtNomeCli.setText("");
                    txtTelefoneCli.setText("");
                }
            
    }//GEN-LAST:event_txtCpfCliKeyTyped

    private void btnRegistrarLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarLocacaoActionPerformed

                String dataInicioStr = txtDataInicio.getText();
                String dataTerminoStr = txtDataTermino.getText();

                LocalDate dataInicio;
                LocalDate dataTermino;
                try {
                    dataInicio = LocalDate.parse(dataInicioStr, dateFormatter);
                    dataTermino = LocalDate.parse(dataTerminoStr, dateFormatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(panelRegistroLocacao,
                            "Por favor, insira as datas no formato DD/MM/AAAA.",
                            "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long diasLocacao = calcularDiasEntreDatas(dataInicio, dataTermino);
                if (diasLocacao <= 0) {
                    JOptionPane.showMessageDialog(panelRegistroLocacao,
                            "Data de término deve ser posterior à data de início.",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String cpfCliente = txtCpfCli.getText();
                Cliente cliente = buscarClientePorCpf(cpfCliente);
                if (cliente == null) {
                    JOptionPane.showMessageDialog(panelRegistroLocacao,
                            "Cliente não encontrado. Verifique o CPF e tente novamente.",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String nomeEquip = (String) dropDwnEquip.getSelectedItem();
                Equipamento equipamentoSelecionado = GerenciadorDados.getListaEquipamentos()
                        .stream()
                        .filter(eq -> eq.getNome().equals(nomeEquip) && eq.getStatus() == Status.DISPONIVEL)
                        .findFirst()
                        .orElse(null);

                if (equipamentoSelecionado == null) {
                    JOptionPane.showMessageDialog(panelRegistroLocacao,
                            "Equipamento inválido ou indisponível.",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Locacao novaLocacao = new Locacao(cliente,
                        equipamentoSelecionado,
                        dataInicio,
                        dataTermino,
                        0.1 
                );

                equipamentoSelecionado.incrementarFrequenciaAluguel();
                equipamentoSelecionado.setCliente(cliente);
                equipamentoSelecionado.setStatus(Status.ALUGADO);

                GerenciadorDados.adicionarLocacao(novaLocacao);

                GerenciadorDados.notificarAtualizacao();

                // Exibir resumo para o usuário
                JOptionPane.showMessageDialog(panelRegistroLocacao,
                        String.format(
                                "Locação registrada com sucesso.\n\n" +
                                        "Equipamento: %s\n" +
                                        "Data Início: %s | Data Fim: %s\n" +
                                        "Dias Locação: %d dias\n" +
                                        "Valor Diário: R$ %.2f\n" +
                                        "Valor Total: R$ %.2f",
                                equipamentoSelecionado.getNome(),
                                dataInicio.format(dateFormatter),
                                dataTermino.format(dateFormatter),
                                diasLocacao,
                                novaLocacao.getValorDiario(),
                                calcularValorLocacao(novaLocacao.getValorDiario(), diasLocacao)
                        ), "Sucesso", JOptionPane.INFORMATION_MESSAGE
                );
                limparCampos();
            

        
    }//GEN-LAST:event_btnRegistrarLocacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelLocacao;
    private javax.swing.JButton btnRegistrarLocacao;
    private javax.swing.JComboBox<String> dropDwnEquip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panelRegistroLocacao;
    private javax.swing.JFormattedTextField txtCpfCli;
    private javax.swing.JFormattedTextField txtDataInicio;
    private javax.swing.JFormattedTextField txtDataTermino;
    private javax.swing.JTextField txtNomeCli;
    private javax.swing.JFormattedTextField txtTelefoneCli;
    // End of variables declaration//GEN-END:variables
}

