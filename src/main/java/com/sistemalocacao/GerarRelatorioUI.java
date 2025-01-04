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
import java.io.IOException;

/**
 *
 * @author leticia
 */
public class GerarRelatorioUI extends javax.swing.JPanel {

    public void gerarRelatorioClientes() {
    try {
        // Obtendo o caminho da pasta "Documents" do usuário
        String documentosPath = System.getProperty("user.home");
        
        // Usando o File.separator para garantir a compatibilidade com o sistema operacional
        String separator = File.separator;
        String relatoriosPath = documentosPath + separator + "Documents" + separator + "Relatórios";

        // Criação do diretório "Relatórios", caso não exista
        File pastaRelatorios = new File(relatoriosPath);
        if (!pastaRelatorios.exists()) {
            // Usando mkdirs() para garantir que todos os diretórios sejam criados
            boolean criada = pastaRelatorios.mkdirs();
            if (!criada) {
                JOptionPane.showMessageDialog(null, "Erro ao criar a pasta Relatórios.");
                return;
            }
        }

        // Define o caminho do arquivo do relatório de clientes
        String mesAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
        String destPath = relatoriosPath + separator + "relatorio_clientes_" + mesAtual + ".pdf";
        File arquivoRelatorio = new File(destPath);

        // Verifica se o arquivo já existe e pede um novo nome caso necessário
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
            destPath = relatoriosPath + separator + novoNome + ".pdf";
            arquivoRelatorio = new File(destPath);
        }

        // Criação do arquivo PDF
        PdfWriter writer = new PdfWriter(destPath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Adiciona o título do relatório
        document.add(new Paragraph("Relatório de Clientes - Ranking de Multas Acumuladas").setFont(com.itextpdf.kernel.font.PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD)));

        // Ordenação e adição dos dados dos clientes ao relatório
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

        // Fecha o documento PDF
        document.close();
        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso em: " + destPath);
    } catch (HeadlessException | IOException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
    }
}

    // Método para gerar relatório de equipamentos
    private void gerarRelatorioEquipamentos() {
    try {
        // Obtendo o caminho da pasta "Documents" do usuário
        String documentosPath = System.getProperty("user.home");
        
        // Usando o File.separator para garantir a compatibilidade com o sistema operacional
        String separator = File.separator;
        String relatoriosPath = documentosPath + separator + "Documents" + separator + "Relatórios";

        // Criação do diretório "Relatórios", caso não exista
        File pastaRelatorios = new File(relatoriosPath);
        if (!pastaRelatorios.exists()) {
            // Usando mkdirs() para garantir que todos os diretórios sejam criados
            boolean criada = pastaRelatorios.mkdirs();
            if (!criada) {
                JOptionPane.showMessageDialog(null, "Erro ao criar a pasta Relatórios.");
                return;
            }
        }

        // Define o caminho do arquivo do relatório de equipamentos
        String mesAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM"));
        String destPath = relatoriosPath + separator + "relatorio_equipamentos_" + mesAtual + ".pdf";
        File arquivoRelatorio = new File(destPath);

        // Verifica se o arquivo já existe e pede um novo nome caso necessário
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
            destPath = relatoriosPath + separator + novoNome + ".pdf";
            arquivoRelatorio = new File(destPath);
        }

        // Criação do arquivo PDF
        PdfWriter writer = new PdfWriter(destPath);
        com.itextpdf.kernel.pdf.PdfDocument pdfDoc = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        try (Document document = new Document(pdfDoc)) {
            // Adiciona o título do relatório
            document.add(new Paragraph("Relatório de Equipamentos Mais Alugados"));
            
            // Ordenação e adição dos dados dos equipamentos ao relatório
            List<Equipamento> equipamentosOrdenados = GerenciadorDados.getListaEquipamentos()
                    .stream()
                    .sorted((e1, e2) -> Integer.compare(e2.getFrequenciaAluguel(), e1.getFrequenciaAluguel())) // Ordem decrescente
                    .toList();
            
            double receitaTotal = 0.0;
            
            // Adiciona os cabeçalhos da tabela
            document.add(new Paragraph(String.format("%-10s | %-25s | %-15s | %-15s | %s",
                    "Código", "Nome", "Total Aluguéis", "Receita Total", "Status")));
            
            // Preenche os dados dos equipamentos
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
            
            // Adiciona a receita total acumulada
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(String.format("Receita Total Acumulada: R$ %.2f", receitaTotal)));
        }

        // Exibe uma mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso em: " + destPath);
    } catch (HeadlessException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório!");
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao criar o arquivo PDF!");
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

        setPreferredSize(new java.awt.Dimension(616, 616));

        panelRelatorio.setPreferredSize(new java.awt.Dimension(616, 616));

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
                .addGap(104, 104, 104)
                .addGroup(panelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRelatorioEquip, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addComponent(btnRelatorioCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        panelRelatorioLayout.setVerticalGroup(
            panelRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRelatorioLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(btnRelatorioCli, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnRelatorioEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
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
