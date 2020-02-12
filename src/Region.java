import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

/**
 * This class generates different regions that inhabit the space trader universe
 */
public class Region {
    protected String name;
    protected int techLevel;
    protected int xCoordinate;
    protected int yCoordinate;
    protected String description;
    protected boolean hasBeenVisited = false;
    protected static int numberOfRegions = 0;
    protected int regionID;

    private Random rand = new Random();

    public Region() {
        name = generateName();
        techLevel = rand.nextInt(5) + 1;
        xCoordinate = rand.nextInt(1000);
        yCoordinate = rand.nextInt(700);
        if (rand.nextInt(1000) % 2 == 0) {
            xCoordinate = -xCoordinate;
        }
        if (rand.nextInt(1000) % 2 == 0) {
            yCoordinate = -yCoordinate;
        }
        description = name + ", a level " + techLevel + " civilization \r located at X:" + xCoordinate + " | Y:"
                + yCoordinate;
        regionID = numberOfRegions;
        numberOfRegions++;
        //System.out.print(this.regionID + " Generated");
    }

    private String generateName() {
        ArrayList<String> nameDictionary = new ArrayList<>();
        ArrayList<String> classDictionary = new ArrayList<>();
        Scanner regionNameSC = null;
        Scanner classNameSC = null;
        try {
            File regionName = new File("resources/RegionNames.txt");
            regionNameSC = new Scanner(regionName);

            File className = new File("resources/RegionClassName.txt");
            classNameSC = new Scanner(className);
        } catch (FileNotFoundException e) {
            System.out.println("Region's names files are missing from the resources folder");
        }
        while (regionNameSC.hasNext()) {
            nameDictionary.add(regionNameSC.next());
        }
        while (classNameSC.hasNext()) {
            classDictionary.add(classNameSC.nextLine());
        }

        return nameDictionary.get(rand.nextInt(nameDictionary.size()))
                + classDictionary.get(rand.nextInt(classDictionary.size()));
    }
}

