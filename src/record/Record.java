package record;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import version.GameVersion;
import version.LevelList;


public class Record {
    private static final String recordName = "test";
    private static final String recordPath = "../records/";
    private static final ArrayList< String > availableAllyTypes = new ArrayList< String >();
    private static final ArrayList< String > chosenAllyTypes = new ArrayList< String >();
    private static final Dictionary< String, String > levelProgresses = new Hashtable<String, String>();   // <battle type, highest level>
    private static String currentBattleTypeName;
    private static String currentLevelName;
    public static void setLevelProgress(String battleTypeName, String levelName){
        levelProgresses.put(battleTypeName, levelName);
    }
    public static String getHighestLevelName(String battleTypeName){
        return levelProgresses.get(battleTypeName);
    }
    public static void setCurrentBattleType(String battleTypeName){
        currentBattleTypeName = battleTypeName;
    }
    public static String getCurrentBattleTypeName(){
        return currentBattleTypeName;
    }
    public static String getCurrentLevel(){
        return currentLevelName;
    }
    public static ArrayList< String > getChosenAllyTypes(){
        ArrayList< String > ret = new ArrayList< String >();
        for(String type : chosenAllyTypes){
            ret.add(type);
        }
        return ret;
    }
    public static void setChosenAllyTypes(ArrayList< String > _chosenAllyTypes){
        chosenAllyTypes.clear();
        for(String type : _chosenAllyTypes){
            chosenAllyTypes.add(type);
        }
    }
    public static ArrayList< String > getAvailableAllyTypes(){
        ArrayList< String > ret = new ArrayList< String >();
        for(String Type : availableAllyTypes){
            ret.add(Type);
        }
        return ret;
    }
    public static void addAvailableAllyTypes(String type){
        availableAllyTypes.add(type);
    }
    public static boolean loadRecord(String recordName){
        String checkPoint = "";
        try{
            BufferedReader fr = new BufferedReader(new FileReader(recordPath + recordName + ".txt"));
            // available ally
            checkPoint = fr.readLine();
            if(!checkPoint.equals("Available Ally")){
                System.out.println("[Record] Record file format error : Available Ally.");
                fr.close();
                return false;
            }
            int availableAllyNum = Integer.parseInt(fr.readLine());
            for(int i = 0; i < availableAllyNum; i++){
                availableAllyTypes.add(fr.readLine());
            }

            // chosen ally
            checkPoint = fr.readLine();
            if(!checkPoint.equals("Chosen Ally")){
                System.out.println("[Record] Record file format error : Chosen Ally.");
                fr.close();
                return false;
            }
            int chosenAllyNum = Integer.parseInt(fr.readLine());
            for(int i = 0; i < chosenAllyNum; i++){
                chosenAllyTypes.add(fr.readLine());
            }
            
            // level progresses
            checkPoint = fr.readLine();
            if(!checkPoint.equals("Level Progress")){
                System.out.println("[Record] Record file format error : Level Progress.");
                fr.close();
                return false;
            }
            int battleTypesNum = Integer.parseInt(fr.readLine());
            for(int i = 0; i < battleTypesNum; i++){
                String[] progress = fr.readLine().split(": ");
                levelProgresses.put(progress[0], progress[1]);
            }
            // current battle type
            checkPoint = fr.readLine();
            if(!checkPoint.equals("Current Battle Type")){
                System.out.println("[Record] Record file format error : Current Battle Type.");
                fr.close();
                return false;
            }
            currentBattleTypeName = fr.readLine();
            currentLevelName = levelProgresses.get(currentBattleTypeName);
            if( currentLevelName == null){
                System.out.println("[Record]: Current battle type not supported, changed to Normal.");
                currentBattleTypeName = "Normal";
                currentLevelName = levelProgresses.get(currentBattleTypeName);
            }
            fr.close();
        }
        catch(FileNotFoundException e){
            System.out.println("[Record]: Cannot find last record.");
            return false;
        }
        catch(IOException e){
            System.out.println("[Record]: Reach unexpected EOF.");
            return false;
        }
        return true;
    }
    public static void gotoNextLevel(){
        currentLevelName = GameVersion.nextLevel(currentBattleTypeName, currentLevelName);
        String progress = levelProgresses.get(currentBattleTypeName);
        // if next level is locked, unlock it
        setLevelProgress(currentBattleTypeName, GameVersion.getHigherLevelOf(currentBattleTypeName, progress, currentLevelName));   
    }
    public static void writeLevel(){
        try{
            File recordFile = new File(recordPath + recordName);
            recordFile.createNewFile();
            // if (recordFile.createNewFile()) {
            //     System.out.println("File created: " + recordFile.getName());
            // } else {
            //     System.out.println("File already exists.");
            // }
            FileWriter fw = new FileWriter(recordPath + recordName);
            // available ally
            fw.write("Available Ally\n");
            fw.write(availableAllyTypes.size() + "\n");
            for(String allyTypeName : availableAllyTypes){
                fw.write(allyTypeName + "\n");
            }
            // chosen ally
            fw.write("Chosen Ally\n");
            fw.write(chosenAllyTypes.size() + "\n");
            for(String allyTypeName : chosenAllyTypes){
                fw.write(allyTypeName + "\n");
            }
            // level progresses
            fw.write("Level Progress\n");
            fw.write(levelProgresses.size());
            Enumeration< String > battleTypeNames = levelProgresses.keys();
            while (battleTypeNames.hasMoreElements()){
               String battleType = battleTypeNames.nextElement();
               String progress = levelProgresses.get(battleType);
               fw.write(battleType + ": " + progress + "\n");
            }
            // current battle type
            fw.write("Current Battle Type\n");
            fw.write(currentBattleTypeName + "\n");
            fw.close();
        }
        catch(FileNotFoundException e){
            System.out.println("[Record] Write record fail: File "+ recordPath+recordName + " not found.");
        }
        catch(IOException e){
            System.out.println("[Record] Write record fail: IO exception.");
        }
    }
}
