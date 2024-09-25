CREATE TABLE empresa (
                         id SERIAL PRIMARY KEY,
                         razaoSocial VARCHAR(64) NOT NULL,
                         cnpj VARCHAR(32) NOT NULL UNIQUE,
                         logradouro VARCHAR(64),
                         municipio VARCHAR(64),
                         numero VARCHAR(10),
                         complemento VARCHAR(64),
                         bairro VARCHAR(64),
                         cep VARCHAR(18),
                         telefone VARCHAR(32),
                         email VARCHAR(254) NOT NULL,
                         site VARCHAR(254),
                         usuario VARCHAR(20) NOT NULL UNIQUE,
                         senha VARCHAR(128),
                         createdAt TIMESTAMP NOT NULL,
                         updatedAt TIMESTAMP NOT NULL,
                         CONSTRAINT empresa_cnpj_uk UNIQUE (cnpj),
                         CONSTRAINT empresa_usuario_uk UNIQUE (usuario)
);

-- Atualizando a criação da tabela leilao
CREATE TABLE leilao (
                        id SERIAL PRIMARY KEY,
                        codigo INTEGER NOT NULL,
                        descricao VARCHAR(80) NOT NULL,
                        empresa_id INTEGER NOT NULL,  -- Chave estrangeira para a tabela empresa
                        inicioPrevisto TIMESTAMP NOT NULL,
                        createdAt TIMESTAMP NOT NULL,
                        updatedAt TIMESTAMP NOT NULL,
                        CONSTRAINT leilao_empresa_fk FOREIGN KEY (empresa_id) REFERENCES empresa (id)
);

CREATE TABLE lote (
                      id SERIAL PRIMARY KEY,
                      numeroLote INTEGER,
                      descricao VARCHAR(60) NOT NULL,
                      quantidade NUMERIC NOT NULL,
                      valorInicial NUMERIC,
                      unidade_id INTEGER NOT NULL,  -- Referência à tabela unidade
                      leilao_id INTEGER NOT NULL,
                      createdAt TIMESTAMP NOT NULL,
                      updatedAt TIMESTAMP NOT NULL,
                      CONSTRAINT lote_leilao_fk FOREIGN KEY (leilao_id) REFERENCES leilao (id),
                      CONSTRAINT lote_unidade_fk FOREIGN KEY (unidade_id) REFERENCES unidade (id));

CREATE TABLE comprador (
                           empresa_id INTEGER NOT NULL,
                           leilao_id INTEGER NOT NULL,
                           PRIMARY KEY (empresa_id, leilao_id),
                           CONSTRAINT comprador_empresa_fk FOREIGN KEY (empresa_id) REFERENCES empresa (id),
                           CONSTRAINT comprador_leilao_fk FOREIGN KEY (leilao_id) REFERENCES leilao (id)
);

CREATE TABLE unidade (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(128) NOT NULL,
                             createdAt TIMESTAMP NOT NULL,
                         updatedAt TIMESTAMP NOT NULL
);







