/* Criação do banco de dados */
drop database if exists TCG_SHOP;
Create database TCG_SHOP;
use TCG_SHOP;
SET SQL_SAFE_UPDATES=0;

/* Tabela 6 */
create table Cartas(
idCarta int not null auto_increment primary key,
Nome varchar(100)
);

/* Tabela 5 */
create table Produtos(
idProduto int not null auto_increment primary key,
Nome varchar(100)
);

/* Tabela 4 */
create table Pedidos(
idPedido int auto_increment not null primary key,
dataPedido datetime default now()
);

/* Tabela 3 (N:N – Produtos e Cartas dentro de pedidos) */
create table ItensPedido(
    idItem int auto_increment primary key,
    idPedido int not null,
    idProduto int null,
    idCarta int null,
    Quantidade int,
    foreign key (idPedido) references Pedidos(idPedido),
    foreign key (idProduto) references Produtos(idProduto),
    foreign key (idCarta) references Cartas(idCarta)
);

/* Tabela 2 */
create table Jogador(
  idJogador int not null auto_increment primary key,
  Nome varchar(100),
  idPedido int,
  foreign key (idPedido) references Pedidos(idPedido)
);

/* Tabela 1 (1-1 com Jogador)*/
create table Usuario(
    idJogador int not null auto_increment,
    Email varchar(100),
    Senha VARCHAR(100),
    CPF varchar(11),
    Endereco varchar(200),
    Telefone varchar(20),
    primary key(idJogador),
    foreign key (idJogador) references Jogador(idJogador)
);

/* Inserção de dados nas tabelas */

insert into Cartas
(Nome)
values
('The One Ring'),
('Last March of the Ents'),
('Nazgûl'),
('Sauron, the Dark Lord'),
('Gandalf the White');

insert into Produtos
(Nome)
values
('Booster Avulso - Final Fantasy - Booster de Jogo'),
('Booster Avulso - O Senhor dos Anéis: Contos da Terra Média - Booster de Coleção'),
('Booster Avulso - Bloomburrow - Booster de Jogo'),
('Shield Dragon Shield - Dual Matte - Lightning (Amarelo) (100 unidades)'),
('Shield Dragon Shield - Dual Matte - Justice (Cinza) (100 unidades)');

insert into 
Pedidos () 
values
(),(),(),(),();

insert into Jogador
(Nome)
values
('Kleber'),
('Cleyton'),
('Rogério'),
('Robson'),
('Glauber');

insert into 
Usuario (idJogador,Email,Senha,CPF,Endereco,Telefone) 
values
(1,'kleber@gmail.com','12345678910','12345678910','Rua Joselito dos Santos,999 - Centro - SP','(11)98765-4321'),
(2,'cleyton@gmail.com','12345678911','12345678911','Rua Joselito dos Santos,1999 - Centro - SP','(11)98325-4121'),
(3,'rogerio@gmail.com','12345678912','12345678912','Rua Joselito dos Santos,2999 - Centro - SP','(11)9956-4486'),
(4,'robson@gmail.com','12345678913','12345678913','Rua Joselito dos Santos,3999 - Centro - SP','(11)98453-7892'),
(5,'glauber@gmail.com','12345678914','12345678914','Rua Joselito dos Santos,4999 - Centro - SP','(11)98451-8796');

insert into ItensPedido (idPedido,idCarta,Quantidade) values
(1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5);

insert into ItensPedido (idPedido,idProduto,Quantidade) values
(1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5);

ALTER TABLE Produtos ADD preco DOUBLE;
ALTER TABLE Cartas ADD preco DOUBLE;
select * from Usuario;
select * from Produtos;