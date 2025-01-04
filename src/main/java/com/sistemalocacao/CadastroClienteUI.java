/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.sistemalocacao;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author leticia
 */
public class CadastroClienteUI extends javax.swing.JPanel {

    /**
     * Creates new form CadastroCliente
     */
    public CadastroClienteUI() {
        initComponents();
        aplicarMascaras();
        
    }
    
    public JPanel getPanel() {
        return panelCadCli;
    }
    
    private void aplicarMascaras() {
        try {
            // Máscara para CPF
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            txtCpfCli.setFormatterFactory(new DefaultFormatterFactory(cpfMask));

            // Máscara para Telefone
            MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");
            telefoneMask.setPlaceholderCharacter('_');
            txtTelefoneCli.setFormatterFactory(new DefaultFormatterFactory(telefoneMask));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Erro ao aplicar máscara nos campos!",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCadCli = new javax.swing.JPanel();
        lblNomeCli = new javax.swing.JLabel();
        txtNomeCli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCpfCli = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTelefoneCli = new javax.swing.JFormattedTextField();
        btnCadCli = new javax.swing.JButton();
        btnCancelarCadCli = new javax.swing.JButton();

        lblNomeCli.setText("Nome Completo:");

        jLabel1.setText("CPF:");

        jLabel2.setText("Telefone:");

        btnCadCli.setText("Cadastrar Cliente");
        btnCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadCliActionPerformed(evt);
            }
        });

        btnCancelarCadCli.setText("Cancelar Cadastro");
        btnCancelarCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCadCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCadCliLayout = new javax.swing.GroupLayout(panelCadCli);
        panelCadCli.setLayout(panelCadCliLayout);
        panelCadCliLayout.setHorizontalGroup(
            panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadCliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNomeCli)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadCliLayout.createSequentialGroup()
                        .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelCadCliLayout.createSequentialGroup()
                        .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNomeCli, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCadCliLayout.createSequentialGroup()
                                .addComponent(btnCadCli)
                                .addGap(103, 103, 103)
                                .addComponent(btnCancelarCadCli)))
                        .addGap(155, 155, 155))))
        );
        panelCadCliLayout.setVerticalGroup(
            panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadCliLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCli)
                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTelefoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(panelCadCliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadCli)
                    .addComponent(btnCancelarCadCli))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCadCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 601, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCadCli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCadCliActionPerformed
        // Cancelar cadastro e limpar campos
        JOptionPane.showMessageDialog(this,
            "Cadastro cancelado.",
            "Cancelado", JOptionPane.INFORMATION_MESSAGE);

        txtNomeCli.setText("");
        txtCpfCli.setValue(null);
        txtTelefoneCli.setValue(null);
    }//GEN-LAST:event_btnCancelarCadCliActionPerformed

    private void btnCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadCliActionPerformed
        String nome = txtNomeCli.getText();
        String telefone = txtTelefoneCli.getText();
        String cpf = txtCpfCli.getText().replaceAll("[^\\d]", ""); // Remove a formatação

        // Verifica campos obrigatórios
        if (nome == null || nome.trim().isEmpty() ||
            cpf == null || cpf.trim().isEmpty() ||
            telefone == null || telefone.trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                "Todos os campos são obrigatórios. Por favor, preencha-os!",
                "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verifica duplicidade de CPF e telefone
        if (GerenciadorDados.isCpfCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this,
                "Erro: O CPF informado já está cadastrado!",
                "CPF Duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (GerenciadorDados.isTelefoneCadastrado(telefone)) {
            JOptionPane.showMessageDialog(this,
                "Erro: O número de telefone informado já está cadastrado!",
                "Telefone Duplicado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar um objeto Cliente
        Cliente cliente = new Cliente(nome, cpf, telefone);

        // Adicionar o cliente à lista
        GerenciadorDados.adicionarCliente(cliente);

        // Mostrar mensagem de confirmação
        JOptionPane.showMessageDialog(this,
            "Cliente cadastrado com sucesso:" + "\n" +
            "Nome: " + nome + "\n" +
            "CPF: " + cpf + "\n" +
            "Telefone: " + telefone,
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Limpar os campos de texto
        txtNomeCli.setText("");
        txtCpfCli.setValue(null);
        txtTelefoneCli.setValue(null);
    
    }//GEN-LAST:event_btnCadCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadCli;
    private javax.swing.JButton btnCancelarCadCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblNomeCli;
    private javax.swing.JPanel panelCadCli;
    private javax.swing.JFormattedTextField txtCpfCli;
    private javax.swing.JTextField txtNomeCli;
    private javax.swing.JFormattedTextField txtTelefoneCli;
    // End of variables declaration//GEN-END:variables
}
