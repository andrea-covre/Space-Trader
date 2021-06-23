# Space Trader

## Overview
This project is a recreation of the popular game [Space Trader](https://en.wikipedia.org/wiki/Space_Trader_(Palm_OS)) as an exercise for version control best practices, object design, object-oriented programming methods, Agile and Iterative development and JavaFX programming.

<img src="figures/gameplay-demo.gif" alt="Gameplay DEMO by Andrea Covre" width="750"/>

<br>

## Game Dynamics

The goal of the game is to travel around different locations in space to buy/sell items and earn enough credits in order to be able to buy the winning item found at a random location.

### Attributes

When starting a new game, the player can select the difficulty (easy, medium or hard) which determines the starting amount of credits, skill points, and the outcomes of encounters with traders and bandits. 

<img src="figures/attributes.png" alt="Attributes selection by Andrea Covre" width="500"/>

Skill points can be assigned to the player's attributes/skills. The <em>Pilot</em> skill decreases the amount of fuel used, the <em>Fighter</em> skill increase the chances of fighting off a bandit or robbing a trader, the <em>Merchant</em> skill decreases the purchasing price of most items, and finally the <em>Engineer</em> skill decrease the cost of restoring ship's health points.

<img src="figures/player-summary.png" alt="Player summary by Andrea Covre" width="500"/>

<br>

### Travelling

<img src="figures/visited-location-example.png" alt="Map example of visited location by Andrea Covre" width="500"/>
<img src="figures/non-visited-location-example.png" alt="Map example of non visited location by Andrea Covre" width="500"/>
<img src="figures/map-example-1.png" alt="Map example 1 by Andrea Covre" width="500"/>
<img src="figures/map-example-2.png" alt="Map example 2 by Andrea Covre" width="500"/>

The game features multiple locations with randomly generated coordinates, names (through multiple dictionaries), and attributes. The player can travel to any of these locations by spending some ship's fuel based on the distance between the location the player is travelling to and the player's current position (fuel cost can be discounted based on the player's <em>Pilot</em> attribute). During each travel there is a chance of meeting a bandit or a trader on the way. The player can decide to either pay, flee or fight the bandit, or to rob, trade, negotiated  or ignore the trader. The outcomes of these action (success or failure) depend on the game difficulty and player attributes.

<img src="figures/bandit-example.png" alt="Bandit encounter example by Andrea Covre" width="500"/>
<img src="figures/trader-example.png" alt="Trader encounter example by Andrea Covre" width="500"/>

<br>

### Markets

Each location has a pseudo randomly generated market. Each market offers items with equal or lower "tech" level of the civilization at that location. Higher tech items are more expensive therefore it's easier to make a profit when sold. Moreover, markets also offer special items that are either player upgrades, fuel or the ability to restore ship's health points (more effective with a higher <em>Engineer</em> level). The price of the items is randomly determined within a preset range, and a discount is applied based on the player's <em>Merchant</em> attribute.

<img src="figures/market-example.png" alt="Market example by Andrea Covre" width="500"/>

<br>

### Ship

The ship has various attributes:
- current health points and maximum health points;
- current fuel amount and maximum fuel amount;
- attack value (determined by the player's <em>Fighter</em> skill);
- maximum cargo capacity;
- list of cargo items currently stored;
- maximum number of slots for upgrades;
- list of upgrades currently installed.

All this information can be seen at the bottom bar and at the market.

<br>

### Winning

The winning item spawns as a special item at a random location and only after the player has played for some time.

<img src="figures/winning-item.png" alt="Winning item on the market by Andrea Covre" width="500"/>

Purchasing this item will allow the player to win and complete the game, and will cause the game to reset to a fresh new game after the credits.

<img src="figures/winning-scene.png" alt="Winning scene by Andrea Covre" width="500"/>

<br>

## Team

### Andrea Covre's contributions

* **Map & Regions**
    * Designed and implemented:
        * the <em>region</em> class → [`Region.java`](src/primary/Region.java)
    * Designed, styled and wrote the scenes for:
        * the <em>map scene</em> → [`MapScene.java`](src/primary/scenes/MapScene.java)
        * the <em>region scene</em> → [`RegionScene.java`](src/primary/scenes/RegionScene.java)
        <br>
        including:
            * information panes
            * random generation and layout of regions (locations), and their names, descriptions and attributes
            * player travelling sequence and implementation  
            * bottom info bar with amount of credits available, players skills level and ship's information
    <br>
* **Markets & Items**
    * Designed and implemented:
        * the <em>market</em> class → [`Market.java`](src/primary/Market.java)
        * the <em>item</em> class → [`Item.java`](src/primary/Item.java)
    * Designed, styled and wrote the scenes for:
        * the <em>market scene</em> → [`MarketScene.java`](src/primary/scenes/MarketScene.java)
        <br>
        including:
            * random generation of items, and their names, descriptions and attributes
            * generation and adjustments of items prices based on players attributes, region's tech level and item's rarity
            * implementation of player's upgrades
            * items transfers from market to ship cargo and vice versa
            * upgrades transfers from market to player cargo and vice versa
            * bottom info bar with amount of credits available, players skills level and ship's information
            * winning item design and spawn 
    <br>
* **Ship**
    * Designed and implemented:
        * the <em>ship</em> class → [`Ship.java`](src/primary/Ship.java)
        including:
            * health points (HP)
            * fuel & cargo management

    <br>
* **Skill Selection**
    * Designed, styled and wrote the <em>skills selection scene</em> → [`SkillLevelSelectionScene.java`](src/primary/scenes/SkillLevelSelectionScene.java)
        <br>


### Developers

<img src="figures/developers-list.png" alt="Space Trader Developers List" width="500"/>

 <!-- ## Development -->
 
 <!-- need to remake demo gif in full screen -->