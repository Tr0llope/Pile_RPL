import java.util.Scanner;

public class CalcUI {
    
    public void empiler(ObjEmp o){

    }

    public void operation(){

    }

    public static void main(String [] args){
        System.out.println("Bienvenue dans la calculatrice RPL !\n" + //
            "Pour voir les commandes disponibles, tapez 'commandes'.\n" + //
            "Commencez par renseigner la taille de la pile:\n"
            );
        Scanner sc = new Scanner(System.in);
        int taille = sc.nextInt();
        PileRPL pile = new PileRPL(taille);
        System.out.println("Pile créée.\n");

        String input = sc.nextLine();
        while(!input.equals("quitter")){
            if(input.equals("empiler")){
                System.out.println("Veuillez entrer la valeur à empiler sous la forme suivante: x, x y ou x y z.");
                input = sc.nextLine();
                String[] valeurs = input.split(" ");
                if(valeurs.length==1){
                    ObjEmp o = new ObjEmp(Integer.parseInt(valeurs[0]));
                    pile.push(o);
                } else if(valeurs.length==2){
                    ObjEmp o = new ObjEmp(Integer.parseInt(valeurs[0]), Integer.parseInt(valeurs[1]));
                    pile.push(o);
                } else if(valeurs.length==3){
                    ObjEmp o = new ObjEmp(Integer.parseInt(valeurs[0]), Integer.parseInt(valeurs[1]), Integer.parseInt(valeurs[2]));
                    pile.push(o);
                } else {
                    System.out.println("Valeur invalide.");
                }
                System.out.println("Valeur empilée.");

            } else if(input.equals("operation")){
                pile.add();
                System.out.println("Opération effectuée.");

            } else if(input.equals("afficher")){
                System.out.println(pile);

            } else if(input.equals("commandes")){
                System.out.println(
                "Pour ajouter une opérande, tapez 'empiler'\n" + //
                "Pour effectuer une opération, tapez 'operation'.\n" + //
                "Pour afficher la pile, tapez 'afficher'.\n" + //
                "Pour quitter, tapez 'quitter'." + //
                "Pour avoir plus d'information concernant le fonctionnement de la pile, tapez 'help'"
                );
            } else if(input.equals("help")){
              System.out.println("Cette calculatrice permet d'additionner des vecteurs de dimension 1, 2 ou 3.\n" + //
                  "Il faut d'abord empiler les opérandes, puis effectuer l'opération.\n" + //
                  "Exemple: empiler (1,2,3) puis empiler (4,5,6) puis operation.\n"); 
            } else {
                System.out.println("Commande invalide.");
            }
            input = sc.nextLine();
        }
    }
}
