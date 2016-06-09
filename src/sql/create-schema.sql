-- All tables are dropped before creating them again

DROP TABLE IF EXISTS articulo;
DROP TABLE IF EXISTS enlace;
DROP TABLE IF EXISTS blog;
DROP TABLE IF EXISTS usuario;

-- Usuario

CREATE TABLE usuario (
	nombre_usuario VARCHAR(20),
	apellidos_usuario VARCHAR(20),
	login_usuario VARCHAR(20) UNIQUE,
	contraseña_usuario VARCHAR(20),
	nick_usuario VARCHAR(20),
	CONSTRAINT usuario_pk PRIMARY KEY (login_usuario)
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
	CONSTRAINT blog_fk FOREIGN KEY (usuario_blog) REFERENCES usuario(login_usuario)
);


-- Articulo
DROP SEQUENCE IF EXISTS articulo_id_seq;
CREATE SEQUENCE articulo_id_seq;

CREATE TABLE articulo (
	id_articulo BIGINT DEFAULT NEXTVAL('articulo_id_seq'),
	titulo_articulo VARCHAR(20),
	fecha_publicacion_articulo TIMESTAMP,
	texto_articulo VARCHAR(100),
	me_gusta_articulo BOOLEAN,
	blog_articulo BIGINT,
	CONSTRAINT articulo_pk PRIMARY KEY (id_articulo),
	CONSTRAINT articulo_fk FOREIGN KEY (blog_articulo) REFERENCES blog(id_blog)
);

-- Enlace
DROP SEQUENCE IF EXISTS enlace_id_seq;
CREATE SEQUENCE enlace_id_seq;

CREATE TABLE enlace (
	id_enlace BIGINT DEFAULT NEXTVAL('enlace_id_seq'),
	titulo_enlace VARCHAR(20),
	fecha_publicacion_enlace TIMESTAMP,
	url_enlace VARCHAR(20),
	tipo_contenido_enlace VARCHAR(20),
	me_gusta_enlace BOOLEAN,
	blog_enlace BIGINT,
	CONSTRAINT enlace_pk PRIMARY KEY (id_enlace),
	CONSTRAINT enlace_fk FOREIGN KEY (blog_enlace) REFERENCES blog(id_blog)
);



