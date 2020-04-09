package primary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * This class generates different regions that inhabit the space trader universe
 */
public class Region {
    protected String name;
    private int techLevel;
    private int xCoordinate;
    private int yCoordinate;
    private String description;
    private boolean hasBeenVisited = false;
    private static int numberOfRegions = 0;
    private int regionID;
    private Market regionMarket = new Market();
    private boolean winningRegion = false;

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
        description = name + ", a level " + techLevel
                + " civilization \r located at X:" + xCoordinate
                + " | Y:" + yCoordinate;

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
            File regionName = new File("src/resources/RegionNames.txt");
            regionNameSC = new Scanner(regionName);

            File className = new File("src/resources/RegionClassName.txt");
            classNameSC = new Scanner(className);
        } catch (FileNotFoundException e) {
            System.out.println("primary.Region's names "
                            + "files are missing from the resources folder");
        }
        assert regionNameSC != null;
        while (regionNameSC.hasNext()) {
            nameDictionary.add(regionNameSC.next());
        }
        assert classNameSC != null;
        while (classNameSC.hasNext()) {
            classDictionary.add(classNameSC.nextLine());
        }

        return nameDictionary.get(rand.nextInt(nameDictionary.size()))
                + classDictionary.get(rand.nextInt(classDictionary.size()));
    }


    public String getDescription() {
        return description;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public boolean hasBeenVisited() {
        return hasBeenVisited;
    }

    public void setBeenVisited(boolean v) {
        hasBeenVisited = v;
    }

    public String getName() {
        return name;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public Market getRegionMarket() {
        return regionMarket;
    }

    public boolean isWinningRegion() {
        return winningRegion;
    }

    public void setWinningRegion(boolean winningRegion) {
        this.winningRegion = winningRegion;
    }
}

