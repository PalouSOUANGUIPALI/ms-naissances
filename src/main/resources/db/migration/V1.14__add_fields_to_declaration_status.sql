alter table declarations_status add column creation datetime default current_timestamp;
alter table declarations_status add column comment text;

alter table declarations_status add column agent_id int;

alter table declarations_status add constraint fk_declarations_status_agent foreign key(agent_id) references profiles (id);