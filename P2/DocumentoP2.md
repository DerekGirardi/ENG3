Classe UsuarioThread

Método construtor: Referenciar o servidor e o socket.

Método PrintWriter mostrarUsuario(): Sua função é retornar determinado usuário.

Método run(): Método específico da Biblioteca Thread para verificar qual mensagem será enviada de acordo com o usuário que enviou.
Dentro do método há uma verificação que, enquanto o socket não estiver fechado, será executado um loop mostrando assim qual respectivo usuário mandou determinada mensagem.


Classe Usuario

Método construtor: Referenciar o nome do usuário, o endereço de IP e a porta.

Método executarUsuario(): Método responsável pela execução do usuário. É instanciado os objetos com seus respectivos parâmetros juntamente com a classe ServidorThread.
Enquanto a thread estiver rodando, ela será executada a cada fim de mensagem, iniciando uma nova.

Método Principal Main(): Após receber o nome do usuário, a execução entra em loop, logo se o campo solicitado estiver vazio, o mesmo alertará o erro notificando o usuário para digitar um nome válido.
Após o nome do usuário for validado, é chamado o construtor com os parâmetros solicitados e instanciado o mesmo, chamando o metódo para execução do usuário.


Classe ServidorThread

Método construtor: Referenciar o nome e o socket do usuário.

Método adcProximaMsg(): Método criado para manipular as mensagens da lista.
Utiliza-se o comando "synchronized" para garantir que duas threads não executem o mesmo método, e a seguir insere o primeiro elemento da lista de mensagens originalmente em espera.

Método run(): Método criado para gerar interface no chat, mostrando as mensagens para todos.
O método recebe o socket e define o autoflush (limpeza de memória ou campo) para falso, e enquanto este socket não estiver fechado, a execução entrará em um loop, mostrando se o servidor está disponível, habilitando a entrada de dados 
do scanner.
Se a mensagem digitada for não nula (verdadeira), é utilizado o comando synchronized, retornando a primeira mensagem da lista e mostrando para todos os usuários.


Classe Servidor

Método construtor: Referenciar a porta na hora da declaração no main.

Método executarServidor(): Responsável pela execução do servidor.
Após receber a porta a ser utilizada, é alertado que o servidor está funcionando através de um println, e em seguida é chamado o método adcUsuario().

Método List<UsuarioThread> getUsuario(): Retorna um usuário específico.

Método adcUsuario(): Responsável por adicionar o usuário de acordo com seu socket.
É exibido o endereço de cada usuário conectado, e em seguida eles são adicionados na lista de arrays de usuários.

Método Principal Main(): Após ser definido a porta, é feito a chamada do método executarServidor() para inicializar o servidor.
