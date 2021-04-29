create table cart
(
    id         varchar(255) not null,
    created_at datetime(6),
    primary key (id)
);


create table cart_amount
(
    id      bigint  not null auto_increment,
    amount  integer not null,
    cart_id varchar(255),
    item_id bigint,
    primary key (id)
);

create table image
(
    id      bigint not null auto_increment,
    url     varchar(255),
    item_id bigint,
    primary key (id)
);

create table item
(
    id          bigint  not null auto_increment,
    description varchar(255),
    price       integer not null,
    title       varchar(255),
    category    integer not null,
    primary key (id)
);

create table purchase
(
    id           bigint not null,
    purchased_at datetime(6),
    user_id      bigint,
    total_price integer,
    primary key (id)
);

create table purchase_amount
(
    id          bigint  not null auto_increment,
    amount      integer not null,
    item_id     bigint,
    purchase_id bigint,
    primary key (id)
);

create table user
(
    id       bigint not null auto_increment,
    email    varchar(255),
    password varchar(255),
    username varchar(255),
    enabled  bit    not null,
    primary key (id)
);

create table verification_token (
    id          bigint not null auto_increment,
    expiry_date datetime(6),
    token       varchar(255),
    user_id     bigint not null,
    primary key (id),
    foreign key (user_id) references user (id)
);