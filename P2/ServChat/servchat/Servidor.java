package servchat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Classe criada respons�vel pela cria��o do Servidor.
public class Servidor
{
    private int porta;
    //Porta padr�o de conex�o.
    private List<UsuarioThread> threadusuarios = new ArrayList<UsuarioThread>();
    //Criamos uma lista de arrays de Usuarios para que o servidor n�o possua somente um usu�rio.
    
    //Construtor para refer�nciar a porta na hora da declara��o no main.
    public Servidor(int porta)
    {
        this.porta = porta;
    }

    //M�todo para execu��o do servidor em si.
    private void executarServidor()
    {
        try
        {
        	ServerSocket ss = new ServerSocket(this.porta);
        	System.out.println("Servidor rodando na porta " + this.porta + "...");
            adcUsuario(ss);
            //Logo alertamos que o servidor est� funcionando e chamamos o m�todo adcUsu�rios().
        }
        catch (IOException e)
        {
            System.out.println("Erro no servidor/porta: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    //M�todo para retorno do usu�rio espec�fico.
    public List<UsuarioThread> getUsuario()
    {
        return this.threadusuarios;
    }
    
    //M�todo para adicionar o usu�rio de acordo com o socket do mesmo.
    private void adcUsuario(ServerSocket serverSocket)
    {
        while(true)
        {
            try
            {
            	//Mostramos os endere�os de cada usu�rio com seus nomes e adicionamos
            	//os mesmo na lista de arrays de usu�rio
                Socket socket = serverSocket.accept();
                System.out.println("Endere�o do Socket: " + socket.getRemoteSocketAddress());
                
                UsuarioThread usuario = new UsuarioThread(this, socket);//this = servidor
                Thread thread = new Thread(usuario);
                thread.start();
                
                threadusuarios.add(usuario);
            }
            catch (IOException e)
            {
                System.out.println("Erro no adi��o do usu�rio: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    //M�todo para execu��o da classe em si.
    public static void main(String[] args)
    {
        Servidor s = new Servidor(8989);
        s.executarServidor();
    }
}