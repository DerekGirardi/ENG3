package servchat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Classe criada responsável pela criação do Servidor.
public class Servidor
{
    private int porta;
    //Porta padrão de conexão.
    private List<UsuarioThread> threadusuarios = new ArrayList<UsuarioThread>();
    //Criamos uma lista de arrays de Usuarios para que o servidor não possua somente um usuário.
    
    //Construtor para referênciar a porta na hora da declaração no main.
    public Servidor(int porta)
    {
        this.porta = porta;
    }

    //Método para execução do servidor em si.
    private void executarServidor()
    {
        try
        {
        	ServerSocket ss = new ServerSocket(this.porta);
        	System.out.println("Servidor rodando na porta " + this.porta + "...");
            adcUsuario(ss);
            //Logo alertamos que o servidor está funcionando e chamamos o método adcUsuários().
        }
        catch (IOException e)
        {
            System.out.println("Erro no servidor/porta: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    //Método para retorno do usuário específico.
    public List<UsuarioThread> getUsuario()
    {
        return this.threadusuarios;
    }
    
    //Método para adicionar o usuário de acordo com o socket do mesmo.
    private void adcUsuario(ServerSocket serverSocket)
    {
        while(true)
        {
            try
            {
            	//Mostramos os endereços de cada usuário com seus nomes e adicionamos
            	//os mesmo na lista de arrays de usuário
                Socket socket = serverSocket.accept();
                System.out.println("Endereço do Socket: " + socket.getRemoteSocketAddress());
                
                UsuarioThread usuario = new UsuarioThread(this, socket);//this = servidor
                Thread thread = new Thread(usuario);
                thread.start();
                
                threadusuarios.add(usuario);
            }
            catch (IOException e)
            {
                System.out.println("Erro no adição do usuário: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    //Método para execução da classe em si.
    public static void main(String[] args)
    {
        Servidor s = new Servidor(8989);
        s.executarServidor();
    }
}