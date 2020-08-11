/* Generate Environment
hibernate.format_sql: true
hibernate.id.new_generator_mappings: true
hibernate.physical_naming_strategy: org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
hibernate.dialect: class org.hibernate.dialect.H2Dialect
hibernate.implicit_naming_strategy: org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy
*/
create sequence hibernate_sequence start with 1 increment by 1;

create table company (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table contact (
    id bigint not null,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    status varchar(255),
    company_id bigint,
    primary key (id)
);

alter table contact 
    add constraint FKpgbqt6dnai52x55o1qvsx1dfn 
    foreign key (company_id) 
    references company;
