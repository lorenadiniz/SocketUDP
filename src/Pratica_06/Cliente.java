package Pratica_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

	public static void main(String[] args) throws IOException {

		// Criar um datagrama e informar o endereco servidor

		DatagramSocket s = new DatagramSocket();
		InetAddress dest = InetAddress.getByName("localhost");

		// Habilitar que o usuário entre com os dados
		String env;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("> ");
		env = teclado.readLine();

		// enquanto env for diferente de "" vai permanecer enviando o datagrama ao serv
		while (env.equalsIgnoreCase("")) {

			byte[] buffer = env.getBytes();
			DatagramPacket msg = new DatagramPacket(buffer, buffer.length, dest, 4545);
			s.send(msg);

			// Declara p/ receber o buffer, basta informar o tamanho do datagrama
			DatagramPacket resp = new DatagramPacket(new byte[512], 512);
			// recebe a resposta, tudo encapsulado
			s.receive(resp);

			// percorre a resp que é um array e impreme na forma de char
			for (int i = 0; i < resp.getLength(); i++) {
				System.out.print((char) resp.getData()[i]);
			}
			
		//	System.out.println();
			System.out.print("\n> ");
			env = teclado.readLine();
		}

		// encerra o datagrama criado
		s.close();
	}

}
