/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import com.sistemalocacao.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author leticia
 */
public class EquipamentoTest {
    
    @Test
    public void testGerarCodigoUnico() {
        Equipamento equipamento1 = new Equipamento("Câmera", "Câmera profissional", 50.0);
        Equipamento equipamento2 = new Equipamento("Tripé", "Tripé ajustável", 30.0);

        assertTrue(equipamento1.getCodigo() != equipamento2.getCodigo());
    }

    @Test
    public void testCalcularReceitaTotal() {
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);
        equipamento.incrementarFrequenciaAluguel();
        equipamento.incrementarFrequenciaAluguel();

        assertEquals(100.0, equipamento.calcularReceitaTotal(), 0.01);
    }

    @Test
    public void testInicializarStatus() {
        Equipamento equipamento = new Equipamento("Câmera", "Câmera profissional", 50.0);

        assertEquals(Status.DISPONIVEL, equipamento.getStatus());
    }
}
