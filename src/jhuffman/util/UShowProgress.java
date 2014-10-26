package jhuffman.util;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class UShowProgress 
{
	private static UShowProgress progreso;
	private JFrame frame;
	private JTextArea consola;
	
	private long i=0;
	private int porcAnt=0;
	private int porcentaje=0;
	private long longitud=0;
	private String procName=null;
    private long milliInic;
    private long milliTotal;    
    private int txtDesde;
    private int txtHasta;
    
    public Frame getFrame()
    {
    	return frame;
    }
    
    public JTextArea getTextArea()
    {
    	return consola;
    }

    
    private UShowProgress()
	{
		frame=new JFrame("Procesando");
		consola=new JTextArea();
		consola.setFont(new Font(Font.MONOSPACED,Font.PLAIN,12));
		frame.getContentPane().add(new JScrollPane(consola),BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,300);
	}
	
	public static UShowProgress getProgress()
	{
		if(progreso==null)
		{
			progreso=new UShowProgress();
		}
		
		if( !progreso.frame.isVisible() )
		{
			progreso.frame.setVisible(true);
			progreso.frame.requestFocus();
		}
		
		return progreso;
	}
	
    public void setProcessLength(long n)
    {
    	longitud=n;
    }
    
    public void setProcessName(String s)
    {
    	procName=s;
    } 
    
    public void beginProcess()
    {
    	consola.append(procName+" [000%]");
    	milliInic=System.currentTimeMillis();
    	porcentaje=0;
    	porcAnt=0;
    	i=0;
    	
    	txtHasta=consola.getText().lastIndexOf("%");
    	txtDesde=txtHasta-3;
		consola.setCursor(new Cursor(Cursor.WAIT_CURSOR));

    }
    
    public void addProgress()
    {
    	i++;
    	porcentaje=(int)(i*100/longitud);
    	if( porcentaje>porcAnt )
    	{
    		String sPorc=UString.lpad(Integer.toString(porcentaje),3,'0');
    		consola.replaceRange(sPorc, txtDesde, txtHasta);
    		porcAnt=porcentaje;
    	}
    }
    
	public void endProcess()
	{
		long milliFin=System.currentTimeMillis();
		long tot=milliFin-milliInic;

		String s=UTime.toString(tot);
		
		consola.append(" "+s+".\n");
		milliTotal+=tot;
		consola.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
    public void mostrarEstadisticas()
    {
    	String s=UTime.toString(milliTotal);
    	consola.append("-->> Tiempo total insumido: "+s+" <<--");
    }    
}
