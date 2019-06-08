package servchat;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//Classe usuário responsável para conectar ao servidor.
public class Usuario
{
	private String nomeusuario, localhost;
	private int porta;
	
	//Construtor para referênciar o nome do usuário o endereço (IP) e a porta.
	private Usuario(String nomeusuario, String localhost, int porta)
	{
		this.nomeusuario = nomeusuario;
		this.localhost = localhost;
		this.porta = porta;
	}
	
	//Método para execução do usuário em si.
	private void executarUsuario()
	{
		try
		{
			//Instanciamos os obj com seus respectivos parâmetro
			//juntamente com a classe ServidorThread.
			Scanner scn = new Scanner(System.in);
			Socket socket = new Socket(this.localhost, this.porta);
			
			ServidorThread st = new ServidorThread(socket, this.nomeusuario);
			Thread thread = new Thread(st);
			thread.start();
			
			//Enquanto a thread estivar rodando ela será executada
			//a cada fim de msg, iniciando uma nova.
			while(thread.isAlive())
			{
				if(scn.hasNextLine())
				{
					st.adcProximaMsg(scn.nextLine());
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("Erro fatal no servidor: " + e.getMessage());
            e.printStackTrace();
		}
	}
	
	//Método main para executar a classe em si.
	public static void main(String[] args)
	{
		String lernome = null;
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Digite seu nome: ");
		
		//Entramos no loop automaticamente, logo se o campo solicitado estiver
		//vazio o mesmo alertará o erro notificando o usuário para digitar
		//um nome correto.
		while(lernome == null || lernome.trim().equals(""))
		{
			lernome = scn.nextLine();
			
			if(lernome.trim().equals(""))
			{
				System.out.println("Nome inválido, digite novamente: ");
			}
		}
		
		//Por fim chamamos o construtor com os parâmetros solicitados
		//e instanciamos o mesmo chamando o método para execução.
		Usuario usuario = new Usuario(lernome, "127.0.0.1", 8989);
		usuario.executarUsuario();
	}
}
