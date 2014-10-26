package jhuffman.def;

public class Node
{
	private int c;
	private long n;
	
	private Node sig=null;
	private Node izq=null;
	private Node der=null;
	
	public int getC()
	{
		return c;
	}
	public void setC(int c)
	{
		this.c = c;
	}
	public long getN()
	{
		return n;
	}
	public void setN(long n)
	{
		this.n = n;
	}
	public Node getSig()
	{
		return sig;
	}
	public void setSig(Node sig)
	{
		this.sig = sig;
	}
	public Node getIzq()
	{
		return izq;
	}
	public void setIzq(Node izq)
	{
		this.izq = izq;
	}
	public Node getDer()
	{
		return der;
	}
	public void setDer(Node der)
	{
		this.der = der;
	}
	
	@Override
	public String toString()
	{
		String ret=c<256?Character.toString((char)c):("*"+(256-c));
		ret+="("+n+")";
		return ret;
	}
	
	

	
	
}
