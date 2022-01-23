
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.LinkedList;


public class ProduitIplement extends  UnicastRemoteObject implements ProduitI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connection;
	 static final String QUERY = "select *from mesprod where id =?";
    
     String id ="",nom="",prix="";

	protected ProduitIplement() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Produit prixProduit(String ide) throws RemoteException {
		Produit b = null;
		// TODO Auto-generated method stub
		   try {
	         	
	        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

	             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
	                 preparedStatement.setString(1,ide);
	                 System.out.println(preparedStatement);
	                
	                 ResultSet rs = preparedStatement.executeQuery();

	                 
	                 while (rs.next()) {
	                      id = rs.getString("id");
	                      nom = rs.getString("nom");
	                      prix = rs.getString("prix");
	                    
	                     System.out.println(id + "," + nom + "," + prix );
	                 }
	                 b =new Produit(id,nom,Integer.parseInt(prix));
	         } catch (Exception exception) {
	             exception.printStackTrace();
	         }
		return  b;
	}

	@Override
	public int ajouterProduit(Produit prod) throws RemoteException {
		// TODO Auto-generated method stub
		int retourner = 0;
        try {
        	//Class.forName("com.mysql.jdbc.Driver");
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
        	
            String query = "INSERT INTO mesprod values('" + prod.getIdentifiant() + "','" + prod.getNom() + "','" + prod.getPrix()   + "')";

            Statement sta = connection.createStatement();
            int reponse = sta.executeUpdate(query);
            if (reponse == 0) {
                System.out.println( "Probleme d'enregistrement");
                
                retourner=0;
            } else {
                System.out.println("Enregister avec succes");
                retourner=1;
                
            }
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
        return retourner;
	}
		
	@Override
	public LinkedList<Produit> listeProduits() throws RemoteException {
			LinkedList<Produit> list= new LinkedList<>();
		// TODO Auto-generated method stub
		final String mes = "select *from mesprod";
		try {
         	
        	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/produits?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");

             PreparedStatement preparedStatement = connection.prepareStatement(mes);
            // Statement instruction = connexion.createStatement();
             ResultSet resultat = preparedStatement.executeQuery("SELECT * FROM mesprod");
             while (resultat.next()) {
               System.out.println("---------------------------------");
               System.out.println("Id : "+ resultat.getString("id"));
               System.out.println("Nom : "+ resultat.getString("nom"));
               System.out.println("Prix : "+ resultat.getString("prix"));
               Produit test= new Produit(resultat.getString("id"),resultat.getString("nom"),Integer.parseInt(resultat.getString("prix")));
               list.add(test);
               
             }
             
         } catch (Exception exception) {
             exception.printStackTrace();
         }
		return list;
	}
	
	

}
