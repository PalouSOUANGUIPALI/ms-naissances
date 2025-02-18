alter table profiles add column active boolean;
alter table profiles add column roles_id int;
alter table profiles add constraint fk_profiles_roles foreign key(roles_id) references roles(id);