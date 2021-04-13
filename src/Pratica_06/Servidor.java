package Pratica_06;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {

	public static void main(String[] args) throws IOException {

		// Criar datagrama na porta usando a porta 4545
		DatagramSocket s = new DatagramSocket(4545);
		System.out.println("Servidor aguardando conexão...");

		// Declara conteúdo recebido
		DatagramPacket recebe = new DatagramPacket(new byte[512], 512);

		// servidor permanece executando indefinidamente, atualizando o que foi recebido
		while (true) {
			s.receive(recebe);
			System.out.print("Mensagem recebida: ");
			
			// percorre o recebe que é um array(conjunto de byte) e impreme na forma de char
			
			for (int i = 0; i < recebe.getLength(); i++) {
				System.out.print((char) recebe.getData()[i]);
			}
			System.out.println();
			
			//Datagrama já possui as informações que o cliente enviou, por isso os get no recebe
			DatagramPacket resp = new DatagramPacket(recebe.getData(), recebe.getLength(), recebe.getAddress(), recebe.getPort());
			s.send(resp);
		}

	}

}
