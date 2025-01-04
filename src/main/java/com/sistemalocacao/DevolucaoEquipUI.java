/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.sistemalocacao;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * @author leticia
 */
public class DevolucaoEquipUI extends javax.swing.JPanel {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public DevolucaoEquipUI() {
        initComponents();
        
        try {
            // Aplicando máscara para CPF
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            txtCpfCli.setFormatterFactory(new DefaultFormatterFactory(cpfMask));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
     private void buscarLocacao(String cpf) {
        String cpfDigitado = txtCpfCli.getText().replaceAll("[^\\d]", ""); // Remove pontuações

        // Busca cliente pelo CPF
        Optional<Cliente> clienteOpt = GerenciadorDados.getListaClientes()
                .stream()
                .filter(cli -> cli.getCpfCli().replaceAll("[^\\d]", "").equals(cpfDigitado))
                .findFirst();

        if (clienteOpt.isEmpty()) {
            JOptionPane.showMessageDialog(panelDevolucao,
                    "Cliente não encontrado. Confira o CPF e tente novamente.",
                    "Erro", JOptionPane.WARNING_MESSAGE);
            txtResult.setText("");
            return;
        }

        Cliente cliente = clienteOpt.get();

        // Buscar equipamento alugado do cliente
        Optional<Locacao> locacaoOpt = GerenciadorDados.getListaLocacoes()
                .stream()
                .filter(locacao -> locacao.getCliente().getCpfCli().equals(cliente.getCpfCli()))
                .findFirst();

        if (locacaoOpt.isEmpty()) {
            JOptionPane.showMessageDialog(panelDevolucao,
                    "Nenhuma locação ativa encontrada para esse cliente.",
                    "Erro", JOptionPane.WARNING_MESSAGE);
            txtResult.setText("");
            return;
        }

        Locacao locacao = locacaoOpt.get();
        Equipamento equipamento = locacao.getEquipamento();

        txtResult.setText(String.format(
                "Equipamento: %s\n" +
                "Cliente: %s\n" +
                "Data de Empréstimo: %s\n" +
                "Data Prevista de Devolução: %s\n" +
                "Valor da Locação: R$ %s",
            equipamento.getNome(),
            cliente.getNomeCli(),
            locacao.getDataInicio().format(dateFormatter),
            locacao.getDataPrevistaDevolucao().format(dateFormatter),
            Utils.formatarMonetario(equipamento.getValorDiario())
        ));

        // Armazenar o equipamento no painel
        txtResult.putClientProperty("locacaoSelecionada", locacao);
    }

    private void confirmarDevolucao() {
        Object locacaoObj = txtResult.getClientProperty("locacaoSelecionada");

        if (locacaoObj == null || !(locacaoObj instanceof Locacao locacao)) {
            JOptionPane.showMessageDialog(panelDevolucao,
                    "Nenhuma locação selecionada ou encontrada. Tente buscar novamente.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Equipamento equipamento = locacao.getEquipamento();

        equipamento.setStatus(Status.DISPONIVEL);
        equipamento.setCliente(null);

        LocalDate dataDevolucao = LocalDate.now();
        locacao.setDataDevolucao(dataDevolucao);
        long diasAtraso = locacao.getDiasAtraso();
        double multa = locacao.calcularMulta();
        double valorAluguel = locacao.getValorDiario() * locacao.getQuantidadeDiasLocacao();

        double total = valorAluguel + multa;

        if (diasAtraso > 0) {
            Cliente cliente = locacao.getCliente();
            if (cliente != null) {
                String descricaoMulta = String.format(
                        "Atraso de %d dia(s) na devolução do equipamento %s.",
                        diasAtraso,
                        equipamento.getNome()
                );
                cliente.adicionarMulta(descricaoMulta, multa);
            }
        }

        txtResult.setText(String.format(
        "Resumo da Devolução:\n\n" +
        "Equipamento: %s\n" +
        "Data Prevista de Término: %s\n" +
        "Data de Devolução: %s\n" +
        "Multa por atraso: R$ %s\n" +
        "Valor do Aluguel: R$ %s\n" +
        "Total a Pagar: R$ %s",
        equipamento.getNome(),
        locacao.getDataPrevistaDevolucao().format(dateFormatter),
        dataDevolucao.format(dateFormatter),
        Utils.formatarMonetario(multa),
        Utils.formatarMonetario(valorAluguel),
        Utils.formatarMonetario(total)
    ));

txtResult.putClientProperty("locacaoSelecionada", null);
    }

    private void confirmarPagamento() {
        // Exibe um pop-up com mensagem de confirmação
        JOptionPane.showMessageDialog(panelDevolucao,
                "Pagamento confirmado e devoluções realizadas!",
                "Confirmação", JOptionPane.INFORMATION_MESSAGE);

        txtCpfCli.setValue(null);
        txtCpfCli.setText("");
        txtResult.setText("");
    }

    public JPanel getPanel() {
        return panelDevolucao;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDevolucao = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscarLoc = new javax.swing.JButton();
        txtCpfCli = new javax.swing.JFormattedTextField();
        btnConfDevolucao = new javax.swing.JButton();
        btnConfPag = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResult = new javax.swing.JTextArea();

        panelDevolucao.setPreferredSize(new java.awt.Dimension(616, 616));

        jLabel1.setText("CPF:");

        btnBuscarLoc.setText("Buscar Locação");
        btnBuscarLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLocActionPerformed(evt);
            }
        });

        btnConfDevolucao.setText("Confirmar Devolução");
        btnConfDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfDevolucaoActionPerformed(evt);
            }
        });

        btnConfPag.setText("Confirmar Pagamento");
        btnConfPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfPagActionPerformed(evt);
            }
        });

        txtResult.setColumns(20);
        txtResult.setRows(5);
        jScrollPane1.setViewportView(txtResult);

        javax.swing.GroupLayout panelDevolucaoLayout = new javax.swing.GroupLayout(panelDevolucao);
        panelDevolucao.setLayout(panelDevolucaoLayout);
        panelDevolucaoLayout.setHorizontalGroup(
            panelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDevolucaoLayout.createSequentialGroup()
                .addGroup(panelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDevolucaoLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(panelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCpfCli))
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDevolucaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(panelDevolucaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnConfDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(btnConfPag, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDevolucaoLayout.setVerticalGroup(
            panelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDevolucaoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscarLoc)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelDevolucaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfPag, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLocActionPerformed
        buscarLocacao(txtCpfCli.getText());
    }//GEN-LAST:event_btnBuscarLocActionPerformed

    private void btnConfDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfDevolucaoActionPerformed
        confirmarDevolucao();
    }//GEN-LAST:event_btnConfDevolucaoActionPerformed

    private void btnConfPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfPagActionPerformed
        confirmarPagamento();
    }//GEN-LAST:event_btnConfPagActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarLoc;
    private javax.swing.JButton btnConfDevolucao;
    private javax.swing.JButton btnConfPag;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDevolucao;
    private javax.swing.JFormattedTextField txtCpfCli;
    private javax.swing.JTextArea txtResult;
    // End of variables declaration//GEN-END:variables
}
