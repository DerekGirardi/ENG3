package servchat;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

//Classe criada para implementar a classe Servidor.
public class ServidorThread extends Thread
{
	private Socket socket;
	private String nomeusuario;
	private LinkedList<String> msgsenviar = new LinkedList<String>();
	private boolean msgs = false;
	
	//Construtor para referênciar o socket do usuário e o nome do mesmo.
	public ServidorThread(Socket socket, String nomeusuario)
	{
		this.socket = socket;
		this.nomeusuario = nomeusuario;
	}
	
	//Método criado para manipular as msgs da lista.
	public void adcProximaMsg(String mensagem)
	{
		synchronized(msgsenviar) //Faz com que duas thread não executem o msm método
		{
			msgs = true;
			msgsenviar.push(mensagem);
			//Insere o primeiro elemento da lista de msgs em espera
		}
	}
	
	//Método criado para gerar interface no chat, mostrando as msgs para todos.
	@Override
	public void run()
	{
		System.out.println("Bem vindo, " + this.nomeusuario + "." + "\nComece o bate papo!!!");
		
		try
		{
			//Recebe o socket e o define o autoflush (limpeza de memória ou campo) para falso
			PrintWriter mostrarservidor = new PrintWriter(this.socket.getOutputStream(), false);
			InputStream servidorstream = this.socket.getInputStream();
			Scanner scn = new Scanner(servidorstream);
			
			//Enquanto o socket não estiver fechado esse loop será executado
			//mostrando se o servidor está disponível habilitando a entrada de dados do scanner
			while(!socket.isClosed())
			{
				if(servidorstream.available() > 0)
				{
					if(scn.hasNextLine())
					{
						System.out.println(scn.nextLine());
					}
				}
				//Se a msg digitada for verdadeira (não nula) é utilizado um synchro
				//retornando a primeira msg da lista e mostrando para todos os usuários
				if(msgs)
				{
					String proximamsg = "";
					synchronized(msgsenviar)
					{
						proximamsg = msgsenviar.pop();
						msgs = !msgsenviar.isEmpty();
					}

					mostrarservidor.println(this.nomeusuario + ": " + proximamsg);
					mostrarservidor.flush();
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Erro no envio de mensagem: " + e.getMessage());
			e.printStackTrace();
		}

	}
}