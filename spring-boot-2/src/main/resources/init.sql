create table async_kafka
(
    id                       bigint auto_increment
        primary key,
    create_time              datetime not null,
    update_time              datetime null,
    status                   varchar(50)
);

create table reactor
(
    id                       bigint auto_increment
        primary key,
    create_time              datetime not null,
    update_time              datetime null,
    status                   varchar(50)
);

create table async
(
    id                       bigint auto_increment
        primary key,
    create_time              datetime not null,
    update_time              datetime null,
    status                   varchar(50)
);

create table rxjava
(
    id                       bigint auto_increment
        primary key,
    create_time              datetime not null,
    update_time              datetime null,
    status                   varchar(50)
);

create table kafka
(
    id                       bigint auto_increment
        primary key,
    create_time              datetime not null,
    update_time              datetime null,
    status                   varchar(50)
);