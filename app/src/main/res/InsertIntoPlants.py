import sqlite3 as sql

conn = sql.connect('plants.db')
c = conn.cursor()

keepGoing = 'y'

while keepGoing == 'y':
    NAME = input("Enter Plant Name: ")
    ANNUAL = input("Enter Annual (0-1): ")
    BIENNIAL = input("Enter Biennial (0-1): ")
    PERENNIAL = input("Enter Perennial (0-1): ")
    INDOOR = input("Enter Indoor (0-1): ")
    COLOR = input("Enter colors CHAR(255), separate colors with commas: ")
    MAINTENANCE = input("Enter Maintenance (1-3): ")
    WATERMAINTAIN = input("Enter Water Maintain CHAR(255): ")
    SOIL = input("Enter soil CHAR(100): ")
    FERTILIZER = input("Enter fertilizer CHAR(255): ")
    SUNMAINTAINSUM = input("Enter sun maintain summer CHAR(100): ")
    SUNMAINTAINWIN = input("Enter sun maintain winter CHAR(100): ")
    PHMAINTAIN = input("Enter PH Maintain CHAR(20) (ex: 6.7 to 7.1): ")
    PRUNING = input("Enter Pruning CHAR(255): ")
    FLOWERING = input("Enter Flowering (0-1): ")
    EDIBLE = input("Enter edible (0-1): ")
    POISON = input("Enter poisonous (0-1): ")
    TUBULAR = input("Enter tubular (0-1): ")
    SUCCULENT = input("Enter succulent (0-1): ")
    CACTUS = input("Enter cactus (0-1): ")
    TREE = input("Enter tree (0-1): ")
    LANDSCAPING = input("Enter landscaping (0-1): ")
    HARDINESSZONE = input("Enter Hardiness Zone CHAR(20) (ex: 10 to 12): ")

    item = (NAME, ANNUAL, BIENNIAL, PERENNIAL, INDOOR, COLOR ,MAINTENANCE, WATERMAINTAIN, SOIL, FERTILIZER,
            SUNMAINTAINSUM, SUNMAINTAINWIN, PHMAINTAIN, PRUNING, FLOWERING, EDIBLE, POISON , TUBULAR, SUCCULENT, CACTUS, TREE, LANDSCAPING, HARDINESSZONE)

    c.execute('INSERT INTO plants VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', item)
    conn.commit()

    c.execute('SELECT * FROM plants WHERE NAME=\'' + NAME + '\'')

    keepGoing = input("Enter Another Plant? (y/n)")




