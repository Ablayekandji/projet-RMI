
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.TextField;


public class Client extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTable table;
    private JTextField textField_3;
	public Client() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(200, 10, 811, 597);
	        setResizable(false);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        
	        JLabel lblNewLabel = new JLabel("SEN PRODUITS ");
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	        lblNewLabel.setBounds(198, 11, 276, 56);
	        contentPane.add(lblNewLabel);
	        
	        JLabel lblNewLabel_1 = new JLabel(" identifiant");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblNewLabel_1.setBounds(57, 124, 128, 27);
	        contentPane.add(lblNewLabel_1);
	        
	        textField = new JTextField();
	        textField.setBounds(198, 118, 229, 38);
	        contentPane.add(textField);
	        textField.setColumns(10);
	        
	        JLabel lblNewLabel_2 = new JLabel("Nom");
	        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblNewLabel_2.setBounds(65, 192, 120, 27);
	        contentPane.add(lblNewLabel_2);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(198, 195, 229, 38);
	        contentPane.add(textField_1);
	        textField_1.setColumns(10);
	        
	        JLabel lblNewLabel_3 = new JLabel("Prix");
	        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblNewLabel_3.setBounds(65, 272, 108, 27);
	        contentPane.add(lblNewLabel_3);
	        
	        textField_2 = new JTextField();
	        textField_2.setBounds(198, 257, 229, 38);
	        contentPane.add(textField_2);
	        textField_2.setColumns(10);
	        
	        JButton btnNewButton = new JButton("Enregistrer");
	        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		try {
	        			System.out.println("Recherche de l'objet serveur...");
	        			ProduitI stub = (ProduitI)Naming.lookup("rmi://192.168.1.19:1099/PR");	
	        			System.out.println("Invocation des Methodes");
	        			Produit a = new Produit(textField.getText().toString(),textField_1.getText().toString(),Integer.parseInt(textField_2.getText().toString()));
	        			int val=stub.ajouterProduit(a);
	        			if(val==1)
	        				JOptionPane.showMessageDialog(null, "Enregistrer avec Succes");
	        			else if(val==0)
	        				JOptionPane.showMessageDialog(null, "Recommencer SVP!!!");
	        			
	        			
	        			
	        		} catch(Exception arg) {
	        			arg.printStackTrace();
	        		}
	        	}
	        });
	        btnNewButton.setBounds(258, 364, 133, 38);
	        contentPane.add(btnNewButton);
	        
	        JButton button = new JButton("Liste Des Produits");
	        button.setFont(new Font("Tahoma", Font.BOLD, 14));
	        button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
        			

	        		LinkedList<Produit> result;
					try {
						ProduitI stub = (ProduitI)Naming.lookup("rmi://192.168.1.19:1099/PR");	
						result = stub.listeProduits();
						String columns[] = { "ID", "Nom","Prix"};
					      String data[][] = new String[200][3];
					      int i=0;
						for(Produit n : result) {
							  
							   System.out.println("---------------------------------");
	        	               System.out.println("Id : "+ n.getIdentifiant());
	        	               System.out.println("Nom : "+ n.getNom());
	        	               System.out.println("Prix : "+ n.getPrix());
	        	               data[i][0] = n.getIdentifiant() + "";
	        	               data[i][1] =  n.getNom();
	        	               data[i][2] =String.valueOf(n.getPrix());
	        	               i++;
	        	               
	        			}
						DefaultTableModel model = new DefaultTableModel(data, columns);
					      JTable table = new JTable(model);
					      table.setShowGrid(true);
					      table.setShowVerticalLines(true);
					      JScrollPane pane = new JScrollPane(table);
					      JFrame f = new JFrame("Liste des Produits");
					      JButton retour=new JButton("Retour");
					      JPanel panel = new JPanel();
					      panel.add(pane);
					      panel.add(retour);
					      f.getContentPane().add(panel);
					      f.setSize(500, 500);
					      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					      f.setVisible(true);
					      retour.addActionListener(new  ActionListener() {
					        	public void actionPerformed(ActionEvent e) {
					        		Client t = new Client();
					        		f.setVisible(false);
					        		t.setVisible(true);
					        	}
					        	});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			
	        	}
	        });
	        button.setBounds(582, 108, 180, 48);
	        contentPane.add(button);
	        
	        table = new JTable();
	        table.setBounds(455, 416, 307, -224);
	        contentPane.add(table);
	        
	        JLabel lblNewLabel_4 = new JLabel("Rechercher");
	        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblNewLabel_4.setBounds(483, 305, 89, 33);
	        contentPane.add(lblNewLabel_4);
	        
	        textField_3 = new JTextField();
	        textField_3.setBounds(582, 301, 180, 40);
	        contentPane.add(textField_3);
	        textField_3.setColumns(10);
	        
	        JButton btnNewButton_1 = new JButton("Valider");
	        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
        			try {
        				ProduitI stub = (ProduitI)Naming.lookup("rmi://192.168.1.19:1099/PR");	
    	        		Produit C = null;
    	        		//System.out.println(C.getNom());
    	        		C=stub.prixProduit(textField_3.getText().toString());
						JOptionPane.showMessageDialog(null,"Voici le prix du produit: \n"+"Identifiant: "+ C.getIdentifiant()+"\n"+"Nom: "+C.getNom()+"\n"+"Prix: "+C.getPrix());
						System.out.println("Affichage du resultat :");
	        			System.out.println("Voici le prix du produit: "+C.getIdentifiant()+"  "+C.getNom()+"  "+C.getPrix());
	        			
    	        		
    	        		
	        				
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        			
	        	}
	        });
	        btnNewButton_1.setBounds(623, 364, 120, 38);
	        contentPane.add(btnNewButton_1);
	}
	public static void main(String[] args) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		Client affi = new Client();
			   affi.setVisible(true);
		
	}
}
