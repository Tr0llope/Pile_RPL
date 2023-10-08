import java.lang.*;
import java.io.*;

public class PileRPL{
	private int nbObj;
	private static final int NB_OBJ_MAX=10;
	public ObjEmp [] tab;


	public PileRPL(){
		this.nbObj=0;
		this.tab = new ObjEmp[NB_OBJ_MAX];
	}
	public PileRPL(int taille){
		this.nbObj=0;
		this.tab = new ObjEmp[taille];

	}

	public boolean isEmpty(){
		return tab.length==0;
	}

	public boolean isFull(){
		return tab.length==NB_OBJ_MAX;
	}

	public void push(ObjEmp o){
		if (!isFull()){ 
			tab[nbObj]=o;		
			nbObj+=1;
		}
		else{
			System.out.println("Pile pleine !");
		}
	}

	public int pop(){
		int res=0;
		if (!isEmpty()){ 
			res = tab[nbObj-1].valeur;
			nbObj-=1;
		}
		else{	
			System.out.println("Pile vide !");
		}
		return res;
	}

	public void add(){
		if(nbObj<=2){ 
			ObjEmp o1 = this.pop();
			ObjEmp o2 = this.pop();
			
			int [] res = o1.add(o2);
			
			ObjEmp res = new ObjEmp();
			this.push(res);
		} else {
			System.out.println("Trop peu d'arguments !");
		}
	}

	public String toString(){
		String affichage="";
		for(int i=0;i<nbObj;i++){
			String elem = Integer.toString(tab[i].entier);
			affichage+="	* " + elem;
			affichage+= new String(new char[3-elem.length()]).replace("\0", " ") +  "*\n";
		}
		affichage+="	******\n";
		return affichage; 
	}

	public static void main(String[] args){
		ObjEmp o1 = new ObjEmp(5);
		ObjEmp o2 = new ObjEmp(12);
		ObjEmp o3 = new ObjEmp(6,4);
		PileRPL pile = new PileRPL(5);
		System.out.println(pile);
		pile.push(o1);
		System.out.println(pile);
		pile.push(o2);
		System.out.println(pile);
		pile.add();
		System.out.println(pile);
		pile.push(o3);
		System.out.println(pile);
		pile.add();
		System.out.println(pile);

	}
}
