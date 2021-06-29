package record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import battletype.BattleType;

public class RecordIO {
    private String recordPath = "./records/";
    private String recordName;
    private String levelListPath = "./levelList.txt";

    private final Set< String > battleTypes = new HashSet< String >();

    private int battletypesNum;
    
    private ArrayList< Integer > levelNum;
    public RecordIO(BattleType[] battleStatusCheckers){
        // get every battle types' name
        for(BattleType type : battleStatusCheckers){
            battleTypes.add(type.getName());
        }

        levelNum = new ArrayList< Integer >();
        try{
            BufferedReader fr = new BufferedReader(new FileReader(levelListPath));
            // first line will be number of types of battle
            battletypesNum = Integer.parseInt(fr.readLine());

            for(int i = 0; i < battletypesNum; i++){
                String battleType = fr.readLine();
                if(battleTypes.contains(battleType)){
                    levelNum.add(Integer.parseInt(fr.readLine()));  // how many level does this type has

                    for(int j = 0; j < levelNum.get(i); j++){
                        // read in level names

                    }
                }
                else{
                    System.out.println("Battle type " + battleType + " is not supported.");
                    levelNum.add(Integer.parseInt(fr.readLine())); 
                    for(int j = 0; j < levelNum.get(i); j++){ fr.readLine(); }
                }
            }            
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File " + recordPath + recordName + " does not exist.");
        }
        catch(IOException e){
            System.out.println("Reach unexpected EOF.");
        }
    }   
    public void setRecord(String recordName){
        this.recordName = recordName;
    }
    public Record loadRecord(){
        try{
            BufferedReader fr = new BufferedReader(new FileReader(recordPath + recordName + ".txt"));
            // first line will be the highest level name
            String levelName = fr.readLine();
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File " + recordPath + recordName + " does not exist.");
        }
        catch(IOException e){
            System.out.println("Reach unexpected EOF.");
        }
    }   
    public void writeRecord(){

    }
}
