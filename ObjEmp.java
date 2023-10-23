public class ObjEmp{
	public int [] valeurs;
	public int taille;
	
	public ObjEmp(int x){
		this.valeurs= new int[]{x};
		this.taille=1;
	}
	
	public ObjEmp(int x, int y){
		this.valeurs=new int[]{x, y};
		this.taille=2;
	}

	public ObjEmp(int x, int y, int z){
		this.valeurs=new int[]{x, y, z};
		this.taille=3;
	}

	public ObjEmp ope(ObjEmp o2, String type){
		int len = Math.max(this.taille, o2.taille);
		int [] res = new int[len];
		 
		try{
		switch(type){
			case "add":
				for(int i=0;i<len;i++){
					res[i]=this.valeurs[i] + o2.valeurs[i];
				}
				break;
				
			case "sub":
				for(int i=0;i<len;i++){
					res[i]=this.valeurs[i] - o2.valeurs[i];
				}
				break;
			case "mul":
				for(int i=0;i<len;i++){
					res[i]=this.valeurs[i] * o2.valeurs[i];
				}
				break;
			case "div":
				for(int i=0;i<len;i++){
					res[i]=this.valeurs[i] / o2.valeurs[i];
				}
				break;
			}
		}catch(ArrayIndexOutOfBoundsException e){
		}

		if(len==1){
			return new ObjEmp(res[0]);
		} else if(len==2){
			return new ObjEmp(res[0], res[1]);
		} else {
			return new ObjEmp(res[0], res[1], res[2]);
		}
	}

	public String toString(){
		String affichage = "(";
		for(int i=0;i<this.taille-1;i++){
			affichage+=this.valeurs[i]+", ";
		}
		affichage+=this.valeurs[this.taille-1]+")";
		return affichage;
	}
}
