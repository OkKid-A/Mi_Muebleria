    --Creacion del usuario para la base de datos--

    CREATE USER 'mi_nuevo_usuario'@'localhost'
IDENTIFIED BY 'abc123';

--creando la base de datos--

CREATE DATABASE mi_nuevo_usuario ;

GRANT ALL PRIVILEGES 
ON mi_muebleria.*
TO mi_nuevo_usuario@localhost;

--seleccionamos la base de datos--
USE mi_muebleria;

--creacion de las tablas--

CREATE TABLE `cliente` (
  `nombre` varchar(40) NOT NULL,
  `nit` varchar(10) NOT NULL,
  `direccion` varchar(20) NOT NULL,
  `municipio` varchar(20) DEFAULT NULL,
  `departamento` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nit`),
  CONSTRAINT `confirmar_departamento` CHECK ((`departamento` = (case when (`municipio` = NULL) then NULL end)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `factura` (
  `numero_factura` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `cliente` varchar(10) NOT NULL,
  `total` decimal(8,2) DEFAULT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  `vendedor` varchar(25) NOT NULL,
  PRIMARY KEY (`numero_factura`),
  KEY `factura_usuario_nombre_usuario_fk` (`vendedor`),
  KEY `factura_cliente_nit_fk` (`cliente`),
  CONSTRAINT `factura_cliente_nit_fk` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`nit`),
  CONSTRAINT `factura_usuario_nombre_usuario_fk` FOREIGN KEY (`vendedor`) REFERENCES `usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mueble_ensamble_parte` (
  `mueble` varchar(30) NOT NULL,
  `pieza` varchar(25) NOT NULL,
  `cantidad` int unsigned NOT NULL,
  PRIMARY KEY (`mueble`,`pieza`),
  KEY `pieza_idx` (`pieza`),
  CONSTRAINT `mueble` FOREIGN KEY (`mueble`) REFERENCES `mueble_tipo` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mueble_producto` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `tipo_mueble` varchar(30) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `usuario` varchar(25) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `vendido` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mueble_a_creador` (`usuario`),
  KEY `mueble_producto__a_tipo` (`tipo_mueble`),
  CONSTRAINT `mueble_a_creador` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`nombre_usuario`),
  CONSTRAINT `mueble_producto__a_tipo` FOREIGN KEY (`tipo_mueble`) REFERENCES `mueble_tipo` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `mueble_tipo` (
  `nombre` varchar(30) NOT NULL,
  `precio` decimal(8,2) unsigned NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `pieza` (
  `tipo` varchar(25) NOT NULL,
  `valor` decimal(8,2) unsigned NOT NULL,
  `cantidad` int unsigned NOT NULL,
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuario` (
  `nombre_usuario` varchar(25) NOT NULL,
  `tipo` int NOT NULL,
  `password` char(32) NOT NULL,
  PRIMARY KEY (`nombre_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `venta` (
  `id_producto` int(10) unsigned zerofill NOT NULL,
  `id_factura` int(10) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

