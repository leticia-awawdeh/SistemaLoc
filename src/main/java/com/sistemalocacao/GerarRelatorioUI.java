/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.sistemalocacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leticia
 */
public class GerarRelatorioUI extends javax.swing.JPanel {

    private void gerarRelatorioClientes() {
        try {
            String documentosPath = System.getProperty("user.home") + "\\Documents";
            String relatoriosPath = documentosPath + "\\Relatórios";

            // Criação dos diretórios, caso não existam
            File pastaRelatorios = new File(relatoriosPath);
            if (!pastaRelatorios.exists() && !pastaRelatorios.mkdir()) {
                JOptionPane.showMessageDialog(null, "Erro ao criar a pasta Relatórios.");
                return;
            }

            // Define o caminho do arquivo do relatório de clientes
            String mesAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
            String destPath = relatoriosPath + "\\relatorio_clientes_" + mesAtual + ".pdf";
            File arquivoRelatorio = new File(destPath);
            while (arquivoRelatorio.exists()) {
                String novoNome = JOptionPane.showInputDialog(
                        null,
                        "O arquivo " + arquivoRelatorio.getName() + " já existe.\n"
                                + "Por favor, insira um novo nome ou clique em 'Cancelar' para interromper:",
                        "Arquivo já existe",
                        JOptionPane.WARNING_MESSAGE
                );

                if (novoNome == null || novoNome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
                    return;
                }
                destPath = relatoriosPath + "\\" + novoNome + ".pdf";
                arquivoRelatorio = new File(destPath);
            }

            PdfWriter writer = new PdfWriter(destPath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Relatório de Clientes - Ranking de Multas Acumuladas").setFont(com.itextpdf.kernel.font.PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD)));

            List<Cliente> clientesOrdenados = GerenciadorDados.getListaClientes()
                    .stream()
                    .sorted((c1, c2) -> Double.compare(c2.getMultasTotais(), c1.getMultasTotais())) // Ordena decrescente
                    .toList();

            document.add(new Paragraph(String.format("%-5s | %-25s | %-15s | %-15s",
                    "Pos.", "Nome", "CPF", "Multas (R$)")));

            int posicao = 1;
            for (Cliente cliente : clientesOrdenados) {
                String clienteInfo = String.format(
                        "%-5d | %-25s | %-15s | R$ %-12s",
                        posicao,
                        cliente.getNomeCli(),
                        cliente.getCpfCli(),
                        Utils.formatarMonetario(cliente.getMultasTotais()) // Formata o valor
                );
                document.add(new Paragraph(clienteInfo));
                posicao++;
            }

            document.close();
            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso em: " + destPath);
        } catch (HeadlessException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
        }
    }
    
    private void gerarRelatorioEquipamentos() {
        try {
            String documentosPath = System.getProperty("user.home") + "\\Documents";

            String relatoriosPath = documentosPath + "\\Relatórios";

            File pastaRelatorios = new File(relatoriosPath);
            if (!pastaRelatorios.exists()) {
                boolean criada = pastaRelatorios.mkdir();
                if (!criada) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar a pasta Relatórios.");
                    return;
                }
            }

            String mesAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
            String destPath = relatoriosPath + "\\relatorio_equipamentos_" + mesAtual + ".pdf";

            File arquivoRelatorio = new File(destPath);

            while (arquivoRelatorio.exists()) {
                String novoNome = JOptionPane.showInputDialog(
                        null,
                        "O arquivo " + arquivoRelatorio.getName() + " já existe.\n"
                                + "Por favor, insira um novo nome ou clique em 'Cancelar' para interromper:",
                        "Arquivo já existe",
                        JOptionPane.WARNING_MESSAGE
                );

                if (novoNome == null || novoNome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
                    return;
                }

                destPath = relatoriosPath + "\\" + novoNome + ".pdf";
                arquivoRelatorio = new File(destPath);
            }

            PdfWriter writer = null;
            try {
                writer = new PdfWriter(destPath);
            } catch (IOException ex) {
                Logger.getLogger(GerarRelatorioUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            try (Document document = new Document(pdfDoc)) {
                document.add(new Paragraph("Relatório de Equipamentos Mais Alugados"));
                
                List<Equipamento> equipamentosOrdenados = GerenciadorDados.getListaEquipamentos()
                        .stream()
                        .sorted((e1, e2) -> Integer.compare(e2.getFrequenciaAluguel(), e1.getFrequenciaAluguel())) // Ordem decrescente
                        .toList();
                
                double receitaTotal = 0.0;
                
                document.add(new Paragraph(String.format("%-10s | %-25s | %-15s | %-15s | %s",
                        "Código", "Nome", "Total Aluguéis", "Receita Total", "Status")));
                
                for (Equipamento equipamento : equipamentosOrdenados) {
                    String equipamentoInfo = String.format(
                            "%-10s | %-25s | %-15d | R$ %-12.2f | %s",
                            equipamento.getCodigo(),
                            equipamento.getNome(),
                            equipamento.getFrequenciaAluguel(),
                            equipamento.calcularReceitaTotal(),
                            equipamento.getStatus().name()
                    );
                    document.add(new Paragraph(equipamentoInfo));
                    
                    receitaTotal += equipamento.calcularReceitaTotal();
                }
                
                document.add(new Paragraph("\n"));
                document.add(new Paragraph(String.format("Receita Total Acumulada: R$ %.2f", receitaTotal)));
            }

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso em: " + destPath);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
        }
    }

    public JPanel getPanel() {
        return panelRelatorio;
    }
    public GerarRelatorioUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRelatorio = new javax.swing.JPanel();
        btnRelatorioCli = new javax.swing.JButton();
        btnRelatorioEquip = new javax.swing.JButton();

        btnRelatorioCli.setText("Gerar Relatório de Clientes");
        btnRelatorioCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioCliActionPerformed(evt);
            }
        });

        btnRelatorioEquip.setText("Gerar Relatório de Equipamentos");
        btnRelatorioEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRelatorioEquipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRelatorioLayout = new javax.swing.GroupLayout(panelRelatorio);
        panelRelatorio.setLayout(panelRelatorioLayout);
        panelRelatorioLayout.setHorizontalGroup(
            panelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRelatorioLayout.createSequentialGroup()
                .addGroup(panelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRelatorioLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btnRelatorioCli))
                    .addGroup(panelRelatorioLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnRelatorioEquip)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panelRelatorioLayout.setVerticalGroup(
            panelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRelatorioLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnRelatorioCli)
                .addGap(72, 72, 72)
                .addComponent(btnRelatorioEquip)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRelatorioCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioCliActionPerformed
        gerarRelatorioClientes();
    }//GEN-LAST:event_btnRelatorioCliActionPerformed

    private void btnRelatorioEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRelatorioEquipActionPerformed
       gerarRelatorioEquipamentos();
    }//GEN-LAST:event_btnRelatorioEquipActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRelatorioCli;
    private javax.swing.JButton btnRelatorioEquip;
    private javax.swing.JPanel panelRelatorio;
    // End of variables declaration//GEN-END:variables
}
