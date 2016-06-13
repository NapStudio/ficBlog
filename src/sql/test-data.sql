-- Inserts some test data into the tables
INSERT INTO usuario VALUES ('Christopher', 'Gomez', 'chrisgomez', '123456', 'chris');
INSERT INTO usuario VALUES ('SMITH', 'CLERK', 'SmithClerk', '123456', 'clerk');
INSERT INTO usuario VALUES ('ALLEN', 'SALESMAN', 'ALLENSALESMAN', '123456', 'ALLEN');
INSERT INTO usuario VALUES ('WARD', 'Gonzalez', 'wardy', '123456', 'Waard');



INSERT INTO blog VALUES (7369, 'Spring', '2015-05-09', 'SmithClerk');
INSERT INTO blog VALUES (7499, 'Java', '2015-06-09', 'chrisgomez');
INSERT INTO blog VALUES (7521, 'Asus', '2015-08-19', 'ALLENSALESMAN');
INSERT INTO blog VALUES (7566, 'Agua', '2016-03-04', 'ALLENSALESMAN');
INSERT INTO blog VALUES (7782, 'Portatiles', '2016-04-09', 'wardy');
INSERT INTO blog VALUES (7844, 'UDC', '2016-06-09', 'SmithClerk');



INSERT INTO articulo VALUES (230, 'UDC universidad', '2015-02-09', 'La universidad en la UDC ...',TRUE, 7844);
INSERT INTO articulo VALUES (240, 'UDC deportes', '2015-03-09', 'Los deportes en la UDC ...',TRUE, 7844);
INSERT INTO articulo VALUES (250, 'Java patron', '2015-06-09', 'patrones java ...',TRUE, 7499);
INSERT INTO articulo VALUES (260, 'Portatiles baratos', '2015-06-19', 'ortatiles barato ...', FALSE, 7782);
INSERT INTO articulo VALUES (270, 'Portatiles potentes', '2015-09-29', 'estos son los ...',FALSE, 7782);
INSERT INTO articulo VALUES (280, 'Spring framework', '2016-01-09', 'S ...',FALSE, 7369);


INSERT INTO enlace VALUES (6453, 'Agua mineral', '2015-08-29', 'S ...', 'foto',TRUE, 7566);
INSERT INTO enlace VALUES (5643, 'Agua con gas', '2015-09-24', 'S ...', 'web',TRUE, 7566);
INSERT INTO enlace VALUES (2345, 'Asus blablabla', '2016-01-4', 'S ...', 'video',FALSE, 7369);
INSERT INTO enlace VALUES (6454, 'Agua Cabreiroa', '2015-08-30', 'S ...', 'foto',TRUE, 7566);
INSERT INTO enlace VALUES (5644, 'Agua sin gas', '2015-09-27', 'S ...', 'web',TRUE, 7566);
INSERT INTO enlace VALUES (2344, 'Asus netbook', '2016-01-14', 'S ...', 'video',FALSE, 7369);
INSERT INTO enlace VALUES (6457, 'Agua mineral natural', '2015-09-09', 'S ...', 'foto',TRUE, 7566);
INSERT INTO enlace VALUES (5647, 'Agua con gaseosa', '2015-10-14', 'S ...', 'web',TRUE, 7566);
INSERT INTO enlace VALUES (2347, 'Asus portatil', '2016-02-4', 'S ...', 'video',FALSE, 7369);
