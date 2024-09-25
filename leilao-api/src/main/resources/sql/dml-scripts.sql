-- Inserindo dados na tabela unidade
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('kg', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('litros', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('unidades', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('metros', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('toneladas', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('pacotes', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('caixas', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('galões', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('peças', NOW(), NOW());
INSERT INTO unidade (nome, createdAt, updatedAt) VALUES ('latas', NOW(), NOW());


-- Inserindo dados na tabela empresa
INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa A', '12345678900101', 'Rua A', 'Cidade A', '100', 'Apto 1', 'Bairro A', '00000-001', '123456789', 'emaila@empresa.com', 'www.empresaA.com', 'usuarioA', 'senhaA', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa B', '12345678900102', 'Rua B', 'Cidade B', '101', 'Apto 2', 'Bairro B', '00000-002', '123456789', 'emailb@empresa.com', 'www.empresaB.com', 'usuarioB', 'senhaB', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa C', '12345678900103', 'Rua C', 'Cidade C', '102', 'Apto 3', 'Bairro C', '00000-003', '123456789', 'emailc@empresa.com', 'www.empresaC.com', 'usuarioC', 'senhaC', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa D', '12345678900104', 'Rua D', 'Cidade D', '103', 'Apto 4', 'Bairro D', '00000-004', '123456789', 'emaild@empresa.com', 'www.empresaD.com', 'usuarioD', 'senhaD', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa E', '12345678900105', 'Rua E', 'Cidade E', '104', 'Apto 5', 'Bairro E', '00000-005', '123456789', 'emaile@empresa.com', 'www.empresaE.com', 'usuarioE', 'senhaE', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa F', '12345678900106', 'Rua F', 'Cidade F', '105', 'Apto 6', 'Bairro F', '00000-006', '123456789', 'emailf@empresa.com', 'www.empresaF.com', 'usuarioF', 'senhaF', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa G', '12345678900107', 'Rua G', 'Cidade G', '106', 'Apto 7', 'Bairro G', '00000-007', '123456789', 'emailg@empresa.com', 'www.empresaG.com', 'usuarioG', 'senhaG', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa H', '12345678900108', 'Rua H', 'Cidade H', '107', 'Apto 8', 'Bairro H', '00000-008', '123456789', 'emailh@empresa.com', 'www.empresaH.com', 'usuarioH', 'senhaH', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa I', '12345678900109', 'Rua I', 'Cidade I', '108', 'Apto 9', 'Bairro I', '00000-009', '123456789', 'emaili@empresa.com', 'www.empresaI.com', 'usuarioI', 'senhaI', NOW(), NOW());

INSERT INTO empresa (razaoSocial, cnpj, logradouro, municipio, numero, complemento, bairro, cep, telefone, email, site, usuario, senha, createdAt, updatedAt)
VALUES ('Empresa J', '12345678900110', 'Rua J', 'Cidade J', '109', 'Apto 10', 'Bairro J', '00000-010', '123456789', 'emailj@empresa.com', 'www.empresaJ.com', 'usuarioJ', 'senhaJ', NOW(), NOW());

-- Inserindo dados na tabela leilao com empresa_id como chave estrangeira
INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (101, 'Leilão de veículos', 1, '2024-10-01 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (102, 'Leilão de imóveis', 2, '2024-11-01 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (103, 'Leilão de máquinas', 3, '2024-12-01 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (104, 'Leilão de equipamentos', 4, '2024-10-15 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (105, 'Leilão de obras de arte', 5, '2024-11-15 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (106, 'Leilão de eletrônicos', 6, '2024-12-15 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (107, 'Leilão de móveis', 7, '2024-11-20 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (108, 'Leilão de imóveis comerciais', 8, '2024-12-20 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (109, 'Leilão de terrenos', 9, '2024-10-20 10:00:00', NOW(), NOW());

INSERT INTO leilao (codigo, descricao, empresa_id, inicioPrevisto, createdAt, updatedAt)
VALUES (110, 'Leilão de utensílios domésticos', 10, '2024-11-20 10:00:00', NOW(), NOW());

-- Inserindo dados na tabela lote (usando unidade_id e leilao_id válidos)
INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (1, 'Lote de carros', 10, 100000.00, 11, 1, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (2, 'Lote de caminhões', 5, 500000.00, 2, 2, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (3, 'Lote de imóveis', 3, 1000000.00, 3, 3, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (4, 'Lote de máquinas', 15, 750000.00, 4, 4, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (5, 'Lote de obras de arte', 20, 200000.00, 5, 5, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (6, 'Lote de eletrônicos', 50, 150000.00, 6, 6, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (7, 'Lote de móveis', 10, 50000.00, 7, 7, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (8, 'Lote de imóveis comerciais', 2, 2000000.00, 8, 8, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (9, 'Lote de terrenos', 5, 750000.00, 9, 9, NOW(), NOW());

INSERT INTO lote (numeroLote, descricao, quantidade, valorInicial, unidade_id, leilao_id, createdAt, updatedAt)
VALUES (10, 'Lote de utensílios domésticos', 100, 30000.00, 10, 10, NOW(), NOW());




-- Inserindo dados na tabela comprador (empresa_id e leilao_id válidos)
INSERT INTO comprador (empresa_id, leilao_id)
VALUES (1, 1);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (2, 1);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (3, 2);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (4, 2);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (5, 3);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (6, 4);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (7, 5);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (8, 6);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (9, 7);

INSERT INTO comprador (empresa_id, leilao_id)
VALUES (10, 8);
