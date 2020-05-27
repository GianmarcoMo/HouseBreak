package GameComponents;

import java.util.ArrayList;
/**
 * @author Moresi Gianmarco
 */
public class Inventory {
    private final ArrayList<GameObject> objectsUser;
    private int size;

    public Inventory(){
        objectsUser= new ArrayList<>();
        size=2;
    }
    
    public void guardaInventario(){
        if (objectsUser.size() >= 1) {
            if (objectsUser.size() >= 1) {
                System.out.println("\nOggetti nell'inventario:");
                objectsUser.forEach((elemento) -> {
                    System.out.println("-" + elemento.getNome() + "   " + elemento.getDescrizione());
                });
            }
        } else {
            System.out.println("Inventario piu' vuoto del tuo conto in banca!");
        }
        
    }

    //aggiunge un singolo oggetto all'inventario
    public void addObject(GameObject object){
        this.objectsUser.add(this.objectsUser.size(), object);
        System.out.println("Hai raccolto: "+ object.getNome());
        this.decementaSizeInventory();
    }

    public void dropObject(GameObject object){
        this.objectsUser.remove(this.getIndexObject(object));
        System.out.println("Hai lasciato: "+ object.getNome());
        this.incrementaSizeInvetory();
    }
    
    public boolean containsObject(GameObject objectInput){
        return objectsUser.stream().anyMatch((object) -> object.equals(objectInput));
    }

    private int getIndexObject(GameObject obj){
        for(int index=0; index< this.objectsUser.size(); index++){
            if(objectsUser.get(index).getNome().equals(obj.getNome())){
                return index;
            }
        }
        return -1;
    }
    private void setSizeInvetory(int sizeInput){
        this.size = sizeInput;
    }
    
    public int getSizeInvetory(){
        if(this.size>0){
            return this.size;
        }else{
            System.out.println("Inventario pieno! Elimina qualcosa!");
            return this.size;
        }
    }
    
    public void decementaSizeInventory(){
        this.size--;
    }
    
    public void incrementaSizeInvetory(){
        this.size++;
    }
}
