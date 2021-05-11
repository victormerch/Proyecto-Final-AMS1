--CONFIGURATION TABLES
DROP FINAL_PROYECT;
CREATE DATABASE FINAL_PROYECT;
USE FINAL_PROYECT;

CREATE TABLE RACE (
RACE_ID INT auto_increment,
RACE_NAME VARCHAR(20) NOT NULL,
HP INT (3),
STRENGTH INT (2),
DEF INT (2),
AGILITY INT (2),
VELOCITY INT (2),
CONSTRAINT pk_Race PRIMARY KEY (RACE_ID)
);

CREATE TABLE WEAPONS(
WEAPON_ID INT auto_increment,
WEAPON_NAME varchar(20) NOT NULL,
ADD_STR INT (2),
ADD_VEL INT (2),
WEAPON_RACE VARCHAR(200),
WEAPON_IMAGE_PATH VARCHAR(29),
CONSTRAINT pk_Weapons PRIMARY KEY (WEAPON_ID));

CREATE TABLE WARRIORS(
WARRIOR_ID INT auto_increment,
WARRIOR_NAME VARCHAR(20) NOT NULL,
RACE_ID INT,
WARRIOR_IMAGE_PATH VARCHAR(20),
CONSTRAINT pk_Warriors PRIMARY KEY (WARRIOR_ID),
CONSTRAINT fk_WarriorsRace FOREIGN KEY (RACE_ID)
REFERENCES RACE(RACE_ID));

CREATE TABLE PLAYERS(
PLAYER_ID int auto_increment,
PLAYER_NAME VARCHAR(20) NOT NULL,
CONSTRAINT pk_Players PRIMARY KEY (PLAYER_ID));

CREATE TABLE WEAPONS_AVAILABLE(
WARRIOR_ID INT,
WEAPON_ID INT,
CONSTRAINT fk_Weapons_Available_warrior FOREIGN KEY (WARRIOR_ID)
REFERENCES WARRIORS(WARRIOR_ID),
CONSTRAINT fk_Weapons_Available_weapons FOREIGN KEY (WEAPON_ID)
REFERENCES WEAPONS(WEAPON_ID));

CREATE TABLE RANKING(
PLAYER_ID INT,
TOTAL_POINTS INT(4),
WARRIOR_ID INT,
CONSTRAINT fk_Ranking_Player FOREIGN KEY(PLAYER_ID)
REFERENCES PLAYERS(PLAYER_ID),
CONSTRAINT fk_Ranking_Warrior FOREIGN KEY(WARRIOR_ID)
REFERENCES WARRIORS(WARRIOR_ID));

/*BATTLE TABLES*/

CREATE TABLE BATTLE (
BATTLE_ID INT auto_increment,
PLAYER_ID INT,
WARRIOR_ID INT,
WARRIOR_WEAPON_ID INT,
OPPONENT_ID INT,
OPPONENT_WEAPON_ID INT,
INJURIES_CAUSED INT (3),
INJURIES_SUFFERED INT (3),
BATTLE_POINTS INT (3),
CONSTRAINT pk_Battle PRIMARY KEY (BATTLE_ID),
CONSTRAINT fk_PlayerBattle FOREIGN KEY (PLAYER_ID)
REFERENCES PLAYERS(PLAYER_ID),
CONSTRAINT fk_WarriorsBattle FOREIGN KEY (WARRIOR_ID)
REFERENCES WARRIORS(WARRIOR_ID),
CONSTRAINT fk_OpponentBattle FOREIGN KEY (OPPONENT_ID)
REFERENCES WARRIORS(WARRIOR_ID),
CONSTRAINT fk_WarriorWeapon FOREIGN KEY (WARRIOR_WEAPON_ID)
REFERENCES WEAPONS_AVAILABLE(WEAPON_ID),
CONSTRAINT fk_OpponentWeapon FOREIGN KEY (OPPONENT_WEAPON_ID)
REFERENCES WEAPONS_AVAILABLE(WEAPON_ID));

--ALL RACES
insert into RACE(RACE_NAME, HP, STRENGTH, DEF, AGILITY, VELOCITY) values('Dwarf', 60, 6, 4, 5, 3);
insert into RACE(RACE_NAME, HP, STRENGTH, DEF, AGILITY, VELOCITY) values('Human', 50, 5, 3, 6, 5);
insert into RACE(RACE_NAME, HP, STRENGTH, DEF, AGILITY, VELOCITY) values('Elf', 40, 4, 2, 7, 7);


--ALL WEAPONS
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Dagger', 0, 3, 'Human, Elf', 'dagger.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Sword', 1, 1, 'Human, Elf, Dwarf', 'sword.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Axe', 3, 0, 'Human, Dwarf', 'axe.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Double Sword', 2, 2, 'Human, Elf', 'doubleSword.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Scimitar', 1, 2, 'Human, Elf', 'scimitar.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Bow', 1, 5, 'Elf', 'bow.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Katana', 2, 3, 'Human', 'katana.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Dirk', 0, 4, 'Human, Elf, Dwarf', 'dirk.png');
insert into WEAPONS(WEAPON_NAME, ADD_STR, ADD_VEL, WEAPON_RACE, WEAPON_IMAGE_PATH) values('Double Axe', 5, 0, 'Dwarf', 'doubleAxe.png');



insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Tyron', 1, 'tyron.png');
insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Gimli', 1, 'gimli.png');
insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Adkam', 1, 'adkam.png');

insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Luke', 2, 'luke.png');
insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Jack', 2, 'jack.png');
insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Michonne', 2, 'michonne.png');

insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Legolas', 3, 'legolas.png');
insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Sylvanas', 3, 'sylvanas.png');
insert into WARRIORS(WARRIOR_NAME, RACE_ID, WARRIOR_IMAGE_PATH) values('Link', 3, 'link.png');


