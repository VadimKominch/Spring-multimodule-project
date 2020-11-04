create table Certificates (
                        id int primary key ,
                        title varchar(100),
                        description text,
                        price double,
                        creation_Date datetime(3),
                        last_update_time datetime(3),
                        duration smallint,
                        );

create table tags (
                      id int primary key ,
                      name varchar(100)
);

create table certificates_tags (
                       id int primary key ,
                       certificate_id int,
                       tag_id int
);