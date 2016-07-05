-- All tables are dropped before creating them again

DROP TABLE IF EXISTS UserRoles;
DROP TABLE IF EXISTS megusta;
DROP TABLE IF EXISTS articulo;
DROP TABLE IF EXISTS enlace;
DROP TABLE IF EXISTS blog;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS megusta;

-- Usuario

CREATE TABLE usuario (
	nombre_usuario VARCHAR(20),
	apellidos_usuario VARCHAR(20),
	login_usuario VARCHAR(20) UNIQUE,
	contrasinal_usuario VARCHAR(20),
	nick_usuario VARCHAR(20),
	enabled BOOLEAN,
	CONSTRAINT usuario_pk PRIMARY KEY (login_usuario)
);

CREATE INDEX ON usuario(login_usuario);

CREATE TABLE UserRoles (
	userRoleId SERIAL,
	login_usuario VARCHAR(20),
	role VARCHAR(32),
	CONSTRAINT UserRoles_pk PRIMARY KEY (userRoleId),
	CONSTRAINT UserRoles_login_kf FOREIGN KEY (login_usuario) REFERENCES usuario(login_usuario)  ON DELETE CASCADE
);


-- Blog
DROP SEQUENCE IF EXISTS blog_id_seq;
CREATE SEQUENCE blog_id_seq;

CREATE TABLE blog (
	id_blog BIGINT DEFAULT NEXTVAL('blog_id_seq'),
	titulo_blog VARCHAR(20),
	fecha_creacion_blog TIMESTAMP NOT NULL,
	usuario_blog VARCHAR(20),
	CONSTRAINT blog_pk PRIMARY KEY (id_blog),
	CONSTRAINT blog_fk FOREIGN KEY (usuario_blog) REFERENCES usuario(login_usuario)  ON DELETE CASCADE
);


-- Articulo
DROP SEQUENCE IF EXISTS articulo_id_seq;
CREATE SEQUENCE articulo_id_seq;

CREATE TABLE articulo (
	id_articulo BIGINT DEFAULT NEXTVAL('articulo_id_seq'),
	titulo_articulo VARCHAR(100),
	fecha_publicacion_articulo TIMESTAMP,
	texto_articulo VARCHAR(1500),
	me_gusta_articulo INTEGER,
	blog_articulo BIGINT,
	CONSTRAINT articulo_pk PRIMARY KEY (id_articulo),
	CONSTRAINT articulo_fk FOREIGN KEY (blog_articulo) REFERENCES blog(id_blog)  ON DELETE CASCADE
);

-- Enlace
DROP SEQUENCE IF EXISTS enlace_id_seq;
CREATE SEQUENCE enlace_id_seq;

CREATE TABLE enlace (
	id_enlace BIGINT DEFAULT NEXTVAL('enlace_id_seq'),
	titulo_enlace VARCHAR(100),
	fecha_publicacion_enlace TIMESTAMP,
	url_enlace VARCHAR(100),
	tipo_contenido_enlace VARCHAR(20),
	me_gusta_enlace INTEGER,
	blog_enlace BIGINT,
	CONSTRAINT enlace_pk PRIMARY KEY (id_enlace),
	CONSTRAINT enlace_fk FOREIGN KEY (blog_enlace) REFERENCES blog(id_blog)  ON DELETE CASCADE
);

-- megusta
DROP SEQUENCE IF EXISTS megusta_id_seq;
CREATE SEQUENCE megusta_id_seq;

CREATE TABLE megusta (
	id_megusta BIGINT DEFAULT NEXTVAL('blog_id_seq'),
	id_enlace  BIGINT REFERENCES enlace(id_enlace)  ON DELETE CASCADE,
	id_articulo BIGINT REFERENCES articulo(id_articulo)  ON DELETE CASCADE,
	login_usuario VARCHAR(20) REFERENCES usuario(login_usuario)  ON DELETE CASCADE,
	CONSTRAINT megusta_pk PRIMARY KEY (id_megusta)
);

