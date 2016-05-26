-- Inserts some test data into the tables
INSERT INTO usuario VALUES ('Christopher', 'Gomez', 'chrisgomez', '123456', 'chris');
INSERT INTO usuario VALUES ('SMITH', 'CLERK', 'SmithClerk', '123456', 'clerk');
INSERT INTO usuario VALUES ('ALLEN', 'SALESMAN', 'ALLENSALESMAN', '123456', 'ALLEN');
INSERT INTO usuario VALUES ('WARD', 'Gonzalez', 'wardy', '123456', 'Waard');



INSERT INTO blog VALUES (7369, 'Spring', null, 'SmithClerk');
INSERT INTO blog VALUES (7499, 'Java', null, 'chrisgomez');
INSERT INTO blog VALUES (7521, 'Asus', null, 'ALLENSALESMAN');
INSERT INTO blog VALUES (7566, 'Agua', null, 'ALLENSALESMAN');
INSERT INTO blog VALUES (7782, 'Portatiles', null, 'wardy');
INSERT INTO blog VALUES (7844, 'UDC', null, 'SmithClerk');



INSERT INTO articulo VALUES (230, 'UDC universidad', null, 'La universidad en la UDC ...',TRUE, 7844);
INSERT INTO articulo VALUES (240, 'UDC deportes', null, 'Los deportes en la UDC ...',TRUE, 7844);
INSERT INTO articulo VALUES (250, 'Java patron', null, 'patrones java ...',TRUE, 7499);
INSERT INTO articulo VALUES (260, 'Portatiles baratos', null, 'ortatiles barato ...', FALSE, 7782);
INSERT INTO articulo VALUES (270, 'Portatiles potentes', null, 'estos son los ...',FALSE, 7782);
INSERT INTO articulo VALUES (280, 'Spring framework', null, 'S ...',FALSE, 7369);


INSERT INTO enlace VALUES (6453, 'Agua mineral', null, 'S ...', 'foto',TRUE, 7566);
INSERT INTO enlace VALUES (5643, 'Agua con gas', null, 'S ...', 'web',TRUE, 7566);
INSERT INTO enlace VALUES (2345, 'Asus blablabla', null, 'S ...', 'video',FALSE, 7369);
