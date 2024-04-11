# Proiektu-pertsonala Julen Galindo
 datu atzipeneko proiektua

Proiektu honen eginkizun nagusia Gainerako Api bat garatzea da, MongoDB datu-base batera sartzeko eta Rest Api bat web-aplikazio batean nola sar daitekeen ikasteko.
Hauek dira kontuan hartu beharreko erabilera orokorreko urratsak:

    - Swagger access: http://localhost:8081/swagger-ui/index.html
    - MongoDB connection String: mongodb://localhost
    - Database name: NBA
    - Collection names: Players and Conferences
    - Maven execution command (same directory as pom.xml): mvn spring-boot:run

# Dataset

Dataset honek hainbat motako atributoak dira. Ideia nagusia zen gure datubaseetan nolabaiteko sakontasunaz arduratzea, datu lauak biltzea saihestuz. Proiekturako aukeratutako dataseta Kagglen dagoen datu-multzo batetik aukeratu zen.

# Rest Serbitzua
Atseden-zerbitzuak egitura berezia du. Zerbitzu honen bidez erabiltzaileak eskaera mota desberdinak egin ditzake, mota horiek CRUD aplikazio batek egiten dituen berberak dira; sortu (POST), irakurri (GET), eguneratu (PUT) eta ezabatu (DELETE). Amaiera-puntu bakoitzak eskaera mota bat kudeatzen du, eta horrek esan nahi du, zein kasutan exekutatuko den, zerbitzuak eragiketa mota bat edo beste bat egingo duela. Eskaera egin ahal izateko, erabiltzaile-interfazea edo beste garapen-ingurune bat erabil daiteke, hala nola Imsomnia, Postman eta abar. Swagger-etik azken puntuetara joan, modeloak ikusi eta api doc, yaml formatuan. Hola geldituko litzateke.
![image](https://github.com/julengalin/proiektu-pertsonala/assets/114486026/aad586b1-112f-4cbe-88a0-29700793c3e1)

![image](https://github.com/julengalin/proiektu-pertsonala/assets/114486026/24cb259d-de56-4909-bdbf-c6d417141aed)

# MongoDB

SpringBoot aplikazio honek MongoDB datu-base baten eskaera sortzen du. Datu-basea sortzea nekezagoa da MongoDBren eta MongoDB Compassen edizio komunitarioa instalatzeko. Behin instalatuta, bezeroa ireki eta gure makinarekin konexioa sortzen dugu. Hemen urruneko konexioak sor ditzakezu zerbitzariekin, tokiko makinarekin eta baita MongoDB edo Cloudarekin ere.

Konexio-kate hori editatzeak eta doitzeak aukera emango dizu behar duzun lekuan konektatu ahal izateko. Kasu honetan, aplikazioa tokiko makinara konektatzeko konfiguratuta dago Localhost-en bidez. Mongodb:// localhost sartu eta datu-basea sortu dezakezu.

Ezkerraldean datu-baseak ikus daitezke, eta hor datu-basea eta datuak gordeko diren bilduma sortzen dira. Datu-basearen izenari NBA esaten zaio, eta bildumari Players. Datu-basea eta bilduma sortu ondoren, gure datuak inportatzen ditugu. Datuak JSON dokumentu bat dira. Mongo dokumentua inportatzeak dokumentu kopuru handia sortuko du. Dokumentu bakoitza jokalari bat da, eta jokalari bakoitzak bere ezaugarriak ditu:

     - @param playerName Jokalariaren izena String formatuan
     - @param lastName Jokalariaren abizena String formatuan
     - @param team Jokalariaren ekipoa String formatuan
     - @param conference Jokalariaren konferentzia String formatuan
     - @param gamesPlayed Jokalariaren jolastutako partidak String formatuan
     - Eta hainbat estatistika mixed, double eta int32 formatuetan.

# Javako proiektua

Java proiektua garatu da SpringBoot proiektu bat sortuz. Maven motako proiektu bat ere erabiltzen ari gara, dependentzia erosoak gehitu eta gure proiektuan sar ditzakegulako.

SpringBoot motako proiektuek klaseen eta ereduen egitura berezia dute. Proiektua bi pakete nagusitan banatuta dago: modeloa eta kontrolatzailea. Pakete horietatik kanpo bi klase gehiago ditugu. Primerako klasea SpringConfiguration da. Klase honekin aplikazioak konexio-katea lor dezake aplikazioaren propietateetatik eta MongoDB bezeroak aldi berean BSONDocumentetik JavaObjeturako bihurketa sor dezake. Eta bigarren klasea aplikazio-klase nagusia da. Honekin, aplikazioa hasi eta zerbitzu osoa zuzentzen da.

Pakete modeloaren barruan klase mota desberdinak aurki ditzakegu. Objektu-ereduak horietako bat dira. Eredu horiekin, aplikazioak kartografia bat sortzen du datuak egituratzeko eta MongoDBn prozesatu ahal izateko. Objektu mota batekin ari garenez lanean, non objektuak berak objektu bat duen bere baitan, azpiofiziala ere mapeatu behar dugu. Kasu honetan Player klase bat dugu. Behin modelo horiek JavaObjetuen bihurketa eraikitzen dutenean eta Mongo dokumentuak egin daitezkeenean. Swagger exekutatu eta jaitsi ondoren, eskemak ikus ditzakegu.

Objektuen klaseak aparte utzita, beste bi klase ditugu, errepositorioak. Errepositorio horietan datuak bildu eta kudeatu ditzakegu eragiketak egiteko. Interfaze bat dugu oinarrizko funtzioak adierazteko eta gero MongoDB errepositorio bat dugu. Errepositorio honek errepositorioaren interfazean adierazitako funtzioak inplementatzen ditu. Funtzio horiek dira gero arduradunak eskaera mota desberdinak sortzeko erabiliko dituen oinarrizko funtzionamendua.

