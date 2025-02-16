-- Création de la table declarations
create table declarations
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    comment text,
    registered  datetime default current_timestamp,
    child_id int,
    first_parent_id int,
    second_parent_id int,
    company_id int,
    constraint fk_declarations_children foreign key(child_id) references profiles(id),
    constraint fk_declarations_first_parent foreign key(first_parent_id) references profiles(id),
    constraint fk_declarations_second_parent foreign key(second_parent_id) references profiles(id),
    constraint fk_declarations_company foreign key(company_id) references companies(id)
);


create table declarations_status
(
    id int auto_increment primary key,
    status_id int,
    declarations_id int,

    constraint fk_declarations_status_declarations foreign key(status_id) references status(id),
    constraint fk_declarations_status_status foreign key(declarations_id) references declarations(id)

);