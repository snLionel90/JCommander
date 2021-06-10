package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import cmd.CrearProcesoCmd;

import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Scrollbar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

@SuppressWarnings("serial")
public class JCommader extends JFrame {

	private JPanel contentPane;
	private JTextArea textResultado;
	private JTextArea textComando;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCommader frame = new JCommader();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCommader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textComando = new JTextArea();
		//textComando.setBounds(90, 45, 240, 77);
		
		JScrollPane sp2 = new JScrollPane(textComando, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp2.setBounds(90, 45, 240, 77);
		contentPane.add(sp2);
		//contentPane.add(textComando);
		
		JButton btnLanzar = new JButton("Lanzar");
		btnLanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//todo el codigo va aqui
			String comando= textComando.getText();
			String[] listaComando = comando.split("\n");
			String resultado="";
			//System.out.println(comando);
			for (int i=0; i<listaComando.length; i++) 
			{
				System.out.println("comando " +listaComando[i]);
				CrearProcesoCmd cpc= new CrearProcesoCmd(listaComando[i]);
				
				String resultado1 = cpc.mostrarComando(cpc.getP());
				String resultado2 = cpc.mostrarErroresComando(cpc.getP());
				resultado = resultado+resultado1+resultado2 +"\n";
			}
			System.out.println("mostrando el resultado -->" +resultado);
			textResultado.setText("res:" +resultado);
			}
		});
		btnLanzar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLanzar.setBounds(71, 142, 108, 23);
		contentPane.add(btnLanzar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResultado.setText("");
				textComando.setText("");
				
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpiar.setBounds(250, 142, 108, 23);
		contentPane.add(btnLimpiar);
		
		this.textResultado = new JTextArea();
		textResultado.setBounds(30, 176, 360, 47);
		JScrollPane sp = new JScrollPane(textResultado, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(30, 176, 360, 47);
		contentPane.add(sp);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		contentPane.add(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo/File");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir/exit");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
				dispose();
			}
		});
		
		JMenuItem mntmAblil = new JMenuItem("Abrir/Open");
		mntmAblil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc =new JFileChooser();
				int opcion = fc.showOpenDialog(contentPane);
					if (opcion==JFileChooser.APPROVE_OPTION) 
					{
						File ficheroComando = fc.getSelectedFile();	
						//ilsdehfglasdjkhgakgñkrgadkga`gñdfkgdf
						try {
							FileReader fr= new FileReader (ficheroComando);
							BufferedReader br = new BufferedReader ( fr);
							String linea = "";
							String resultado= "";
							while ((linea=br.readLine()) !=null)
							{
								resultado = resultado+linea+"\n";
							}
							fr.close();
							textComando.setText(resultado);
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					}			
			}
		});
		mnArchivo.add(mntmAblil);
		
		JMenuBar menuBar_1 = new JMenuBar();
		mnArchivo.add(menuBar_1);
		
		JMenuItem mntmUu = new JMenuItem("Propiedades");
		mnArchivo.add(mntmUu);
		mnArchivo.add(mntmSalir);
		//contentPane.add(textResultado);

	}
}
