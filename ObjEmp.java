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

	public ObjEmp add(ObjEmp o2){
		int len = Math.max(this.taille, o2.taille);
		int [] somme = new int[len];
		 
		try{
			for(int i=0;i<len;i++){
				somme[i]=this.valeurs[i] + o2.valeurs[i];
			}
		}catch(ArrayIndexOutOfBoundsException e){
		System.out.println("Addition impossible !");
		}

		if(len==1){
			return new ObjEmp(somme[0]);
		} else if(len==2){
			return new ObjEmp(somme[0], somme[1]);
		} else {
			return new ObjEmp(somme[0], somme[1], somme[2]);
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

	public static void main(String [] args){
		ObjEmp o1 = new ObjEmp(1, 2, 3);
		ObjEmp o2 = new ObjEmp(4, 5, 6);
		ObjEmp o3 = o1.add(o2);
		System.out.println(o3);
		ObjEmp o21 = new ObjEmp(1, 2);
		ObjEmp o22 = new ObjEmp(4, 5);
		ObjEmp o23 = o21.add(o22);
		System.out.println(o23);
		ObjEmp o33 = o21.add(o2);
		System.out.println(o33);
	}
}
