package jhuffman.def;

/**
 * Esta interface representa un codigo Huffman. <br>
 * Recordemos que se trata de un numero binario que puede tener 
 * hasta 128 digitos.
 */
public class ICode
{
	/**
	 * Retorna el i-esimo bit (1 o 0) de este codigo Huffman.
	 * @param i Es el i-esimo bit del codigo, contando de izquierda a derecha entre 0 y 127
	 * @return El i-esimo bit (1 o 0) de este codigo Huffman (contando desde la izquierda)
	 */
	
	int arr[] = new int[128];
	int len = 0;
	
	public int getBitAt(int i)
	{
		return arr[i];
	}

	
	/**
	 * Retorna la longitud de este codigo Huffman (la cantidad de digitos binarios).
	 * @return La longitud del codigo Huffman
	 */
	public int getLength()
	{
		return len;
	}
	
	/**
	 * Inicializa codigo Huffman tomando los caracteres de la cadena sCod 
	 * que deben ser "ceros" o "unos". 
	 * @param sCod Es la cadena compuesta de "ceros" y "unos" con los que se debe inicializar este codigo
	 */
	public void fromString(String sCod)
	{	
		for (int i=0;i<sCod.length();i++)
		{
			arr[i] = sCod.charAt(i);
		}
		len = sCod.length();
	}
}
