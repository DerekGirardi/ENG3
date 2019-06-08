package servchat;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//Classe usu�rio respons�vel para conectar ao servidor.
public class Usuario
{
	private String nomeusuario, localhost;
	private int porta;
	
	//Construtor para refer�nciar o nome do usu�rio o endere�o (IP) e a porta.
	private Usuario(String nomeusuario, String localhost, int porta)
	{
		this.nomeusuario = nomeusuario;
		this.localhost = localhost;
		this.porta = porta;
	}
	
	//M�todo para execu��o do usu�rio em si.
	private void executarUsuario()
	{
		try
		{
			//Instanciamos os obj com seus respectivos par�metro
			//juntamente com a classe ServidorThread.
			Scanner scn = new Scanner(System.in);
			Socket socket = new Socket(this.localhost, this.porta);
			
			ServidorThread st = new ServidorThread(socket, this.nomeusuario);
			Thread thread = new Thread(st);
			thread.start();
			
			//Enquanto a thread estivar rodando ela ser� executada
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
	
	//M�todo main para executar a classe em si.
	public static void main(String[] args)
	{
		String lernome = null;
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Digite seu nome: ");
		
		//Entramos no loop automaticamente, logo se o campo solicitado estiver
		//vazio o mesmo alertar� o erro notificando o usu�rio para digitar
		//um nome correto.
		while(lernome == null || lernome.trim().equals(""))
		{
			lernome = scn.nextLine();
			
			if(lernome.trim().equals(""))
			{
				System.out.println("Nome inv�lido, digite novamente: ");
			}
		}
		
		//Por fim chamamos o construtor com os par�metros solicitados
		//e instanciamos o mesmo chamando o m�todo para execu��o.
		Usuario usuario = new Usuario(lernome, "127.0.0.1", 8989);
		usuario.executarUsuario();
	}
}
