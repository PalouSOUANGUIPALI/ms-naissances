-- Creation de la table activations des profiles avec l'envoi du code par email
create table activations(
    id int auto_increment,
    activation_user_code_to_persist varchar(200),
    activation_status boolean,
    creation datetime default current_timestamp,
    desactivation datetime,
    profiles_id int,
    constraint fk_activations_profiles foreign key(profiles_id) references profiles(id),
    primary key (id)
)