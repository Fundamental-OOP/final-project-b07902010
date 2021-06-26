package unit;

public class AllyConstructor {
    public AllyConstructor(){

    }
    public Unit constructAlly(String allyType){     // and other attributes
        Unit newAlly = null;
        switch(allyType){
            case "Corgi":
                newAlly = new Corgi();
                break;
            case "MimiMaoMao":
                break;
            default:
                System.out.println("Ally type " + allyType + " not found.");
        }
        return newAlly;
    }
}
