import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class ProduitServeur {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				ProduitIplement prod = new ProduitIplement();
				
				 LocateRegistry.createRegistry(1099);
			
				System.out.println("Création de l'objet serveur...");
				
				System.out.println(prod.toString());
				Naming.rebind("rmi://192.168.1.19:1099/PR",prod);
				//je change simplement rmi://localhost si c'est pour local et je donne l'adresse ip si c'est pour
				//des machines qui se situent sur le meme reseau
				
			} catch(Exception e) {
				e.printStackTrace();
			}
	}

}
