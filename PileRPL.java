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
		return nbObj==NB_OBJ_MAX;
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

	public ObjEmp pop(){
		ObjEmp res = null;
		if (!isEmpty()){ 
			res = tab[nbObj-1];
			nbObj-=1;
		}
		else{	
			System.out.println("Pile vide !");
		}
		return res;
	}

	public void ope(String type){
		if(nbObj>=2){ 
			ObjEmp o1 = this.pop();
			ObjEmp o2 = this.pop();
			
			ObjEmp res = o1.ope(o2, type);
			this.push(res);
		} else {
			System.out.println("Trop peu d'arguments !");
		}
	}

	public String toString(){
		String affichage="	++-----------++\n";
		if(nbObj==0){
			affichage+="	|| Pile vide ||\n";
		}else{
			for(int i=0;i<nbObj;i++){
				String elem = tab[i].toString();
				affichage+="	|| " + elem;
				for(int j = 0;j<(10-elem.length());j++){
					affichage += " ";
				}
				affichage += "||\n";
			}
		}
		affichage+="	++-----------++\n";
		return affichage; 
	}

}
