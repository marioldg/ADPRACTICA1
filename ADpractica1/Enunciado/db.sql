-- Eliminar la base de datos si existe
DROP DATABASE IF EXISTS AppDatabase;

-- Crear la base de datos
CREATE DATABASE AppDatabase;
USE AppDatabase;

-- Tabla AdminTorneo
CREATE TABLE AdminTorneo (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

-- Tabla Entrenador
CREATE TABLE Entrenador (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(255) NOT NULL
);

-- Tabla Torneo (idAdmin ya no es clave foránea)
CREATE TABLE Torneo (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    codRegion CHAR(1) NOT NULL,
    puntosVictoria FLOAT NOT NULL,
    idAdmin INT  -- Solo valor referencial, sin clave foránea
);

-- Tabla intermedia EntrenadorTorneo
CREATE TABLE EntrenadorTorneo (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idEntrenador BIGINT NOT NULL,
    idTorneo INT NOT NULL,
    FOREIGN KEY (idEntrenador) REFERENCES Entrenador(id),
    FOREIGN KEY (idTorneo) REFERENCES Torneo(id)
);

-- Tabla Carnet
CREATE TABLE Carnet (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idEntrenador BIGINT NOT NULL,
    fechaExpedicion DATE NOT NULL,
    puntos FLOAT NOT NULL,
    numVictorias INT NOT NULL
);

-- Tabla Combate (permite NULL en idEntrenador1, idEntrenador2 e idTorneo)
CREATE TABLE Combate (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    idEntrenador1 BIGINT NULL,
    idEntrenador2 BIGINT NULL,
    idTorneo INT NULL

);

-- Tabla Paises
CREATE TABLE pais (
    id CHAR(4) NOT NULL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Insertar datos en la tabla AdminTorneo
INSERT INTO AdminTorneo (nombre, contrasena) VALUES
('Admin1', 'password1'),
('Admin2', 'password2'),
('Admin3', 'password3');

-- Insertar datos en la tabla Torneo
INSERT INTO Torneo (nombre, codRegion, puntosVictoria, idAdmin) VALUES
('Liga Pokémon', 'A', 50.0, 1),
('Torneo Mundial', 'B', 75.5, 2),
('Copa Cascada', 'C', 60.0, 3),
('Torneo Regional', 'D', 40.0, 1);

-- Insertar datos en la tabla Entrenador
INSERT INTO Entrenador (nombre, nacionalidad) VALUES
('Ash Ketchum', 'Japón'),
('Brock Slate', 'Estados Unidos'),
('Misty Cascade', 'Canadá'),
('Serena Grace', 'Francia');

-- Insertar datos en la tabla EntrenadorTorneo
INSERT INTO EntrenadorTorneo (idEntrenador, idTorneo) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 3),
(4, 1),
(4, 4);

-- Insertar datos en la tabla Carnet
INSERT INTO Carnet (idEntrenador, fechaExpedicion, puntos, numVictorias) VALUES
(1, '2023-01-15', 120.5, 10),
(2, '2022-07-20', 98.0, 8),
(3, '2021-03-12', 75.0, 6),
(4, '2020-11-05', 150.0, 12);

-- Insertar datos en la tabla Combate
INSERT INTO Combate (fecha, idEntrenador1, idEntrenador2, idTorneo) VALUES
('2024-01-05', 1, 2, 1),
('2024-01-10', 3, 4, 2),
('2024-01-15', NULL, 3, 3),
('2024-01-20', 2, NULL, 4);

-- Insertar datos en la tabla Paises
INSERT INTO pais (id, nombre) VALUES
('AE', 'Emiratos Árabes Unidos'),
('AT', 'Austria'),
('AU', 'Australia'),
('BE', 'Bélgica'),
('BR', 'Brasil'),
('CA', 'Canadá'),
('CH', 'Suiza'),
('CO', 'Colombia'),
('DE', 'Alemania'),
('DK', 'Dinamarca'),
('EC', 'Ecuador'),
('EE', 'Estonia'),
('ES', 'España'),
('FI', 'Finlandia'),
('FR', 'Francia'),
('HK', 'China'),
('HR', 'Croacia'),
('HU', 'Hungría'),
('ID', 'Indonesia'),
('IE', 'Irlanda'),
('IN', 'India'),
('IR', 'Irán'),
('IS', 'Islandia'),
('IT', 'Italia'),
('JM', 'Jamaica'),
('JP', 'Japón'),
('KR', 'Korea'),
('LI', 'Principado de Liechtenstein'),
('LU', 'Luxemburgo'),
('MA', 'Marruecos'),
('MO', 'Argentina'),
('MX', 'México'),
('MY', 'Malasia'),
('NL', 'Holanda'),
('NO', 'Noruega'),
('NZ', 'Nueva Zelanda'),
('PT', 'Portugal'),
('SA', 'Arabia Saudí'),
('SE', 'Suecia'),
('SG', 'Singapur'),
('TH', 'Tailandia'),
('TN', 'Túnez'),
('TR', 'Turquía'),
('TW', 'Chile'),
('UK', 'Reino Unido'),
('US', 'Estados Unidos de América'),
('VN', 'Vietnam'),
('ZA', 'Sudáfrica');
