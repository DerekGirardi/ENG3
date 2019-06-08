package servchat;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//Classe criada para implementar a classe Servidor.
public class UsuarioThread extends Thread
{
	private Socket socket;
	private PrintWriter mostrarusuario;
	private Servidor servidor;
	private Scanner scn;
	
	//Construtor para refer�nciar o servidor e o socket.
	public UsuarioThread(Servidor servidor, Socket socket)
	{
		this.servidor = servidor;
		this.socket = socket;
	}
	
	//M�todo criado para mostrar determinado usu�rio.
	private PrintWriter mostrarUsuario()
	{
		return this.mostrarusuario;
	}

	//M�todo espec�fico da Biblioteca Thread para verificar qual msg ser� enviada
	//de acordo com o usu�rio que enviou.
	@Override
	public void run()
	{
		try
		{
			this.mostrarusuario = new PrintWriter(socket.getOutputStream(), false);
			
			//Enquanto o socket n�o estiver fechado esse loop ser� executado
			//mostrando assim qual respectivo usu�rio mandou a msg.
			while(!socket.isClosed())
			{
				scn = new Scanner(socket.getInputStream());

				if(scn.hasNextLine())
				{
					String entrada = scn.nextLine();
					PrintWriter mostrar;
					
					for(UsuarioThread usuario : servidor.getUsuario())
					{
						mostrar = usuario.mostrarUsuario();
						if(mostrar != null)
						{
							mostrar.write(entrada + "\r\n");
							mostrar.flush();
						}
					}
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("Erro na inser��o de mensagem: " + e.getMessage());
			e.printStackTrace();
		}
	}
}