/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sistemalocacao;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leticia
 */
public class SisLoc extends javax.swing.JFrame {
private final List<Cliente> clientes;


    public SisLoc() {
        clientes = new ArrayList<>();
        initComponents();
        

        ClienteUI cadastroCliente = new ClienteUI();
        panelSis.addTab("Cadastro de Cliente", cadastroCliente.getPanel());
        
        EquipamentoUI cadastroEquip = new EquipamentoUI();
        panelSis.addTab("Cadastro de Equipamentos", cadastroEquip.getPanel());
        
        LocacaoUI locacaoEquip = new LocacaoUI();
        panelSis.addTab("Locação de Equipamentos", locacaoEquip.getPanel());
        
        DevolucaoEquipUI devolucaoEquip = new DevolucaoEquipUI();
        panelSis.addTab("Devolução de Equipamentos", devolucaoEquip.getPanel());
        
        GerarRelatorioUI gerarRelatorio = new GerarRelatorioUI();
        panelSis.addTab("Gerar Relatório", gerarRelatorio.getPanel());
        
        setContentPane(panelSis);
        setTitle("Sistema de Locação de Equipamentos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(616, 616);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSis = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelSis.setPreferredSize(new java.awt.Dimension(616, 616));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSis, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelSis, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            try {
                SisLoc frame = new SisLoc();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace(); // Registrar exceções adequadamente em produção
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane panelSis;
    // End of variables declaration//GEN-END:variables
}
