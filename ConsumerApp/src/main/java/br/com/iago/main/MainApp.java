package br.com.iago.main;

import javax.swing.JOptionPane;

import br.com.iago.controller.ReadQueueController;

public class MainApp {

	public static void main(String[] args) {

		ReadQueueController rqc = new ReadQueueController();
		if(rqc.readQueueToFile()) {
			JOptionPane.showMessageDialog(null, "Mensagens lidas da fila", "Mensagem lida", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Problemas ao ler fila", "Problemas", JOptionPane.ERROR_MESSAGE);
		}

	}
}
