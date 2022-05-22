package Lisa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Thomas.Jeu;
import Thomas.SNCF;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class JFrameJeu extends JFrame {

	private JPanel contentPane;
	private Jeu sim;
	private MonJPanelDessin panel;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameJeu frame = new JFrameJeu();
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
	public JFrameJeu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		
		

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setOpaque(false);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		panel_2.add(panel_5);
		
		JButton btnNewButton = new JButton("C'est parti !");
		panel_5.add(btnNewButton);
		panel_5.setOpaque(false);
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				clicStart();
			}
		});

		


		
	}
	public void clicStart() 
	{ 
		System.out.println("débutsncf");
		SNCF newsncf = new SNCF();
		
		System.out.println("finsncf");
		sim = new Jeu(newsncf, this);
	
		panel = new MonJPanelDessin(sim);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setOpaque(false);// peut recup valeur et pas que ecrire
		panel.repaint();// choisir bon panel
		sim.start();
	}
	/**
	public void afficherImage() {
		panel.setImage("croix.jpg");
		

	}
**/
	public void fond() {
		String imagePath = "C:/fond.jpg";
		ImageFond fond = new ImageFond(imagePath);
		fond.pack();
		fond.setVisible(true);
	}
}
