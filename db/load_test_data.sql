-- categories -----------------------------------------------------------------
INSERT INTO `category` VALUES
(1,'Leves','Soup',1,NULL),
(2,'Klasika','Traditional',2,NULL),
(3,'Taliani','Italian',3,NULL);

-- menu -----------------------------------------------------------------------
INSERT INTO `article` VALUES
(1,10,75,1,1,2,'Halusky','Nocks','Je to dobre!','Hm, it is sooo good!'),
(2,NULL,50,1,1,3,'spagety','spaghetti','talianska pomsta','the italian revenge'),
(3,NULL,60,1,1,3,'Makarony','Macaroni','vnutri fakt derave','Really hollow inside'),
(4,NULL,40,1,1,1,'Polivka','Soup','je grunt','is basis');

-- locations ------------------------------------------------------------------
INSERT INTO `delivery_location` VALUES
(1,'DHL','DHL',1),
(2,'SITA','SITA',1),
(3,'INBEV','INBEV',1),
(4,'RUZE','Bar - OC Ruže',1);

-- customers ------------------------------------------------------------------
INSERT INTO `customer` VALUES
(1,'Stojan','Jakotyc','stojan@tycka.hr','aBcDeFg',0,1,1),
(2,'Obivan','Kenobi','obivan.kenobi@jedi.uni','fOrCe',0,1,1);
