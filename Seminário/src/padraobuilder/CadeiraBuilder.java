package padraobuilder;

public abstract class CadeiraBuilder
{
	protected CadeiraProduct cadeira;
	
	public CadeiraBuilder()
	{
		cadeira = new CadeiraProduct();
	}
	
	public abstract void criarEncosto();
	
	public abstract void criarAssento();
	
	public abstract void criarPernas();
	
	public abstract void criarPreco();
	
	public CadeiraProduct getCadeira()
	{
		return cadeira;
	}
}