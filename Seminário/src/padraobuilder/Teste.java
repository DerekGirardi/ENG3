package padraobuilder;

public class Teste
{
	public static void main(String[] args)
	{
		CadeiraDirector cadeira = new CadeiraDirector(new CadeiraConcreteProduct01());
		cadeira.criarCadeira();
		CadeiraProduct cadeiranova = cadeira.getCadeira();
		
		System.out.println("Encosto: " + cadeiranova.encosto + "\nAssento: " + 
		cadeiranova.assento + "\nQt. Pernas: " + cadeiranova.pernas + 
		"\nPreço: R$" + cadeiranova.preco + "\n");
		
		cadeira = new CadeiraDirector(new CadeiraConcreteProduct02());
		cadeira.criarCadeira();
		cadeiranova = cadeira.getCadeira();
		
		System.out.println("Encosto: " + cadeiranova.encosto + "\nAssento: " + 
		cadeiranova.assento + "\nQt. Pernas: " + cadeiranova.pernas + 
		"\nPreço: R$" + cadeiranova.preco);
	}
}
