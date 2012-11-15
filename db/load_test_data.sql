-- categories -----------------------------------------------------------------
INSERT INTO `category` VALUES
(1,"Polévky","Soups",1,1),
(2,"Malé saláty","Small salads",2,1),
(3,"Velké saláty","Large salads",3,1),
(4,"Těstoviny","Pasta",4,1),
(5,"Rizota","Risotto",5,1),
(6,"Hotovky","Other",6,1),
(7,"Speciální nabídka","Special offer",7,1);

-- menu -----------------------------------------------------------------------
INSERT INTO article (Id,category_id,is_active,name_cz,name_en,description_cz,description_en,price,new_flag,daily_limit,version) VALUES 

(1,1,1,"Fazolová polévka s klobásou","Bean soup with sausage","","",20,1,NULL,1),
(2,1,1,"Kuřecí vývar","Chicken soup","mrkev, celer, nudle","carrot, celery root, noodles",20,0,NULL,1),
(3,1,1,"Špenátový krém","Spinach cream soup","špenát, smetana","špenát, smetana",20,0,NULL,1),
(67,1,0,"Česnečka","Garlic soup","","",20,0,NULL,1),
(73,1,0,"Zuppa di Pomodoro","Zuppa di Pomodoro","rajčatový krém ","tomato cream soup",20,0,NULL,1),
(36,1,0,"Bramboračka","Bramboračka","brambory, česnek","potatoes, garlic",20,0,NULL,1),
(37,1,0,"Čočková polévka s párkem","Lentil soup","mrkev, celer, párek","carrot, celery root, wienerwurst",20,0,NULL,1),
(38,1,0,"Zuppa Minestrone","Zuppa Minestrone","zeleninová polévka z lilku, cukety, pórku, rajčat, mrkvy, petrželu, celeru, a s bazalkou ","vegetable soup from eggplant, zucchini, leek, tomato, carrot, celery, basil",20,0,NULL,1),
(39,1,0,"Mexická fazolová","Mexican bean soup ","fazolová zjemněná smetanou","bean soup with cream ",20,0,NULL,1),
(4,2,1,"Malý šopský salát ","Small 'šopský' salad ","salát, okurky, paprika, rajčata, balkánsky sýr","lettuce, cucumber, pepper, tomato, Balkan cheese",20,0,NULL,1),
(5,3,1,"Salát di pollo","Salad di pollo","grilované kuřecí maso, rajčata, okurky, paprika, kukuřice, bylinkový dresink, salát","grilled chicken meat, tomatoes, cucumber, pepper, corn, herbal dressing, lettuce",80,0,NULL,1),
(6,3,1,"Salát di polo al fontina","Salad di polo al fontina","salát, okurky, rajčata, paprika, kousky kuřecího masa v teplé sýrové omáčce","lettuce, cucumber, tomato, peppers, chicken meat in warm cheese sauce",80,0,NULL,1),
(7,3,1,"Insalata barbeque","Insalata barbeque","salát, zelené fazolové lusky restované na cibulce, kuřecí kousky v omáčce barbeque","lettuce, green beans roasted with onions, chicken meat in barbeque sauce",80,0,NULL,1),
(43,3,0,"Řecký salát","Greek salad","salát, rajče, okurka, paprika, černé olivy, balkánský sýr  ","lettuce, tomato, cucumber, pepper, black olives, Balkan cheese",80,0,NULL,1),
(44,3,0,"Salát s mozzarellou","Salad with Mozzarela","salát, mozzarella, rukola, rajčata","lettuce, mozzarella, arugula, tomatoes",80,0,NULL,1),
(53,3,0,"Salát s mořským lososem","Salad with sea salmon","sea salmon, lettuce, tomatoes, cucumber, herbal oil dressing","mořský losos, salát, rajčata, okurky, bylinkový dresink z oleje",80,0,NULL,1),
(8,4,1,"Spaghetti Milanese","Spaghetti Milanese","šunka,česnek, rajčatová omáčka","ham, garlic, tomato souce",80,0,NULL,1),
(9,4,1,"Spaghetti Curry","Spaghetti Curry","kuřecí maso, žampióny, kari smetanová omáčka","chicken meat, mushroom, curry cream sauce",80,0,NULL,1),
(10,4,1,"Spaghetti alla carbonara","Spaghetti alla carbonara","slanina se smetanou a vejcem","chicken meat, mushroom, curry cream sauce",80,0,NULL,1),
(11,4,1,"Penne gorgonzola con spinaci","Penne gorgonzola con spinaci","gorgonzola, špenát, česnek ","gorgonzola, spinach, garlic ",80,1,NULL,1),
(12,4,1,"Penne con pollo e funghi","Penne con pollo e funghi","kuřecí maso, smetana, Niva, žampióny","chicken meat, cream, blue cheese, mushroom",80,0,NULL,1),
(13,4,1,"Penne con pollo e broccoli","Penne con pollo e broccoli","kuřecí maso, brokolice, smetana, česnek, parmazán","chicken meat, broccoli, cream, garlic, Parmesan",80,0,NULL,1),
(14,4,1,"Penne all’arrabbiata","Penne all’arrabbiata","pikantní tomatová omáčka","hot tomato sauce",80,0,NULL,1),
(15,4,1,"Fusilli Verdure","Fusilli Verdure","lilek, cuketa, rajčata, olivy","egg plant, zucchini, tomatoes, olives",80,0,NULL,1),
(16,4,1,"Fusilli prosciutto","Fusilli prosciutto","šunka, hrášek, smetanová omáčka","ham, peas, cream sauce",80,0,NULL,1),
(17,4,1,"Fusilli genovese","Fusilli genovese","kuřecí maso, smetana, kari, ananas","chicken meat, cream, curry, pineapple",80,0,NULL,1),
(18,4,1,"Fusilli palermita","Fusilli palermita","slanina, feferonky, rajčatová omáčka","bacon, chilli peppers, tomato sauce",80,0,NULL,1),
(19,4,1,"Gnocchi gorgonzola","Gnocchi gorgonzola","gorgonzola sýr, smetana, parmazán","gorgonzola cheese, cream, Parmesan",80,0,NULL,1),
(20,4,1,"Gnocchi pecorino","Gnocchi pecorino","slanina, bryndza, smetana","bacon, sheep cheese, cream  ",80,0,NULL,1),
(21,4,1,"Gnocchi al pollo","Gnocchi al pollo","kuřecí maso, pórek, smetana, kari","chicken meat, leek, cream, curry",80,0,NULL,1),
(22,4,1,"Gnocchi salmone","Gnocchi salmone","špenát, uzený losos, česnek, smetana","spinach, smoked salmon, garlic, cream",80,0,NULL,1),
(23,4,1,"Lasagne con broccoli","Lasagne con broccoli","kuřecí maso, brokolice, smetana, eidam, niva, parmazán","chicken meat, broccoli, cream, eidam, blue cheese, Parmesan",80,0,NULL,1),
(24,4,1,"Lasagne alla bolognese","Lasagne alla bolognese","pečené široké nudle prokládané masovým ragů se smetanou, a sýrem ","meat ragout, cream and cheese",80,0,NULL,1),
(42,4,0,"Spaghetti pomodori secchi","Spaghetti pomodori secchi","sušené rajčata, olivy, rukola, olivový olej","dried tomtoes, olives, arugula, olive oil",80,0,NULL,1),
(55,4,0,"Gnocchi pomodoro","Gnocchi pomodoro","šalotka, rajčata, česnek, rukola","shallot, tomato, garlic, arugula",80,0,NULL,1),
(56,4,0,"Tagliatelle funghi","Tagliatelle funghi","žampióny, bazalka, česnek, smetana","mushroom, basil, garlic, cream",80,0,NULL,1),
(57,4,0,"Spaghetti aglio olio","Spaghetti aglio olio","česnek, feferónky, bylinky, olej","garlic, chilli peppers, herbs, oil",80,0,NULL,1),
(58,4,0,"Lasagne funghi","Lasagne funghi","žampióny, vepřové maso, smetana, sýr","mushroom, pork meat, cream, cheese ",80,0,NULL,1),
(60,4,0,"Spaghetti alla bolognese","Spaghetti alla bolognese","masové ragů s bylinkami v tomatové omáčce","meat ragout with herbs in tomato sauce",80,0,NULL,1),
(61,4,0,"Gnocchi quattro formaggi","Gnocchi quattro formaggi","4 druhy sýra","4 kinds of cheese",80,0,NULL,1),
(62,4,0,"Gnocchi spinaci","Gnocchi spinaci","listový špenát, olivový olej, česnek, parmazán","leaf spinach, olive oil, garlic, Parmesan",80,0,NULL,1),
(71,4,0,"Lasagne agli spinaci","Lasagne agli spinaci","kuřecí maso, smetana, sýr, listový špenát, česnek","chicken meat, cream, cheese, leaf spinach, garlic",80,0,NULL,1),
(72,4,0,"Penne quattro formaggi","Penne quattro formaggi","gorgonzola, mozzarella, parmazán, eidam, smetana","gorgonzola, mozzarella, Parmesan, Edam cheese, cream",80,0,NULL,1),
(75,4,0,"Lasagne delizioso","Lasagne delizioso","krabí maso, hermelín, česnek, smetana, eidam","crab meat, hermelin, garlic, cream, Edam cheese",80,0,NULL,1),
(25,5,1,"Risotto alla Stroganoff","Risotto alla Stroganoff","kuřecí maso, kyselá okurka, žampióny","chicken meat, gherkin, mushroom ",80,1,NULL,1),
(26,5,1,"Risotto vegetariana","Risotto vegetariana","paprika, celer, cibule, kukuřice, pórek","peppers, celery, onion, corn, leak",80,0,NULL,1),
(27,5,1,"Risotto Saporito","Risotto Saporito","kuřecí maso, žampióny, smetana, bylinky, parmazán ","chicken meat, mushroom, cream, herbs, Parmesan",80,0,NULL,1),
(28,5,1,"Risotto con pollo e spinaci","Risotto con pollo e spinaci","kuřecí maso, listový špenát, smetana, česnek, eidam","chicken meat, leaf spinach, cream, garlic, Edam cheese",80,0,NULL,1),
(69,5,0,"Risotto salmone","Risotto salmone","špenát, uzený losos, česnek, smetana","spinach, smoked salmon, garlic, cream",80,0,NULL,1),
(70,5,0,"Risotto di maiale","Risotto di maiale","vepřové kousky, kukuřice, žampióny, pórek, červená paprika","pork meat pieces, corn, leak, red bell pepper",80,0,NULL,1),
(47,5,0,"Risotto Fantozzi","Risotto Fantozzi","kuřecí maso, kukuřice, baby karotka, brokolice, máslo","chicken meat, corn, baby carrots, broccoli, peas, butter",80,0,NULL,1),
(29,6,1,"Vepřový řízek s bramborovou kaší","Pork schnitzel with mashed potatoes","","",80,1,NULL,1),
(30,6,1,"Čevabčiči","Čevabčiči","mleté maso, opečený brambor","minced meat, grilled potatoes",80,1,NULL,1),
(31,6,1,"Grilované kuřecí prsíčka","Grilled chicken breasts","kuřecí maso, špenátové brambory","chicken meat, spinach potatoes",80,0,NULL,1),
(32,6,1,"Toskánská kotleta","Tuscan pork chop","vepřové maso, česneková omáčka s brambory","pork meat, garlic sauce with potatoes",80,0,NULL,1),
(33,6,1,"Špíz Bellini","Skewer Bellini","vepřové  a kuřecí maso, paprika, vařený brambor","pork and chicken meat, peppers, boiled potatoes",80,0,NULL,1),
(41,6,0,"Provensálske kuře","Chicken a la Provence","pečené stehno s lilkem, cuketou, rajčetem, olivy, provensálske byliny, rýže","chicken thigh roasted with eggplant, zucchini, tomato, olives, herbs de Provence",80,0,NULL,1),
(45,6,0,"Krkovice na grilu","Pork steak on the grill","krkovice, zelené lusky, šťouchané brambory","pork meat, green beans, mashed potatoes",80,0,NULL,1),
(48,6,0,"Kuře a la bažant","Chicken a la pheasant","kuřecí maso, slanina, víno, jalovec, bobkový list, rýže","chicken meat, bacon, wine, juniper, bay leave, rice",80,0,NULL,1),
(49,6,0,"Kuřecí steak","Chicken steak","kuřecí maso, rajčata, lilek, vařené brambory","chicken meat, tomatoes, eggplant, cooked potatoes",80,0,NULL,1),
(50,6,0,"Chilli con carne","Chilli con carne","hovězí nudličky s rýží, zdobené smetanou","beef with rice decorated with sour cream",80,0,NULL,1),
(51,6,0,"Mexická směs","Mexican mix","kuřecí maso, paprika, cibule, feferonka, slanina, šťouchaný brambor","chicken meat, pepper, onion, chilli peppers, bacon, mashed potatoes",80,0,NULL,1),
(52,6,0,"Kuřecí paličky","Chicken sticks","Kuřecí paličky v jogurtové marinádě s rýží","chicken sticks in yogurt marinade with rice",80,0,NULL,1),
(54,6,0,"Baked sea salmon a la Provence","Pečený mořský losos po provensálsku","mořský losos s provensálskými bylinkami, vařené brambory","sea salmon with herbs de Prevence, boiled potatoes",80,0,NULL,1),
(59,6,0,"Kuřecí stehno zapečené sýrem","Chicken thigh roasted with cheese","žampiónová omáčka, opečený brambor","mushroom sauce, baked potatoes",80,0,NULL,1),
(63,6,0,"Grilovaná zelenina s česnekovými tousty","Grilled vegetables with garlic toasts","grilovaná paprika, rajčata, cukety, lilek, cibule na olivovém oleji, parmazán ","grilled peppers, tomatoes, zucchini, eggplant, onion with Parmesan",80,0,NULL,1),
(64,6,0,"Krakonošův oheň","Krakonošův oheň","vepřové maso s jemně pikantní cibulovou omáčkou se smetanou, vařené brambory","pork meat with mild spicy onion sauce with cream, boiled potatoes",80,0,NULL,1),
(65,6,0,"Kuřecí steak s rajčaty a bazalkou","Chicken steak with tomatoes and basil","kuřecí steak s rajčaty, bazalkou a zapečeným sýrem, šťouchaný brambor","chicken steak with tomatoes and basil baked with cheese, mashed potatoes",80,0,NULL,1),
(66,6,0,"Kuřecí steak s gratinovou brokolicí","Chicken steak with gratinated broccoli","kuřecí steak s gratinovou brokolicí, vařený brambor","chicken steak with gratinated broccoli, boiled potatoes",80,0,NULL,1),
(68,6,0,"Vepřová krkovice s zelenými fazolkami","Pork steak with green beans","vepřové maso, zelené fazolky, šťouchaný brambor","pork meat, green beans, mashed potatoes",80,0,NULL,1),
(74,6,0,"Gurmánská pleskavice","Gurman pleskavice","steaky z mletého masa, grilované brambory","steak from minced meat, grilled potatoes",80,0,NULL,1),
(76,6,0,"Vepřový plátek na žampiónech","Pork file with mushroom","vepřové maso, žampióny, opečené brambory","pork meat, mushroom, roasted potatoes  ",80,0,NULL,1),
(46,7,0,"Pečená kachna","Roasted duck","1/4 kachna, červené zelí, bramborový knedlík","1/4 duck, red cabbage, potato dumpling",80,0,NULL,1)

ON DUPLICATE KEY UPDATE 
category_id=VALUES(category_id), is_active=VALUES(is_active), name_cz=VALUES(name_cz), name_en=VALUES(name_en), description_cz=VALUES(description_cz), 
description_en=VALUES(description_en), price=VALUES(price), new_flag=VALUES(new_flag), daily_limit=VALUES(daily_limit), version=VALUES(version);


-- locations ------------------------------------------------------------------
INSERT INTO `delivery_location` VALUES
(1,"DHL - Mailroom","DHL",1),
(2,"SITA","SITA",1),
(3,"INBEV","INBEV",1),
(4,"Osobní Odběr - OC Ruže","RUZE",1);

-- customers ------------------------------------------------------------------
INSERT INTO customer (Id,first_name,last_name, email, token, credit, version, is_active, default_delivery_location_id) VALUES 
(500,"Ladislav Nemeth (GMAIL)","","Ladislav.Nemeth@gmail.com",MD5("Ladislav Nemeth (GMAIL)xBucG5"),0,1,1,1),
(501,"Michal Galbavý","","mgalbavy@gmail.com",MD5("Michal GalbavýxBucG5"),0,1,1,1),
(502,"Lívia Beleščáková","","livia.belescakova@gmail.com",MD5("Lívia BeleščákováxBucG5"),0,1,1,1),
(503,"Lunch4you (user)","","lunch4you.cz@gmail.com",MD5("Lunch4you (user)xBucG5"),0,1,1,1),
(1,"Aitor Del Campo","","Aitor.DelCampo@dhl.com",MD5("Aitor Del CampoxBucG5"),0,1,0,1),
(2,"Aleksey Senko","","Aleksey.Senko@dhl.com",MD5("Aleksey SenkoxBucG5"),0,1,0,1),
(3,"Alena Losikova","","Alena.Losikova@dhl.com",MD5("Alena LosikovaxBucG5"),0,1,0,1),
(4,"Alena Palkova","","Alena.Palkova@dhl.com",MD5("Alena PalkovaxBucG5"),0,1,0,1),
(5,"Alena Urbanova","","Alena.Urbanova@dhl.com",MD5("Alena UrbanovaxBucG5"),0,1,0,1),
(6,"Ales Kolofik","","Ales.Kolofik@dhl.com",MD5("Ales KolofikxBucG5"),0,1,0,1),
(7,"Ales Kruzik","","Ales.Kruzik@dhl.com",MD5("Ales KruzikxBucG5"),0,1,0,1),
(8,"Alexandra Luzova","","Alexandra.Luzova@dhl.com",MD5("Alexandra LuzovaxBucG5"),0,1,0,1),
(9,"Alexandre Lucas Boels","","Alexandre.Lucas.Boels@dhl.com",MD5("Alexandre Lucas BoelsxBucG5"),0,1,0,1),
(10,"Aliaksandr Lapotka","","Aliaksandr.Lapotka@dhl.com",MD5("Aliaksandr LapotkaxBucG5"),0,1,0,1),
(11,"Antonin Tesar","","Antonin.Tesar@dhl.com",MD5("Antonin TesarxBucG5"),0,1,0,1),
(12,"Arjola Bimaj","","Arjola.Bimaj@dhl.com",MD5("Arjola BimajxBucG5"),0,1,0,1),
(13,"Artur Cuvasov","","Artur.Cuvasov@dhl.com",MD5("Artur CuvasovxBucG5"),0,1,0,1),
(14,"Ashwin Parameswaran","","Ashwin.Parameswaran@dhl.com",MD5("Ashwin ParameswaranxBucG5"),0,1,0,1),
(15,"Attila Labai","","Attila.Labai@dhl.com",MD5("Attila LabaixBucG5"),0,1,0,1),
(16,"Barbora Fabriciova","","Barbora.Fabriciova@dhl.com",MD5("Barbora FabriciovaxBucG5"),0,1,0,1),
(17,"Barbora Krausova","","Barbora.Krausova@dhl.com",MD5("Barbora KrausovaxBucG5"),0,1,0,1),
(18,"Barbora Lazanska","","Barbora.Lazanska@dhl.com",MD5("Barbora LazanskaxBucG5"),0,1,0,1),
(19,"Barbora Simkova","","Barbora.Simkova@dhl.com",MD5("Barbora SimkovaxBucG5"),0,1,0,1),
(20,"Carlo Migliavacca","","Carlo.Migliavacca@dhl.com",MD5("Carlo MigliavaccaxBucG5"),0,1,0,1),
(21,"Chad Kachmar","","Chad.Kachmar@dhl.com",MD5("Chad KachmarxBucG5"),0,1,0,1),
(22,"Damir Mitovski","","Damir.Mitovski@dhl.com",MD5("Damir MitovskixBucG5"),0,1,0,1),
(23,"Dana Kanova","","Dana.Kanova@dhl.com",MD5("Dana KanovaxBucG5"),0,1,0,1),
(24,"Daniela Novakova","","Daniela.Novakova@dhl.com",MD5("Daniela NovakovaxBucG5"),0,1,0,1),
(25,"David Kadlec","","David.Kadlec@dhl.com",MD5("David KadlecxBucG5"),0,1,0,1),
(26,"Denis Solomatine","","Denis.Solomatine@dhl.com",MD5("Denis SolomatinexBucG5"),0,1,0,1),
(27,"Dominika Kutna","","Dominika.Kutna@dhl.com",MD5("Dominika KutnaxBucG5"),0,1,0,1),
(28,"Eric Walker","","Eric.Walker@dhl.com",MD5("Eric WalkerxBucG5"),0,1,0,1),
(29,"Eva Radilova","","Eva.Radilova@dhl.com",MD5("Eva RadilovaxBucG5"),0,1,0,1),
(30,"Frantisek Barda","","Frantisek.Barda@dhl.com",MD5("Frantisek BardaxBucG5"),0,1,0,1),
(31,"Frantisek Kohout","","Frantisek.Kohout@dhl.com",MD5("Frantisek KohoutxBucG5"),0,1,0,1),
(32,"Frantisek Vlk","","Frantisek.Vlk@dhl.com",MD5("Frantisek VlkxBucG5"),0,1,0,1),
(33,"Gabriel Banas","","Gabriel.Banas@dhl.com",MD5("Gabriel BanasxBucG5"),0,1,0,1),
(34,"Gabriela Kudelka","","Gabriela.Kudelka@dhl.com",MD5("Gabriela KudelkaxBucG5"),0,1,0,1),
(35,"Gabriela Markova","","Gabriela.Markova@dhl.com",MD5("Gabriela MarkovaxBucG5"),0,1,0,1),
(36,"Henrieta Scholtzova","","Henrieta.Scholtzova@dhl.com",MD5("Henrieta ScholtzovaxBucG5"),0,1,0,1),
(37,"Houssam Husseini","","Houssam.Husseini@dhl.com",MD5("Houssam HusseinixBucG5"),0,1,0,1),
(38,"Ignacio Garcia","","Ignacio.Garcia@dhl.com",MD5("Ignacio GarciaxBucG5"),0,1,0,1),
(39,"Ilona Velicka","","Ilona.Velicka@dhl.com",MD5("Ilona VelickaxBucG5"),0,1,0,1),
(40,"Istvan Gyorfy","","Istvan.Gyorfy@dhl.com",MD5("Istvan GyorfyxBucG5"),0,1,0,1),
(41,"Jan Grofcik","","Jan.Grofcik@dhl.com",MD5("Jan GrofcikxBucG5"),0,1,0,1),
(42,"Jan Melichar","","Jan.Melichar@dhl.com",MD5("Jan MelicharxBucG5"),0,1,0,1),
(43,"Jan Pesek","","Jan.Pesek@dhl.com",MD5("Jan PesekxBucG5"),0,1,0,1),
(44,"Jan Pors","","Jan.Pors@dhl.com",MD5("Jan PorsxBucG5"),0,1,0,1),
(45,"Jan Snajdr","","Jan.Snajdr@dhl.com",MD5("Jan SnajdrxBucG5"),0,1,0,1),
(46,"Jan Sulek","","Jan.Sulek@dhl.com",MD5("Jan SulekxBucG5"),0,1,0,1),
(47,"Jan Terbl","","Jan.Terbl@dhl.com",MD5("Jan TerblxBucG5"),0,1,0,1),
(48,"Jan Tresnak","","Jan.Tresnak@dhl.com",MD5("Jan TresnakxBucG5"),0,1,0,1),
(49,"Jan Vrubel","","Jan.Vrubel@dhl.com",MD5("Jan VrubelxBucG5"),0,1,0,1),
(50,"Jana Cechova","","Jana.Cechova@dhl.com",MD5("Jana CechovaxBucG5"),0,1,0,1),
(51,"Jana Kapounova","","Jana.Kapounova@dhl.com",MD5("Jana KapounovaxBucG5"),0,1,0,1),
(52,"Jaroslav Kyncl","","Jaroslav.Kyncl@dhl.com",MD5("Jaroslav KynclxBucG5"),0,1,0,1),
(53,"Jiri Cizek","","Jiri.Cizek@dhl.com",MD5("Jiri CizekxBucG5"),0,1,0,1),
(54,"Jiri Nesvadba","","Jiri.Nesvadba@dhl.com",MD5("Jiri NesvadbaxBucG5"),0,1,0,1),
(55,"Juraj Toth","","Juraj.Toth@dhl.com",MD5("Juraj TothxBucG5"),0,1,0,1),
(56,"Kamil Jurczyk","","Kamil.Jurczyk@dhl.com",MD5("Kamil JurczykxBucG5"),0,1,0,1),
(57,"Kamila Hlobilova","","Kamila.Hlobilova@dhl.com",MD5("Kamila HlobilovaxBucG5"),0,1,0,1),
(58,"Kamila Strnadova","","Kamila.Strnadova@dhl.com",MD5("Kamila StrnadovaxBucG5"),0,1,0,1),
(59,"Karl Anthony Rosie","","Karl.Rosie@dhl.com",MD5("Karl Anthony RosiexBucG5"),0,1,0,1),
(60,"Katerina Soukupova Schmiedova","","Katerina.SoukupovaSchmiedova@dhl.com",MD5("Katerina Soukupova SchmiedovaxBucG5"),0,1,0,1),
(61,"Katerina Stalzerova","","Katerina.Stalzerova@dhl.com",MD5("Katerina StalzerovaxBucG5"),0,1,0,1),
(62,"Katerina Suchankova","","Katerina.Suchankova@dhl.com",MD5("Katerina SuchankovaxBucG5"),0,1,0,1),
(63,"Kees van den Munckhof","","Kees.vandenMunckhof@dhl.com",MD5("Kees van den MunckhofxBucG5"),0,1,0,1),
(64,"Kevin Dorekens","","Kevin.Dorekens@dhl.com",MD5("Kevin DorekensxBucG5"),0,1,0,1),
(65,"Kunal Bagarhatta","","Kunal.Bagarhatta@dhl.com",MD5("Kunal BagarhattaxBucG5"),0,1,0,1),
(66,"Ladislav Nemeth","","Ladislav.Nemeth@dhl.com",MD5("Ladislav NemethxBucG5"),0,1,0,1),
(67,"Ladislav Tomasek","","Ladislav.Tomasek@dhl.com",MD5("Ladislav TomasekxBucG5"),0,1,0,1),
(68,"Lars Lischer","","Lars.Lischer@dhl.com",MD5("Lars LischerxBucG5"),0,1,0,1),
(69,"Lenka Vysohlidova","","Lenka.Vysohlidova@dhl.com",MD5("Lenka VysohlidovaxBucG5"),0,1,0,1),
(70,"Libor Pilny","","Libor.Pilny@dhl.com",MD5("Libor PilnyxBucG5"),0,1,0,1),
(71,"Lubos Pusty","","Lubos.Pusty@dhl.com",MD5("Lubos PustyxBucG5"),0,1,0,1),
(72,"Lucia Michalcikova","","Lucia.Michalcikova@dhl.com",MD5("Lucia MichalcikovaxBucG5"),0,1,0,1),
(73,"Lucie Benesova","","Lucie.Benesova@dhl.com",MD5("Lucie BenesovaxBucG5"),0,1,0,1),
(74,"Lucie Moravkova","","Lucie.Moravkova@dhl.com",MD5("Lucie MoravkovaxBucG5"),0,1,0,1),
(75,"Lucie Suchankova","","Lucie.Suchankova@dhl.com",MD5("Lucie SuchankovaxBucG5"),0,1,0,1),
(76,"Lucie Tousova","","Lucie.Tousova@dhl.com",MD5("Lucie TousovaxBucG5"),0,1,0,1),
(77,"Lucio Buffalmano","","Lucio.Buffalmano@dhl.com",MD5("Lucio BuffalmanoxBucG5"),0,1,0,1),
(78,"Ludek Slegr","","Ludek.Slegr@dhl.com",MD5("Ludek SlegrxBucG5"),0,1,0,1),
(79,"Lukas Charouzek","","Lukas.Charouzek@dhl.com",MD5("Lukas CharouzekxBucG5"),0,1,0,1),
(80,"Lukas Lada","","Lukas.Lada@dhl.com",MD5("Lukas LadaxBucG5"),0,1,0,1),
(81,"Marcel Klik","","Marcel.Klik@dhl.com",MD5("Marcel KlikxBucG5"),0,1,0,1),
(82,"Marcel Rychly","","Marcel.Rychly@dhl.com",MD5("Marcel RychlyxBucG5"),0,1,0,1),
(83,"Marco Benfatto","","Marco.Benfatto@dhl.com",MD5("Marco BenfattoxBucG5"),0,1,0,1),
(84,"Marek Kocian(CO)","","M.Kocian@dhl.com",MD5("Marek Kocian(CO)xBucG5"),0,1,0,1),
(85,"Marek Sling","","Marek.Sling@dhl.com",MD5("Marek SlingxBucG5"),0,1,0,1),
(86,"Mark Newson","","Mark.Newson@dhl.com",MD5("Mark NewsonxBucG5"),0,1,0,1),
(87,"Martin Abrman","","Martin.Abrman@dhl.com",MD5("Martin AbrmanxBucG5"),0,1,0,1),
(88,"Martin Danda","","Martin.Danda@dhl.com",MD5("Martin DandaxBucG5"),0,1,0,1),
(89,"Martin Doskocil","","Martin.Doskocil@dhl.com",MD5("Martin DoskocilxBucG5"),0,1,0,1),
(90,"Martin Hokr","","Martin.Hokr@dhl.com",MD5("Martin HokrxBucG5"),0,1,0,1),
(91,"Martin Pelikan","","Martin.Pelikan@dhl.com",MD5("Martin PelikanxBucG5"),0,1,0,1),
(92,"Martin Pesta","","Martin.Pesta@dhl.com",MD5("Martin PestaxBucG5"),0,1,0,1),
(93,"Martin Pinker","","Martin.Pinker@dhl.com",MD5("Martin PinkerxBucG5"),0,1,0,1),
(94,"Martin Supa","","Martin.Supa@dhl.com",MD5("Martin SupaxBucG5"),0,1,0,1),
(95,"Martin Vasek","","Martin.Vasek@dhl.com",MD5("Martin VasekxBucG5"),0,1,0,1),
(96,"Martina Bystrzonovska","","Martina.Bystrzonovska@dhl.com",MD5("Martina BystrzonovskaxBucG5"),0,1,0,1),
(97,"Martina Horackova","","Martina.Horackova@dhl.com",MD5("Martina HorackovaxBucG5"),0,1,0,1),
(98,"Martina Jandova","","Martina.Jandova@dhl.com",MD5("Martina JandovaxBucG5"),0,1,0,1),
(99,"Martina Labaiova","","Martina.Labaiova@dhl.com",MD5("Martina LabaiovaxBucG5"),0,1,0,1),
(100,"Matt Walker","","Matt.Walker@dhl.com",MD5("Matt WalkerxBucG5"),0,1,0,1),
(101,"Michaela Sleichrtova","","Michaela.Sleichrtova@dhl.com",MD5("Michaela SleichrtovaxBucG5"),0,1,0,1),
(102,"Mihai Anton","","Mihai.Anton@dhl.com",MD5("Mihai AntonxBucG5"),0,1,0,1),
(103,"Milan Varkocek","","Milan.Varkocek@dhl.com",MD5("Milan VarkocekxBucG5"),0,1,0,1),
(104,"Miriam Veselova","","Miriam.Veselova@dhl.com",MD5("Miriam VeselovaxBucG5"),0,1,0,1),
(105,"Miroslav Cechmanek","","Miroslav.Cechmanek@dhl.com",MD5("Miroslav CechmanekxBucG5"),0,1,0,1),
(106,"Miroslav Tichy","","Miroslav.Tichy@dhl.com",MD5("Miroslav TichyxBucG5"),0,1,0,1),
(107,"Nihan Ozpinar","","Nihan.Ozpinar@dhl.com",MD5("Nihan OzpinarxBucG5"),0,1,0,1),
(108,"Nilanjana Roy","","Nilanjana.Roy@dhl.com",MD5("Nilanjana RoyxBucG5"),0,1,0,1),
(109,"Oleg Lipatov","","Oleg.Lipatov@dhl.com",MD5("Oleg LipatovxBucG5"),0,1,0,1),
(110,"Oleksiy Dvoryadkin","","Oleksiy.Dvoryadkin@dhl.com",MD5("Oleksiy DvoryadkinxBucG5"),0,1,0,1),
(111,"Olga Novotna","","Olga.Novotna@dhl.com",MD5("Olga NovotnaxBucG5"),0,1,0,1),
(112,"Oscar Tobio","","Oscar.Tobio@dhl.com",MD5("Oscar TobioxBucG5"),0,1,0,1),
(113,"Paolo Fornaroli","","Paolo.Fornaroli@dhl.com",MD5("Paolo FornarolixBucG5"),0,1,0,1),
(114,"Paul Fuschino","","Paul.Fuschino@dhl.com",MD5("Paul FuschinoxBucG5"),0,1,0,1),
(115,"Pavel Fremunt","","Pavel.Fremunt@dhl.com",MD5("Pavel FremuntxBucG5"),0,1,0,1),
(116,"Pavel Havlas","","Pavel.Havlas@dhl.com",MD5("Pavel HavlasxBucG5"),0,1,0,1),
(117,"Pavel Horak(CM)","","Pavel.Horak.CM@dhl.com",MD5("Pavel Horak(CM)xBucG5"),0,1,0,1),
(118,"Pavla Zidkova","","Pavla.Zidkova@dhl.com",MD5("Pavla ZidkovaxBucG5"),0,1,0,1),
(119,"Pedro Garrido","","Pedro.Garrido@dhl.com",MD5("Pedro GarridoxBucG5"),0,1,0,1),
(120,"Peter Torda","","Peter.Torda@dhl.com",MD5("Peter TordaxBucG5"),0,1,0,1),
(121,"Petr Babicka","","Petr.Babicka@dhl.com",MD5("Petr BabickaxBucG5"),0,1,0,1),
(122,"Petr Dobes","","Petr.Dobes@dhl.com",MD5("Petr DobesxBucG5"),0,1,0,1),
(123,"Petr Hojzak","","Petr.Hojzak@dhl.com",MD5("Petr HojzakxBucG5"),0,1,0,1),
(124,"Petr Svihalek","","Petr.Svihalek@dhl.com",MD5("Petr SvihalekxBucG5"),0,1,0,1),
(125,"Petr Vesely","","Petr.Vesely@dhl.com",MD5("Petr VeselyxBucG5"),0,1,0,1),
(126,"Petr Zan","","Petr.Zan@dhl.com",MD5("Petr ZanxBucG5"),0,1,0,1),
(127,"Petr Zeman","","Petr.Zeman@dhl.com",MD5("Petr ZemanxBucG5"),0,1,0,1),
(128,"Petra Kaletova","","Petra.Kaletova@dhl.com",MD5("Petra KaletovaxBucG5"),0,1,0,1),
(129,"Petra Lachoutova","","Petra.Lachoutova@dhl.com",MD5("Petra LachoutovaxBucG5"),0,1,0,1),
(130,"Petra Streicher","","Petra.Streicher@dhl.com",MD5("Petra StreicherxBucG5"),0,1,0,1),
(131,"Prakash Sadasivan","","Prakash.Sadasivan@dhl.com",MD5("Prakash SadasivanxBucG5"),0,1,0,1),
(132,"Pramod Dasan (DHL GB)","","Pramod.Dasan@dhl.com",MD5("Pramod Dasan (DHL GB)xBucG5"),0,1,0,1),
(133,"Radek Opat","","Radek.Opat@dhl.com",MD5("Radek OpatxBucG5"),0,1,0,1),
(134,"Radim Kos","","Radim.Kos@dhl.com",MD5("Radim KosxBucG5"),0,1,0,1),
(135,"Radka Belohradova","","Radka.Belohradova@dhl.com",MD5("Radka BelohradovaxBucG5"),0,1,0,1),
(136,"Rakhi Chandrasekharan","","Rakhi.Chandrasekharan@dhl.com",MD5("Rakhi ChandrasekharanxBucG5"),0,1,0,1),
(137,"Ramon Rebolledo","","Ramon.Rebolledo@dhl.com",MD5("Ramon RebolledoxBucG5"),0,1,0,1),
(138,"Riccardo Sedini","","Riccardo.Sedini@dhl.com",MD5("Riccardo SedinixBucG5"),0,1,0,1),
(139,"Richard Samanek","","Robert.Pelikan@dhl.com",MD5("Richard SamanekxBucG5"),0,1,0,1),
(140,"Robert Kavan","","Robert.Kavan@dhl.com",MD5("Robert KavanxBucG5"),0,1,0,1),
(141,"Robert Pelikan","","Robert.Pelikan@dhl.com",MD5("Robert PelikanxBucG5"),0,1,0,1),
(142,"Roman Antos","","Roman.Antos@dhl.com",MD5("Roman AntosxBucG5"),0,1,0,1),
(143,"Roman Svec","","Roman.Svec@dhl.com",MD5("Roman SvecxBucG5"),0,1,0,1),
(144,"Roman Vane","","Roman.Vane@dhl.com",MD5("Roman VanexBucG5"),0,1,0,1),
(145,"Rudolf Macejka","","Rudolf.Macejka@dhl.com",MD5("Rudolf MacejkaxBucG5"),0,1,0,1),
(146,"Saad.Omer","","Saad.Omer@dhl.com",MD5("Saad.OmerxBucG5"),0,1,0,1),
(147,"Samuel Huba","","Samuel.Huba@dhl.com",MD5("Samuel HubaxBucG5"),0,1,0,1),
(148,"Saps Patel (DHL UK)","","saps.patel@dhl.com",MD5("Saps Patel (DHL UK)xBucG5"),0,1,0,1),
(149,"Shweta Gupta (DHL DE)","","Shweta.Gupta@dhl.com",MD5("Shweta Gupta (DHL DE)xBucG5"),0,1,0,1),
(150,"Slavka Horakova","","Slavka.Horakova@dhl.com",MD5("Slavka HorakovaxBucG5"),0,1,0,1),
(151,"Stepan Vlasak","","Stepan.Vlasak@dhl.com",MD5("Stepan VlasakxBucG5"),0,1,0,1),
(152,"Steven Williams","","steven.s.williams@dhl.com",MD5("Steven WilliamsxBucG5"),0,1,0,1),
(153,"Sujith Gopalakrishnan","","Sujith.Gopalakrishnan@dhl.com",MD5("Sujith GopalakrishnanxBucG5"),0,1,0,1),
(154,"Tereza Reichlova","","Tereza.Reichlova@dhl.com",MD5("Tereza ReichlovaxBucG5"),0,1,0,1),
(155,"Terezia Elias Gerhardtova","","Terezia.EliasGerhardtova@dhl.com",MD5("Terezia Elias GerhardtovaxBucG5"),0,1,0,1),
(156,"Thorsten Maeding","","Thorsten.Maeding@dhl.com",MD5("Thorsten MaedingxBucG5"),0,1,0,1),
(157,"Tibor Meinczinger","","Tibor.Meinczinger@dhl.com",MD5("Tibor MeinczingerxBucG5"),0,1,0,1),
(158,"Tomas Cihelka","","Tomas.Cihelka@dhl.com",MD5("Tomas CihelkaxBucG5"),0,1,0,1),
(159,"Tomas Denemark","","Tomas.Denemark@dhl.com",MD5("Tomas DenemarkxBucG5"),0,1,0,1),
(160,"Tomas Hampl","","Tomas.Hampl@dhl.com",MD5("Tomas HamplxBucG5"),0,1,0,1),
(161,"Tomas Loukotka","","Tomas.Loukotka@dhl.com",MD5("Tomas LoukotkaxBucG5"),0,1,0,1),
(162,"Umar Khan","","Umar.Khan@dhl.com",MD5("Umar KhanxBucG5"),0,1,0,1),
(163,"Vaclav Korolus","","Vaclav.Korolus@dhl.com",MD5("Vaclav KorolusxBucG5"),0,1,0,1),
(164,"Valeria Kabatova","","Valeria.Kabatova@dhl.com",MD5("Valeria KabatovaxBucG5"),0,1,0,1),
(165,"Vamsi Nallacheruvu","","Vamsi.Nallacheruvu@dhl.com",MD5("Vamsi NallacheruvuxBucG5"),0,1,0,1),
(166,"Veronika Kasanderova","","Veronika.Kasanderova@dhl.com",MD5("Veronika KasanderovaxBucG5"),0,1,0,1),
(167,"Vijithkumar Vijayan","","Vijithkumar.Vijayan@dhl.com",MD5("Vijithkumar VijayanxBucG5"),0,1,0,1),
(168,"Viktor Jaros","","Viktor.Jaros@dhl.com",MD5("Viktor JarosxBucG5"),0,1,0,1),
(169,"Vit Curda","","Vit.Curda@dhl.com",MD5("Vit CurdaxBucG5"),0,1,0,1),
(170,"Vit Rocek","","Vit.Rocek@dhl.com",MD5("Vit RocekxBucG5"),0,1,0,1),
(171,"Vladimir Kobetic","","Vladimir.Kobetic@dhl.com",MD5("Vladimir KobeticxBucG5"),0,1,0,1),
(172,"Vojtech Kotous","","Vojtech.Kotous@dhl.com",MD5("Vojtech KotousxBucG5"),0,1,0,1),
(173,"Walid Tarhini","","Walid.Tarhini@dhl.com",MD5("Walid TarhinixBucG5"),0,1,0,1),
(174,"Youssef  Choueiry","","Youssef.Choueiry@dhl.com",MD5("Youssef  ChoueiryxBucG5"),0,1,0,1),
(175,"Zdenek Benes","","Zdenek.Benes@dhl.com",MD5("Zdenek BenesxBucG5"),0,1,0,1),
(176,"Zdenek Dvorak","","Zdenek.Dvorak@dhl.com",MD5("Zdenek DvorakxBucG5"),0,1,0,1),
(177,"Zdenek Layer","","Zdenek.Layer@dhl.com",MD5("Zdenek LayerxBucG5"),0,1,0,1),
(178,"Zdenek Spinka","","Zdenek.Spinka@dhl.com",MD5("Zdenek SpinkaxBucG5"),0,1,0,1),
(179,"Zlata Poseltova","","Zlata.Poseltova@dhl.com",MD5("Zlata PoseltovaxBucG5"),0,1,0,1),
(180,"Zuzana Gibova","","Zuzana.Gibova@dhl.com",MD5("Zuzana GibovaxBucG5"),0,1,0,1),
(181,"Ondrej Svarc","","Ondrej.Svarc@dhl.com",MD5("Ondrej SvarcxBucG5"),0,1,0,1),
(182,"Maria Hubova","","Maria.Hubova@dhl.com",MD5("Maria HubovaxBucG5"),0,1,0,1),
(183,"Katherine Araos","","katherine.araos@dhl.com",MD5("Katherine AraosxBucG5"),0,1,0,1),
(184,"Bohuslav Sys","","Bohuslav.Sys@dhl.com",MD5("Bohuslav SysxBucG5"),0,1,0,1),
(185,"Tomas Marek","","tomas.marek@aspectworks.com",MD5("Tomas MarekxBucG5"),0,1,0,1),
(186,"Pavel Mueller","","pavel.muller@aspectworks.com",MD5("Pavel MuellerxBucG5"),0,1,0,1),
(187,"Pavel Vlasaty","","pavel.vlasaty@aspectworks.com",MD5("Pavel VlasatyxBucG5"),0,1,0,1),
(188,"Karsten Jaehnigen","","karsten.jaehnigen@solid-servision.com",MD5("Karsten JaehnigenxBucG5"),0,1,0,1),
(189,"Dominik Simunek","","dominik.simunek@aspectworks.com",MD5("Dominik SimunekxBucG5"),0,1,0,1),
(191,"Milan Vajgl","","milan.vajgl@gmail.com",MD5("Milan VajglxBucG5"),0,1,0,1)

ON DUPLICATE KEY UPDATE 
first_name = VALUES(first_name), last_name = VALUES(last_name), email = VALUES(email), token = VALUES(token), credit = VALUES(credit),
version = VALUES(version), is_active = VALUES(is_active), default_delivery_location_id = VALUES(default_delivery_location_id);

-- orders ------------------------------------------------------------------
INSERT INTO `plain_order` VALUES
(1,1,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(2,1,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(3,1,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(4,1,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(5,1,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(6,1,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(7,4,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(8,4,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(9,4,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(10,4,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(11,4,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(12,35,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(13,35,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(14,35,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(15,35,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(16,36,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(17,36,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(18,36,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(19,36,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(20,36,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(21,38,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(22,38,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(23,38,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(24,38,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(25,38,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(26,40,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(27,40,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(28,40,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(29,40,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(30,40,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(31,40,"OPEN",1,1,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(32,40,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(33,50,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(34,50,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(35,50,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(36,50,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(37,50,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(38,55,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(39,55,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(40,55,"OPEN",1,3,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(41,55,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(42,56,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(43,57,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(44,58,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(45,59,"OPEN",1,2,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(46,60,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(47,61,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(48,62,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(49,63,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE),
(50,64,"OPEN",1,4,NOW() - INTERVAL (Floor(RAND() * 200)) MINUTE);


INSERT INTO `order_item` VALUES
(1,1,3,1,1),
(2,1,7,2,1),
(3,1,20,3,1),
(4,1,6,4,1),
(5,1,1,5,1),
(6,1,2,6,1),
(7,1,4,7,1),
(8,1,7,8,1),
(9,1,20,9,1),
(10,1,27,10,1),
(11,1,22,11,1),
(12,1,3,12,1),
(13,1,7,13,1),
(14,1,20,14,1),
(15,1,6,15,1),
(16,1,1,16,1),
(17,1,2,17,1),
(18,1,4,18,1),
(19,1,7,19,1),
(20,1,20,20,1),
(21,1,27,21,1),
(22,1,22,22,1),
(23,1,3,23,1),
(24,1,7,24,1),
(25,1,20,25,1),
(26,1,6,26,1),
(27,1,1,27,1),
(28,1,2,28,1),
(29,1,4,29,1),
(30,1,7,30,1),
(31,1,20,31,1),
(32,1,27,32,1),
(33,1,22,33,1),
(34,1,20,34,1),
(35,1,27,35,1),
(36,1,3,36,1),
(37,1,7,37,1),
(38,1,20,38,1),
(39,1,6,39,1),
(40,1,1,40,1),
(41,1,2,41,1),
(42,1,4,42,1),
(43,1,7,43,1),
(44,1,20,44,1),
(45,1,27,45,1),
(46,1,22,46,1),
(47,1,27,47,1),
(48,1,3,48,1),
(49,1,7,49,1),
(50,1,20,50,1);
