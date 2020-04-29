package GameComponents;

import Utente.User;
import java.util.ArrayList;
/**
 * @author Moresi Gianmarco
 */
public class Inventory {
    User utente= new User();
    ArrayList<GameObject> objects= new ArrayList<>();

    public Inventory(User usr){
        this.utente.setEmail(usr.getEmail());
        this.utente.setUsername(usr.getUsername());
    }

    //Aggiunge un insieme di oggetti all'inventario
    public void addObjects(GameObject[] objs){
        for(int index=0; index<objs.length; index++){
            this.objects.add(index, objs[index]);
        }
    }

    //aggiunge un singolo oggetto all'inventario
    public void addSingleObject(GameObject object){
        this.objects.add(this.objects.size(), object);
    }

    public void dropObject(GameObject object){
        this.objects.remove(this.getIndex(object));

    }

    private int getIndex(GameObject obj){
        int index=0;
        for(index=0; index< this.objects.size(); index++){
            if(objects.equals(obj)){
                return index;
            }
        }
        return index;
    }



}