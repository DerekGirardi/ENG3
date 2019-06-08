package padraobuilder;

public class CadeiraConcreteProduct01 extends CadeiraBuilder
{
	@Override
	public void criarEncosto()
	{
		cadeira.encosto = "Almofada Macia";
	}

	@Override
	public void criarAssento()
	{
		cadeira.assento = "Almofada de Algodão";
	}

	@Override
	public void criarPernas()
	{
		cadeira.pernas = 3;
	}

	@Override
	public void criarPreco()
	{
		cadeira.preco = 120.00;
	}
}