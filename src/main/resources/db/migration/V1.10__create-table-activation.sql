create table activations (
                             id int primary key auto_increment,
                             activation_user_code_to_persist varchar(200),
                             activation_status boolean,
                             creation datetime default current_timestamp,
                             desactivation datetime,
                             profiles_id int,
                             constraint fk_activations_profiles foreign key(profiles_id) references profiles(id)

);