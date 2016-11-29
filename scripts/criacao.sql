DROP TABLE Pessoa CASCADE CONSTRAINTS;
DROP TABLE Motorista CASCADE CONSTRAINTS;
DROP TABLE Cliente CASCADE CONSTRAINTS;
DROP TABLE Funcionario CASCADE CONSTRAINTS;
DROP TABLE Cargo CASCADE CONSTRAINTS;
DROP TABLE Remuneracao CASCADE CONSTRAINTS;
DROP TABLE Falecido CASCADE CONSTRAINTS;
DROP TABLE CarroRepository CASCADE CONSTRAINTS;
DROP TABLE Transporta CASCADE CONSTRAINTS;
DROP TABLE Funeral CASCADE CONSTRAINTS;
DROP TABLE Contrata CASCADE CONSTRAINTS;
DROP TABLE Enterro CASCADE CONSTRAINTS;
DROP TABLE Cremacao CASCADE CONSTRAINTS;
DROP TABLE Produto CASCADE CONSTRAINTS;
DROP TABLE Urna CASCADE CONSTRAINTS;
DROP TABLE Caixao CASCADE CONSTRAINTS;
DROP TABLE Telefones CASCADE CONSTRAINTS;
DROP TABLE Convidado CASCADE CONSTRAINTS;

CREATE TABLE Pessoa (
    cpf VARCHAR2(11),
    prim_nome VARCHAR2(20),
    nome_familia VARCHAR2(20),
    data_nasc DATE,
    rg VARCHAR2(9),
    PRIMARY KEY (cpf)
);

CREATE TABLE Motorista (
    cpf_mot VARCHAR2(11),
    cnh VARCHAR2(11),
    PRIMARY KEY (cpf_mot),
    FOREIGN KEY (cpf_mot) REFERENCES Pessoa(cpf) ON DELETE CASCADE
);

CREATE TABLE Cliente (
    cpf_cliente VARCHAR2(11),
    PRIMARY KEY (cpf_cliente),
    FOREIGN KEY (cpf_cliente) REFERENCES Pessoa(cpf) ON DELETE CASCADE
);

CREATE TABLE Funcionario (
    cpf_func VARCHAR2(11),
    cpf_supv VARCHAR2(11), --supervisor
    PRIMARY KEY (cpf_func),
    FOREIGN KEY (cpf_func) REFERENCES Pessoa(cpf) ON DELETE CASCADE,
    FOREIGN KEY (cpf_supv) REFERENCES Funcionario(cpf_func) ON DELETE CASCADE
);

CREATE TABLE Remuneracao (
    salario DECIMAL(6, 2),
    funcao VARCHAR2(27),
    PRIMARY KEY (funcao)
);

CREATE TABLE Cargo (
    funcao VARCHAR2(27),
    cpf_func VARCHAR2(11),
    PRIMARY KEY (cpf_func),
    FOREIGN KEY (cpf_func) REFERENCES Funcionario(cpf_func) ON DELETE CASCADE,
    FOREIGN KEY (funcao) REFERENCES Remuneracao(funcao) ON DELETE CASCADE
);

CREATE TABLE Falecido (
    cpf_falec VARCHAR2(11),
    data_obito DATE,
    altura DECIMAL(3,2),
    largura DECIMAL(3,2),
    PRIMARY KEY (cpf_falec),
    FOREIGN KEY (cpf_falec) REFERENCES Pessoa(cpf) ON DELETE CASCADE
);

CREATE TABLE Carro (
    placa VARCHAR2(7),
    ano_fab DATE,
    fabricante VARCHAR2(20),
    modelo VARCHAR2(15),
    cor VARCHAR2(10),
    PRIMARY KEY (placa)
);

CREATE TABLE Transporta (
    data DATE,
    placa_carro VARCHAR2(7),
    cpf_mot VARCHAR2(11),
    cpf_falec VARCHAR2(11),
    PRIMARY KEY (data),
    FOREIGN KEY (placa_carro) REFERENCES Carro(placa) ON DELETE CASCADE,
    FOREIGN KEY (cpf_mot) REFERENCES Motorista(cpf_mot) ON DELETE CASCADE,
    FOREIGN KEY (cpf_falec) REFERENCES Falecido(cpf_falec) ON DELETE CASCADE
);

CREATE TABLE Funeral (
    id INTEGER,
    data DATE,
    PRIMARY KEY (id)
);

CREATE TABLE Contrata (
    cpf_cliente VARCHAR2(11),
    cpf_falec VARCHAR2(11),
    id_funeral INTEGER,
    data DATE,
    PRIMARY KEY (id_funeral),
    FOREIGN KEY (id_funeral) REFERENCES Funeral(id) ON DELETE CASCADE,
    FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf_cliente) ON DELETE CASCADE,
    FOREIGN KEY (cpf_falec) REFERENCES Falecido(cpf_falec) ON DELETE CASCADE
);

CREATE TABLE Enterro (
    id_funeral INTEGER,
    PRIMARY KEY (id_funeral),
    FOREIGN KEY (id_funeral) REFERENCES Funeral(id) ON DELETE CASCADE
);

CREATE TABLE Cremacao (
    id_funeral INTEGER,
    PRIMARY KEY (id_funeral),
    FOREIGN KEY (id_funeral) REFERENCES Funeral (id) ON DELETE CASCADE
);

CREATE TABLE Produto (
    num_serie VARCHAR2(4),
    preco DECIMAL(5, 2),
    cor VARCHAR2(30),
    fabricante VARCHAR2(100),
    PRIMARY KEY (num_serie)
);

CREATE TABLE Urna (
    num_serie VARCHAR2(100),
    peso DECIMAL(6, 2),
    volume DECIMAL(4, 2),
    id_funeral INTEGER,
    PRIMARY KEY (num_serie),
    FOREIGN KEY (num_serie) REFERENCES Produto(num_serie) ON DELETE CASCADE,
    FOREIGN KEY (id_funeral) REFERENCES Cremacao(id_funeral) ON DELETE CASCADE
);

CREATE TABLE Caixao (
    num_serie VARCHAR2(100),
    altura DECIMAL(3, 2),
    largura DECIMAL(3, 2),
    revestimento VARCHAR2(100),
    id_funeral INTEGER,
    PRIMARY KEY (num_serie),
    FOREIGN KEY (num_serie) REFERENCES Produto(num_serie) ON DELETE CASCADE,
    FOREIGN KEY (id_funeral) REFERENCES Enterro(id_funeral) ON DELETE CASCADE
);

CREATE TABLE Telefones (
    numero VARCHAR2(100),
    cpf_pessoa VARCHAR2(11),
    PRIMARY KEY (numero),
    FOREIGN KEY (cpf_pessoa) REFERENCES Pessoa(cpf) ON DELETE CASCADE
);

CREATE TABLE Convidado (
    id_convidado INTEGER,
    prim_nome VARCHAR2(100),
    nome_familia VARCHAR2(100),
    id_funeral INTEGER,
    PRIMARY KEY (id_convidado),
    FOREIGN KEY (id_funeral) REFERENCES Contrata (id_funeral) ON DELETE CASCADE
);
