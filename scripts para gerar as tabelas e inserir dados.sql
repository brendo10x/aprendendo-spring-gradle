CREATE TABLE "produto" (
    "id" serial NOT NULL,
    "nome" varchar(60) NOT NULL,
    "descricao" varchar(100) NOT NULL,
    "valor" DECIMAL(10,2) NOT NULL,
    "fornecedor" varchar(60),
    "estoque" integer NOT NULL,
    CONSTRAINT produto_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "user" (
    "username" varchar(45) NOT NULL,
    "password" varchar(60) NOT NULL,
    "enabled" BOOLEAN NOT NULL DEFAULT 'true',
    CONSTRAINT user_pk PRIMARY KEY ("username")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "user_role" (
    "user_role_id" integer NOT NULL,
    "username" varchar(45) NOT NULL,
    "role" varchar(45) NOT NULL,
    CONSTRAINT user_role_pk PRIMARY KEY ("user_role_id")
) WITH (
  OIDS=FALSE
);

ALTER TABLE "user_role" ADD CONSTRAINT "user_role_fk0" FOREIGN KEY ("username") REFERENCES "user"("username");


--Inserir usuário
INSERT INTO "user"(
            username, password, enabled)
    VALUES ('brendo','$2a$10$5R/vxO9U5x.UW5WLZIDoFu0RnD9PfSY64j486PaULXq.oUeRbXuTm',TRUE);
    
--Associar um perfil a um usuário
  INSERT INTO user_role(
            user_role_id, username, role)
    VALUES (1,'brendo','ROLE_ADMIN');