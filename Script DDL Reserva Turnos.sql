-- MySQL Script generated by MySQL Workbench
-- Sat Aug  8 07:29:08 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

USE centroautomotriz;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `departamentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `departamentos` (
  `id_depto` INT NOT NULL AUTO_INCREMENT,
  `nom_depto` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_depto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ciudades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ciudades` (
  `id_ciudad` INT NOT NULL AUTO_INCREMENT,
  `nom_ciudad` VARCHAR(50) NOT NULL,
  `id_depto` INT NOT NULL,
  PRIMARY KEY (`id_ciudad`),
  INDEX `fk_ciudades_departamentos_idx` (`id_depto` ASC) VISIBLE,
  CONSTRAINT `fk_ciudades_departamentos`
    FOREIGN KEY (`id_depto`)
    REFERENCES `departamentos` (`id_depto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sucursales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sucursales` (
  `id_sucursal` INT NOT NULL AUTO_INCREMENT,
  `nom_sucursal` VARCHAR(80) NOT NULL,
  `dir_sucursal` VARCHAR(100) NOT NULL,
  `tel_sucursal` DECIMAL(10,0) NULL,
  `id_ciudad` INT NOT NULL,
  PRIMARY KEY (`id_sucursal`),
  INDEX `fk_sucursales_ciudades1_idx` (`id_ciudad` ASC) VISIBLE,
  CONSTRAINT `fk_sucursales_ciudades1`
    FOREIGN KEY (`id_ciudad`)
    REFERENCES `ciudades` (`id_ciudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `areas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `areas` (
  `id_area` INT NOT NULL,
  `nom_area` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_area`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servicios` (
  `id_servicio` INT NOT NULL AUTO_INCREMENT,
  `nom_servicio` INT NOT NULL,
  `valor_servicio` DECIMAL(7,0) NOT NULL,
  `tiempo_servicio` DECIMAL(5,1) NOT NULL,
  `id_area` INT NOT NULL,
  PRIMARY KEY (`id_servicio`),
  INDEX `fk_servicios_areas1_idx` (`id_area` ASC) VISIBLE,
  CONSTRAINT `fk_servicios_areas1`
    FOREIGN KEY (`id_area`)
    REFERENCES `areas` (`id_area`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clientes` (
  `tipo_documento` VARCHAR(5) NOT NULL,
  `nro_doc_cliente` DECIMAL(10,0) NOT NULL,
  `nom_cliente` VARCHAR(50) NOT NULL,
  `ape_cliente` VARCHAR(50) NOT NULL,
  `correo_cliente` VARCHAR(100) NOT NULL,
  `tel_cliente` DECIMAL(10,0) NULL,
  PRIMARY KEY (`tipo_documento`, `nro_doc_cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `turnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `turnos` (
  `id_turno` INT NOT NULL,
  `fecha_turno` DATE NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `hora_fin` TIME NOT NULL,
  `estado_turno` VARCHAR(5) NOT NULL,
  `obs_turno` VARCHAR(250) NULL,
  `cod_reserva` VARCHAR(6) NULL,
  `id_servicio` INT NOT NULL,
  `tipo_documento` VARCHAR(5) NOT NULL,
  `nro_doc_cliente` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_turno`),
  INDEX `fk_turnos_servicios1_idx` (`id_servicio` ASC) VISIBLE,
  INDEX `fk_turnos_clientes1_idx` (`tipo_documento` ASC, `nro_doc_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_turnos_servicios1`
    FOREIGN KEY (`id_servicio`)
    REFERENCES `servicios` (`id_servicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turnos_clientes1`
    FOREIGN KEY (`tipo_documento` , `nro_doc_cliente`)
    REFERENCES `clientes` (`tipo_documento` , `nro_doc_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuarios` (
  `nro_doc_usuario` DECIMAL(10,0) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(128) NOT NULL,
  `tipo_usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nro_doc_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opciones` (
  `id_permiso` INT NOT NULL AUTO_INCREMENT,
  `funcionalidad` VARCHAR(100) NOT NULL,
  `ruta` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_permiso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `permisos_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `permisos_usuarios` (
  `id_permiso` INT NOT NULL,
  `nro_doc_usuario` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_permiso`, `nro_doc_usuario`),
  INDEX `fk_permisos_has_usuarios_usuarios1_idx` (`nro_doc_usuario` ASC) VISIBLE,
  INDEX `fk_permisos_has_usuarios_permisos1_idx` (`id_permiso` ASC) VISIBLE,
  CONSTRAINT `fk_permisos_has_usuarios_permisos1`
    FOREIGN KEY (`id_permiso`)
    REFERENCES `opciones` (`id_permiso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_permisos_has_usuarios_usuarios1`
    FOREIGN KEY (`nro_doc_usuario`)
    REFERENCES `usuarios` (`nro_doc_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `areas_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `areas_usuarios` (
  `id_area` INT NOT NULL,
  `nro_doc_usuario` DECIMAL(10,0) NOT NULL,
  PRIMARY KEY (`id_area`, `nro_doc_usuario`),
  INDEX `fk_areas_has_usuarios_usuarios1_idx` (`nro_doc_usuario` ASC) VISIBLE,
  INDEX `fk_areas_has_usuarios_areas1_idx` (`id_area` ASC) VISIBLE,
  CONSTRAINT `fk_areas_has_usuarios_areas1`
    FOREIGN KEY (`id_area`)
    REFERENCES `areas` (`id_area`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_areas_has_usuarios_usuarios1`
    FOREIGN KEY (`nro_doc_usuario`)
    REFERENCES `usuarios` (`nro_doc_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `areas_sucursales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `areas_sucursales` (
  `id_area_sucursal` INT NOT NULL AUTO_INCREMENT,
  `estado_area` VARCHAR(5) NOT NULL,
  `id_sucursal` INT NOT NULL,
  `id_area` INT NOT NULL,
  PRIMARY KEY (`id_area_sucursal`),
  INDEX `fk_areas_sucursales_sucursales1_idx` (`id_sucursal` ASC) VISIBLE,
  INDEX `fk_areas_sucursales_areas1_idx` (`id_area` ASC) VISIBLE,
  CONSTRAINT `fk_areas_sucursales_sucursales1`
    FOREIGN KEY (`id_sucursal`)
    REFERENCES `sucursales` (`id_sucursal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_areas_sucursales_areas1`
    FOREIGN KEY (`id_area`)
    REFERENCES `areas` (`id_area`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
