package padraobuilder;

public class CadeiraConcreteProduct02 extends CadeiraBuilder
{
	@Override
	public void criarEncosto()
	{
		cadeira.encosto = "Almofada Escorregadia";
	}

	@Override
	public void criarAssento()
	{
		cadeira.assento = "Almofada de Lã";
	}

	@Override
	public void criarPernas()
	{
		cadeira.pernas = 4;
	}

	@Override
	public void criarPreco()
	{
		cadeira.preco = 200.00;
	}
}