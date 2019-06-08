package padraobuilder;

public class CadeiraDirector
{
	protected CadeiraBuilder cadeira;
	
	public CadeiraDirector(CadeiraBuilder cadeira)
	{
		this.cadeira = cadeira;
	}
	
	public void criarCadeira()
	{
		cadeira.criarEncosto();
		cadeira.criarAssento();
		cadeira.criarPernas();
		cadeira.criarPreco();
	}
	
	public CadeiraProduct getCadeira()
	{
		return cadeira.getCadeira();
	}
}