-- Inserts some test data into the tables
INSERT INTO usuario VALUES ('Christopher', 'Gomez', 'chrisgomez', '12345678', 'chris', true);
INSERT INTO usuario VALUES ('SMITH', 'CLERK', 'SmithClerk', '12345678', 'clerk', true);
INSERT INTO usuario VALUES ('ALLEN', 'SALESMAN', 'ALLENSALESMAN', '12345678', 'ALLEN', true);
INSERT INTO usuario VALUES ('WARD', 'Gonzalez', 'wardys', '12345678', 'Waard', true);

INSERT INTO usuario VALUES ('asi', 'asi', 'asiasi', 'asi', 'asiasiasi', true);

INSERT INTO UserRoles (login_usuario, role) VALUES ('chrisgomez', 'ROLE_AUTHENTICATED');
INSERT INTO UserRoles (login_usuario, role) VALUES ('SmithClerk', 'ROLE_AUTHENTICATED');
INSERT INTO UserRoles (login_usuario, role) VALUES ('ALLENSALESMAN', 'ROLE_AUTHENTICATED');
INSERT INTO UserRoles (login_usuario, role) VALUES ('wardys', 'ROLE_AUTHENTICATED');

INSERT INTO UserRoles (login_usuario, role) VALUES ('asiasi', 'ROLE_AUTHENTICATED');

INSERT INTO blog VALUES (7369, 'Spring', '2015-05-09 14:00', 'SmithClerk');
INSERT INTO blog VALUES (7499, 'Java', '2015-06-09 14:00', 'chrisgomez');
INSERT INTO blog VALUES (7521, 'Asus', '2015-08-19 14:00', 'ALLENSALESMAN');
INSERT INTO blog VALUES (7566, 'Agua', '2016-03-04 14:00', 'ALLENSALESMAN');
INSERT INTO blog VALUES (7782, 'Portatiles', '2016-04-09 14:00', 'wardys');
INSERT INTO blog VALUES (7844, 'UDC', '2016-06-09 14:00', 'SmithClerk');
INSERT INTO blog VALUES (7812, 'FicBlog', '2016-06-29 14:00', 'SmithClerk');


INSERT INTO articulo VALUES (230, 'UDC universidad', '2015-02-09 14:00', 'La universidad en la UDC ...',0, 7844);
INSERT INTO articulo VALUES (240, 'UDC deportes', '2015-03-09 14:00', 'Los deportes en la UDC ...',0, 7844);
INSERT INTO articulo VALUES (250, 'Java patron', '2015-06-09 14:00', 'patrones java ...',0, 7499);
INSERT INTO articulo VALUES (260, 'Portatiles baratos', '2015-06-19 14:00', 'Portatiles barato ...', 0, 7782);
INSERT INTO articulo VALUES (270, 'Portatiles potentes', '2015-09-29 14:00', 'estos son los ...',0, 7782);
INSERT INTO articulo VALUES (280, 'Todo lo que está mal en el "hackeo" de la "Independence Day" original', '2016-06-09 14:00', '
Veinte años después de convertirse en todo un fenómeno, de arrebatar a Parque Jurásico el récord de recaudación (aunque le duró poco, apenas un año) y de elevar a status de estrella a Will Smith, Independence Day vuelve a las carteleras. La cinta de Roland Emmerich nos deparó momentos muy recordados (la destrucción de la Casa Blanca, el discurso del presidente...) pero ninguno como la "curiosa" manera en la que los humanos conseguíamos vencer a los aliens (Alerta Spoiler): un virus informático.

Por lo tanto lo que muchos deseamos ver en Independence Day: Contraataque, de nuevo dirigida por el ínclito Emmerich, es con qué nueva heroicidad "hacker" nos deleitará David Levinson, el personaje interpretado por Jeff Goldblum. Pero no adelantemos acontecimientos y volvamos a 1996 para analizar esa mítica escena con (poco) esmero y (mucha) sorna.',0, 7369);
INSERT INTO articulo VALUES (281, 'WD My Book, análisis: copias de seguridad efectivas y seguras de todos tus dispositivos', '2016-06-10 14:00', '
Tener copias de seguridad de nuestros archivos más importantes es algo básico en un mundo donde la mayoría de nuestro trabajo se encuentra en digital. Fotografías, música, vídeos o documentos, todo ello en un disco duro de hasta 8 TB capaz de conectarse a tus ordenadores y guardarlo todo de forma segura.

Hoy analizamos el WD My Book, un disco duro que puede alcanzar los 8 TB y presume no solamente de velocidad y diseño, sino también de comodidad a la hora de trabajar con el ordenador, bien sea un PC con Windows, bien sea un Mac.',0, 7812);
INSERT INTO articulo VALUES (282, 'Qué hace un tipo de sistemas cuando se le cae todo el sistema', '2016-01-09 14:00', '
¿Cómo es posible que empresas de esta magnitud puedan tener fallos así? Evidentemente todas ellas cuentan con intrincadas infraestructuras en las que temas como la redundancia y la alta disponibilidad son recurrentes. Y aún así, siempre hay algo que acaba fallando. ¿Cómo tratan de evitar esas caídas esas empresas, y cómo actúan cuando estas se producen a pesar de todo?',0, 7812);
INSERT INTO articulo VALUES (283, '¿Qué podrás ver en HDR de aquí a final de año? ', '2016-01-09 14:00', '
La tecnología HDR (High Dynamic Range) permite que los niveles de intensidad de luz de una imagen (estática o en movimiento) se perciban con un mayor grado de realismo. Viejo conocido de la fotografía, HDR llega a los televisores, lo que nos permite disfrutar de un contenido con más contraste, colores más brillantes y sombras con más detalle. No vamos a repetirte aquí todas sus ventajas, puesto que ya hemos hablado de la importancia del HDR a la hora de ver la televisión. ',0, 7812);
INSERT INTO articulo VALUES (284, '¿Por qué Google y otras tecnológicas están en contra Brexit y de qué tienen miedo?', '2016-01-09', '
Pese a ser una votación nacional, ya se sabía que el reciente referendum británico iba a tener consecuencias internacionales, sobre todo tras haber sido mayoritaria la opción de dejar la Unión Europea. Algo que por la magnitud no pasará ni hoy ni mañana, pero que afectará a muchos niveles entre ellos el hecho de ser la sede (o una de ellas) de las grandes empresas. Y en relación a ello estas mismas ya empiezan a manifestarse abiertamente, como lo ha hecho recientemente Google.',0, 7812);
INSERT INTO articulo VALUES (285, 'Lo que la ciencia nos dice sobre por qué tenemos alergias ', '2016-06-19 14:00', 'No fui precisamente original y no, no estoy especialmente orgulloso de ello. Al fin y al cabo, las pobres alergias son una de las cosas que más odio acumulan. Y es que las alergias son los inspectores de hacienda de la naturaleza, molestos pero imprescindibles. Veamos por qué. ',0, 7812);


INSERT INTO enlace VALUES (6453, 'Agua mineral', '2015-08-29 14:00', 'S ...', 'foto',0, 7566);
INSERT INTO enlace VALUES (5643, 'Agua con gas', '2015-09-24 14:00', 'S ...', 'web',0, 7566);
INSERT INTO enlace VALUES (2345, 'Asus blablabla', '2016-01-4 14:00', 'S ...', 'video',0, 7369);
INSERT INTO enlace VALUES (6454, 'Agua Cabreiroa', '2015-08-30 14:00', 'S ...', 'foto',0, 7566);
INSERT INTO enlace VALUES (5644, 'Agua sin gas', '2015-09-27 14:00', 'S ...', 'web',0, 7566);
INSERT INTO enlace VALUES (2344, 'Asus netbook', '2016-01-14 14:00', 'S ...', 'video',0, 7369);
INSERT INTO enlace VALUES (6457, 'Agua mineral natural', '2015-09-09 14:00', 'S ...', 'foto',0, 7566);
INSERT INTO enlace VALUES (5647, 'Agua con gaseosa', '2015-10-14 14:00', 'S ...', 'web',0, 7566);
INSERT INTO enlace VALUES (2347, 'Asus portatil', '2016-02-4 14:00', 'S ...', 'video',0, 7369);
INSERT INTO enlace VALUES (2348, 'hey ', '2016-06-06 14:00', 'http://hooooooooo.com/', 'audio',0, 7812);
INSERT INTO enlace VALUES (2349, 'hoo', '2016-06-14 14:00', 'http://heeeeeeeey.com/', 'audio',0, 7812);
INSERT INTO enlace VALUES (2387, 'Bury me with my money', '2016-06-11 14:00', 'http://burymewithmymoney.com/', 'video',0, 7812);
