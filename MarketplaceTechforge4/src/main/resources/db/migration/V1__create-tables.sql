create table usuario (
    id int auto_increment primary key,
    nome varchar(50) not null,
    email varchar(120) not null
);

create table produto (
    id int auto_increment primary key,
    nome varchar(120) not null,
    preco decimal(10, 2) not null,
    descricao text not null,
    favorito boolean default false,
    url_imagem varchar(500)
);

create table pedido (
	id int auto_increment primary key,
	usuario_id int not null,
	valor decimal(10, 2) default 0.00,
	data_pedido timestamp default current_timestamp,
	foreign key (usuario_id) references usuario(id)
);

create table item_pedido (
    pedido_id int not null,
    produto_id int not null,
    quantidade int not null check (quantidade > 0),
    primary key (pedido_id, produto_id),
    foreign key (pedido_id) references pedido(id),
    foreign key (produto_id) references produto(id)
);

create table audit_preco_produto (
    id int auto_increment primary key,
    data_modificacao timestamp default current_timestamp,
    preco_antigo decimal(10, 2) not null,
    preco_novo decimal(10, 2) not null
);