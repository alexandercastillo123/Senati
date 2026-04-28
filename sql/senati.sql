drop database if exists senati;
create database senati;
use senati;

create table carrera(
    id int primary key auto_increment,
    nombre char(50) not null
);

create table ciclo(
    id int primary key auto_increment,
    nombre_ciclo enum("I","II","III","IV","V","VI") not null
);

create table configuracion_pago(
    id int primary key auto_increment,
    id_carrera int,
    id_ciclo int,
    monto_mensual double not null,
    constraint fk_conf_carrera foreign key (id_carrera) references carrera(id),
    constraint fk_conf_ciclo foreign key (id_ciclo) references ciclo(id)
);

create table curso(
    id int primary key auto_increment,
    nombre char(50) not null,
    id_carrera int,
    id_ciclo int,
    constraint fk_curso_carrera foreign key (id_carrera) references carrera(id),
    constraint fk_curso_ciclo foreign key (id_ciclo) references ciclo(id)
);

create table estudiante(
    id int primary key auto_increment,
    correo_institucional char(50) unique,
    id_estudiante char(7) not null,
    nombres char(50) not null,
    apellidos char(50) not null,
    dni char(8) unique not null,
    pass char(15),
    fecha_nacimiento date,
    id_carrera int,
    id_ciclo int,
    constraint fk_estudiante_carrera foreign key (id_carrera) references carrera(id),
    constraint fk_estudiante_ciclo foreign key (id_ciclo) references ciclo(id)
);

create table horario(
    id int primary key auto_increment,
    dia enum('lunes', 'martes', 'miercoles', 'jueves', 'viernes', 'sabado'),
    hora_inicio time,
    hora_fin time,
    id_curso int,
    id_estudiante int,
    constraint fk_horario_curso foreign key (id_curso) references curso(id),
    constraint fk_horario_estudiante foreign key (id_estudiante) references estudiante(id)
);

create table nota(
    id int primary key auto_increment,
    id_estudiante int,
    id_curso int,
    nota_estudiante double null, -- NULL significa que el docente aún no califica
    constraint fk_nota_estudiante foreign key (id_estudiante) references estudiante(id),
    constraint fk_nota_curso foreign key (id_curso) references curso(id)
);

create table cronograma_pago(
    id int primary key auto_increment,
    id_estudiante int,
    mes_pago char(20),
    monto double,
    fecha_vencimiento date,
    estado enum("pendiente","vencido","cancelado") default "pendiente",
    constraint fk_pago_estudiante foreign key (id_estudiante) references estudiante(id)
);

insert into carrera (nombre) values ('Ingenieria de Software'), ('Ciberseguridad');
insert into ciclo (nombre_ciclo) values ('I'), ('II'), ('III'), ('IV'), ('V'), ('VI');

-- Configuración de costos mensuales (Software Ciclo I = 450, Ciclo II = 500)
insert into configuracion_pago (id_carrera, id_ciclo, monto_mensual) values (1, 1, 450.00), (1, 2, 500.00);

-- Cursos para Software Ciclo I
insert into curso (nombre, id_carrera, id_ciclo) values ('Algoritmos', 1, 1), ('Fundamentos TI', 1, 1);

-- Estudiante en Ciclo I de Software
insert into estudiante (correo_institucional, id_estudiante, nombres, apellidos, dni, pass, id_carrera, id_ciclo) 
values ('pedro.lapa@senati.pe', '1000001', 'pedro', 'lapa', '70001020', '12345', 1, 1);

-- Notas para Pedro (Una calificada, una pendiente)
insert into nota (id_estudiante, id_curso, nota_estudiante) values (1, 1, 17.5), (1, 2, null);

-- Pago generado para Pedro basado en su configuración
insert into cronograma_pago (id_estudiante, mes_pago, monto, fecha_vencimiento) 
values (1, 'mayo', 450.00, '2026-05-30');

delimiter //

create procedure sp_login(in _correo char(50), in _pass char(15))
begin
    select id, nombres, apellidos, id_carrera, id_ciclo from estudiante 
    where correo_institucional = _correo and pass = _pass;
end //

create procedure sp_ver_mis_cursos(in _id_estudiante int)
begin
    select c.nombre as curso, cic.nombre_ciclo
    from curso c
    inner join ciclo cic on c.id_ciclo = cic.id
    inner join estudiante e on e.id_carrera = c.id_carrera and e.id_ciclo = c.id_ciclo
    where e.id = _id_estudiante;
end //

create procedure sp_ver_mis_notas(in _id_estudiante int)
begin
    select c.nombre as curso, ifnull(n.nota_estudiante, '--') as nota
    from curso c
    left join nota n on c.id = n.id_curso and n.id_estudiante = _id_estudiante
    inner join estudiante e on e.id_carrera = c.id_carrera and e.id_ciclo = c.id_ciclo
    where e.id = _id_estudiante;
end //

create procedure sp_promedio_ciclo(in _id_estudiante int)
begin
    select avg(nota_estudiante) as promedio from nota 
    where id_estudiante = _id_estudiante and nota_estudiante is not null;
end //

create procedure sp_ver_mis_pagos(in _id_estudiante int)
begin
    select mes_pago, monto, fecha_vencimiento, estado from cronograma_pago where id_estudiante = _id_estudiante;
end //

create procedure sp_ver_mi_horario(in _id_estudiante int)
begin
    select h.dia, h.hora_inicio, h.hora_fin, c.nombre as curso
    from horario h
    inner join curso c on h.id_curso = c.id
    where h.id_estudiante = _id_estudiante
    order by field(dia, 'lunes', 'martes', 'miercoles', 'jueves', 'viernes', 'sabado'), h.hora_inicio;
end //

create procedure sp_perfil_estudiante(in _id_estudiante int)
begin
    select e.nombres, e.apellidos, e.dni, e.correo_institucional, c.nombre as carrera, cic.nombre_ciclo as ciclo
    from estudiante e
    inner join carrera c on e.id_carrera = c.id
    inner join ciclo cic on e.id_ciclo = cic.id
    where e.id = _id_estudiante;
end //

create procedure sp_ver_compañeros(in _id_estudiante int)
begin
    select e2.nombres, e2.apellidos, e2.correo_institucional 
    from estudiante e1
    join estudiante e2 on e1.id_carrera = e2.id_carrera and e1.id_ciclo = e2.id_ciclo
    where e1.id = _id_estudiante and e2.id <> _id_estudiante;
end //

create procedure sp_listar_carreras()
begin
    select * from carrera;
end //

create procedure sp_listar_ciclos()
begin
    select * from ciclo;
end //

create procedure sp_cambiar_pass(in _id_estudiante int, in _nueva_pass char(15))
begin
    update estudiante set pass = _nueva_pass where id = _id_estudiante;
end //

create procedure sp_pagos_pendientes(in _id_estudiante int)
begin
    select mes_pago, monto, fecha_vencimiento from cronograma_pago 
    where id_estudiante = _id_estudiante and estado = 'pendiente';
end //

create procedure sp_cumpleaños_mes()
begin
    select nombres, apellidos, fecha_nacimiento from estudiante where month(fecha_nacimiento) = month(curdate());
end //

create procedure sp_resumen_carreras()
begin
    select c.nombre, count(e.id) as total from carrera c 
    left join estudiante e on c.id = e.id_carrera group by c.nombre;
end //

create procedure sp_buscar_dni(in _dni char(8))
begin
    select * from estudiante where dni = _dni;
end //

delimiter ;