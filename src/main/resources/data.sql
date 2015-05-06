INSERT INTO map(id, name) VALUES (1, 'Mapa1');

INSERT INTO route(id, origin, destination, distance, map_id) VALUES (1,'A','B',10,1);
INSERT INTO route(id, origin, destination, distance, map_id) VALUES (2,'B','D',15,1);
INSERT INTO route(id, origin, destination, distance, map_id) VALUES (3,'A','C',20,1);
INSERT INTO route(id, origin, destination, distance, map_id) VALUES (4,'C','D',30,1);
INSERT INTO route(id, origin, destination, distance, map_id) VALUES (5,'B','E',50,1);
INSERT INTO route(id, origin, destination, distance, map_id) VALUES (6,'D','E',30,1);

CREATE SEQUENCE seq_route AS INTEGER
START WITH 7 INCREMENT BY 1;

CREATE SEQUENCE seq_map AS INTEGER
START WITH 3 INCREMENT BY 1;
