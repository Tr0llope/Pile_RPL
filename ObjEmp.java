public class ObjEmp{
	public int [] valeurs;
	
	public ObjEmp(int x){
		this.valeurs={x};	
	}
	
	public ObjEmp(int x, int y){
		this.valeurs={x, y};
	}

	public ObjEmp(int x, int y, int z){
		this.valeurs={x, y, z};
	}

	public int [] add(ObjEmp o2){
		int len = max(this.length(), o2.length());
		int [] somme;
		ObjEmp res = new 
		for(int i=0;i<len;i++){
			somme[i]=this.valeurs[i] + o2.valeurs[i];
		}
		
		return somme;
	}

	public String toString(){
		String affichage = "(";
		for(int i=0;i<this.valeurs.length();i++){
			affichage+=this.valeurs[i]+" ";
		}
		affichage+=")";
		return affichage;
	}
}
