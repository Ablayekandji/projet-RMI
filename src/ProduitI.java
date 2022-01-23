import java.rmi.*;
import java.util.*;
public interface ProduitI extends Remote{
	
	public Produit  prixProduit(String nom) throws RemoteException;
	public int ajouterProduit(Produit prod) throws RemoteException;
	public LinkedList<Produit> listeProduits() throws RemoteException;

}
