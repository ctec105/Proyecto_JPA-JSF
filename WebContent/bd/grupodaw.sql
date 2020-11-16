SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_area`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_area` (
  `id_area` INT(11) NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_area`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_menu`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_menu` (
  `id_menu` INT(11) NOT NULL AUTO_INCREMENT ,
  `nivel` CHAR(3) NOT NULL ,
  `titulo` VARCHAR(45) NULL DEFAULT NULL ,
  `action` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_menu`) )
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_area_has_tb_menu`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_area_has_tb_menu` (
  `tb_area_id_area` INT(11) NOT NULL ,
  `tb_menu_id_menu` INT(11) NOT NULL ,
  `ver` CHAR(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_area_id_area`, `tb_menu_id_menu`) ,
  CONSTRAINT `fk_tb_area_has_tb_menu_tb_area1`
    FOREIGN KEY (`tb_area_id_area` )
    REFERENCES `grupodaw`.`tb_area` (`id_area` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_area_has_tb_menu_tb_menu1`
    FOREIGN KEY (`tb_menu_id_menu` )
    REFERENCES `grupodaw`.`tb_menu` (`id_menu` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_area_has_tb_menu_tb_area1` ON `grupodaw`.`tb_area_has_tb_menu` (`tb_area_id_area` ASC) ;

CREATE INDEX `fk_tb_area_has_tb_menu_tb_menu1` ON `grupodaw`.`tb_area_has_tb_menu` (`tb_menu_id_menu` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_claseequipo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_claseequipo` (
  `id_claseEquipo` INT(11) NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_claseEquipo`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_cliente` (
  `id_cliente` VARCHAR(11) NOT NULL ,
  `razonsocial` VARCHAR(45) NULL DEFAULT NULL ,
  `direccion` VARCHAR(45) NULL DEFAULT NULL ,
  `telefono` VARCHAR(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_cliente`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_marca`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_marca` (
  `id_marca` INT(11) NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_marca`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_modelo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_modelo` (
  `id_modelo` INT(11) NOT NULL AUTO_INCREMENT ,
  `descripcion` VARCHAR(45) NOT NULL ,
  `tb_marca_id_marca` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_modelo`) ,
  CONSTRAINT `fk_tb_modelo_tb_marca`
    FOREIGN KEY (`tb_marca_id_marca` )
    REFERENCES `grupodaw`.`tb_marca` (`id_marca` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_modelo_tb_marca` ON `grupodaw`.`tb_modelo` (`tb_marca_id_marca` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_equipo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_equipo` (
  `nrotarjeta` VARCHAR(11) NOT NULL ,
  `nroserie` VARCHAR(15) NOT NULL ,
  `tb_claseEquipo_id_claseEquipo` INT(11) NULL DEFAULT NULL ,
  `tb_cliente_id_cliente` VARCHAR(11) NULL DEFAULT NULL ,
  `tb_modelo_id_modelo` INT(11) NOT NULL ,
  PRIMARY KEY (`nrotarjeta`) ,
  CONSTRAINT `fk_tb_equipo_tb_claseEquipo`
    FOREIGN KEY (`tb_claseEquipo_id_claseEquipo` )
    REFERENCES `grupodaw`.`tb_claseequipo` (`id_claseEquipo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_equipo_tb_cliente`
    FOREIGN KEY (`tb_cliente_id_cliente` )
    REFERENCES `grupodaw`.`tb_cliente` (`id_cliente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_equipo_tb_modelo1`
    FOREIGN KEY (`tb_modelo_id_modelo` )
    REFERENCES `grupodaw`.`tb_modelo` (`id_modelo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_equipo_tb_claseEquipo` ON `grupodaw`.`tb_equipo` (`tb_claseEquipo_id_claseEquipo` ASC) ;

CREATE INDEX `fk_tb_equipo_tb_cliente` ON `grupodaw`.`tb_equipo` (`tb_cliente_id_cliente` ASC) ;

CREATE INDEX `fk_tb_equipo_tb_modelo1` ON `grupodaw`.`tb_equipo` (`tb_modelo_id_modelo` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_solicitud`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_solicitud` (
  `id_solicitud` INT(11) NOT NULL AUTO_INCREMENT ,
  `des_problema` VARCHAR(300) NULL DEFAULT NULL ,
  `Representante` VARCHAR(100) NULL DEFAULT NULL ,
  `fecha` DATE NULL DEFAULT NULL ,
  `horometro_actual` INT(11) NULL DEFAULT NULL ,
  `estado` CHAR(1) NULL DEFAULT NULL ,
  `tb_equipo_nrotarjeta` VARCHAR(11) NULL DEFAULT NULL ,
  `telefono` VARCHAR(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_solicitud`) ,
  CONSTRAINT `fk_tb_solicitud_tb_equipo`
    FOREIGN KEY (`tb_equipo_nrotarjeta` )
    REFERENCES `grupodaw`.`tb_equipo` (`nrotarjeta` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_solicitud_tb_equipo` ON `grupodaw`.`tb_solicitud` (`tb_equipo_nrotarjeta` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_ordentrabajo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_ordentrabajo` (
  `id_otrabajo` INT(11) NOT NULL AUTO_INCREMENT ,
  `fecha_inicio` DATE NULL DEFAULT NULL ,
  `fecha_termino` DATE NULL DEFAULT NULL ,
  `comentarios` VARCHAR(200) NULL DEFAULT NULL ,
  `tb_solicitud_id_solicitud` INT(11) NOT NULL ,
  `usuario` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_otrabajo`, `tb_solicitud_id_solicitud`) ,
  CONSTRAINT `fk_tb_ordenTrabajo_tb_solicitud`
    FOREIGN KEY (`tb_solicitud_id_solicitud` )
    REFERENCES `grupodaw`.`tb_solicitud` (`id_solicitud` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_ordenTrabajo_tb_solicitud` ON `grupodaw`.`tb_ordentrabajo` (`tb_solicitud_id_solicitud` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_ordenliquidacion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_ordenliquidacion` (
  `id_liquidacion` INT(11) NOT NULL ,
  `fecha` DATE NULL DEFAULT NULL ,
  `tb_ordenTrabajo_id_otrabajo` INT(11) NULL DEFAULT NULL ,
  `nombre` VARCHAR(100) NULL DEFAULT NULL ,
  `usuario` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_liquidacion`) ,
  CONSTRAINT `fk_tb_ordenLiquidacion_tb_ordenTrabajo`
    FOREIGN KEY (`tb_ordenTrabajo_id_otrabajo` )
    REFERENCES `grupodaw`.`tb_ordentrabajo` (`id_otrabajo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_ordenLiquidacion_tb_ordenTrabajo` ON `grupodaw`.`tb_ordenliquidacion` (`tb_ordenTrabajo_id_otrabajo` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_prefactura`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_prefactura` (
  `id_prefactura` INT(11) NOT NULL ,
  `fecha` DATE NULL DEFAULT NULL ,
  `tb_ordenLiquidacion_id_liquidacion` INT(11) NOT NULL ,
  `usuario` VARCHAR(20) NULL DEFAULT NULL ,
  `cliente` VARCHAR(45) NULL DEFAULT NULL ,
  `direccion` VARCHAR(45) NULL DEFAULT NULL ,
  `RUC` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_prefactura`) ,
  CONSTRAINT `fk_tb_prefactura_tb_ordenLiquidacion1`
    FOREIGN KEY (`tb_ordenLiquidacion_id_liquidacion` )
    REFERENCES `grupodaw`.`tb_ordenliquidacion` (`id_liquidacion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_prefactura_tb_ordenLiquidacion1` ON `grupodaw`.`tb_prefactura` (`tb_ordenLiquidacion_id_liquidacion` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_detalleprefactura`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_detalleprefactura` (
  `id` INT(11) NOT NULL ,
  `tb_prefactura_id_prefactura` INT(11) NULL DEFAULT NULL ,
  `item` INT(11) NULL DEFAULT NULL ,
  `descripcion` VARCHAR(200) NULL DEFAULT NULL ,
  `precio unitario` DOUBLE NULL DEFAULT NULL ,
  `precio total` DOUBLE NULL DEFAULT NULL ,
  `cantidad` INT(11) NULL DEFAULT NULL ,
  `subtotal` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_tb_detallePreFactura_tb_prefactura`
    FOREIGN KEY (`tb_prefactura_id_prefactura` )
    REFERENCES `grupodaw`.`tb_prefactura` (`id_prefactura` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_detallePreFactura_tb_prefactura` ON `grupodaw`.`tb_detalleprefactura` (`tb_prefactura_id_prefactura` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_equipohorometro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_equipohorometro` (
  `id_horometro` INT(11) NOT NULL ,
  `horas` INT(11) NULL DEFAULT NULL ,
  `fecha` DATE NULL DEFAULT NULL ,
  `tb_equipo_nrotarjeta` VARCHAR(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_horometro`) ,
  CONSTRAINT `fk_tb_equipoHorometro_tb_equipo`
    FOREIGN KEY (`tb_equipo_nrotarjeta` )
    REFERENCES `grupodaw`.`tb_equipo` (`nrotarjeta` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_equipoHorometro_tb_equipo` ON `grupodaw`.`tb_equipohorometro` (`tb_equipo_nrotarjeta` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_especialidades`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_especialidades` (
  `id_especialidad` INT(11) NOT NULL ,
  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,
  `costoDia` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`id_especialidad`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_estado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_estado` (
  `idtb_estado` INT(11) NOT NULL ,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL ,
  PRIMARY KEY (`idtb_estado`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_tecnicos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_tecnicos` (
  `id_tecnicos` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NULL DEFAULT NULL ,
  `ape_pat` VARCHAR(45) NULL DEFAULT NULL ,
  `ape_mat` VARCHAR(45) NULL DEFAULT NULL ,
  `fecha_ingreso` DATE NULL DEFAULT NULL ,
  `disponible` CHAR(1) NULL DEFAULT NULL ,
  `tb_especialidades_id_especialidad` INT(11) NULL DEFAULT NULL ,
  `supervisor` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_tecnicos`) ,
  CONSTRAINT `fk_tb_tecnicos_tb_especialidades`
    FOREIGN KEY (`tb_especialidades_id_especialidad` )
    REFERENCES `grupodaw`.`tb_especialidades` (`id_especialidad` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_tecnicos_tb_tecnicos`
    FOREIGN KEY (`supervisor` )
    REFERENCES `grupodaw`.`tb_tecnicos` (`id_tecnicos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_tecnicos_tb_especialidades` ON `grupodaw`.`tb_tecnicos` (`tb_especialidades_id_especialidad` ASC) ;

CREATE INDEX `fk_tb_tecnicos_tb_tecnicos` ON `grupodaw`.`tb_tecnicos` (`supervisor` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_ordentrabajo_has_tb_tecnicos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_ordentrabajo_has_tb_tecnicos` (
  `tb_ordenTrabajo_id_otrabajo` INT(11) NOT NULL ,
  `tb_tecnicos_id_tecnicos` INT(11) NOT NULL ,
  `dias` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_ordenTrabajo_id_otrabajo`, `tb_tecnicos_id_tecnicos`) ,
  CONSTRAINT `fk_tb_ordenTrabajo_has_tb_tecnicos_tb_ordenTrabajo`
    FOREIGN KEY (`tb_ordenTrabajo_id_otrabajo` )
    REFERENCES `grupodaw`.`tb_ordentrabajo` (`id_otrabajo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_ordenTrabajo_has_tb_tecnicos_tb_tecnicos`
    FOREIGN KEY (`tb_tecnicos_id_tecnicos` )
    REFERENCES `grupodaw`.`tb_tecnicos` (`id_tecnicos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_ordenTrabajo_has_tb_tecnicos_tb_ordenTrabajo` ON `grupodaw`.`tb_ordentrabajo_has_tb_tecnicos` (`tb_ordenTrabajo_id_otrabajo` ASC) ;

CREATE INDEX `fk_tb_ordenTrabajo_has_tb_tecnicos_tb_tecnicos` ON `grupodaw`.`tb_ordentrabajo_has_tb_tecnicos` (`tb_tecnicos_id_tecnicos` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetes`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetes` (
  `id_paquetes` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(100) NULL DEFAULT NULL ,
  `duracion` INT(11) NULL DEFAULT NULL ,
  `marca` VARCHAR(45) NULL DEFAULT NULL ,
  `modelo` VARCHAR(45) NULL DEFAULT NULL ,
  `precio` DOUBLE NOT NULL ,
  PRIMARY KEY (`id_paquetes`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetes_has_tb_ordentrabajo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetes_has_tb_ordentrabajo` (
  `tb_paquetes_id_paquetes` INT(11) NOT NULL ,
  `tb_ordenTrabajo_id_otrabajo` INT(11) NOT NULL ,
  `precio` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_paquetes_id_paquetes`, `tb_ordenTrabajo_id_otrabajo`) ,
  CONSTRAINT `fk_tb_paquetes_has_tb_ordenTrabajo_tb_ordenTrabajo`
    FOREIGN KEY (`tb_ordenTrabajo_id_otrabajo` )
    REFERENCES `grupodaw`.`tb_ordentrabajo` (`id_otrabajo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_paquetes_has_tb_ordenTrabajo_tb_paquetes`
    FOREIGN KEY (`tb_paquetes_id_paquetes` )
    REFERENCES `grupodaw`.`tb_paquetes` (`id_paquetes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_paquetes_has_tb_ordenTrabajo_tb_paquetes` ON `grupodaw`.`tb_paquetes_has_tb_ordentrabajo` (`tb_paquetes_id_paquetes` ASC) ;

CREATE INDEX `fk_tb_paquetes_has_tb_ordenTrabajo_tb_ordenTrabajo` ON `grupodaw`.`tb_paquetes_has_tb_ordentrabajo` (`tb_ordenTrabajo_id_otrabajo` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetesactividades`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetesactividades` (
  `id_actividades` INT(11) NOT NULL ,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL ,
  `precio` DOUBLE NOT NULL ,
  PRIMARY KEY (`id_actividades`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetes_has_tb_paquetesactividades`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetes_has_tb_paquetesactividades` (
  `tb_paquetes_id_paquetes` INT(11) NOT NULL ,
  `tb_paquetesActividades_id_actividades` INT(11) NOT NULL ,
  `precio` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_paquetes_id_paquetes`, `tb_paquetesActividades_id_actividades`) ,
  CONSTRAINT `fk_tb_paquetes_has_tb_paquetesActividades_tb_paquetes1`
    FOREIGN KEY (`tb_paquetes_id_paquetes` )
    REFERENCES `grupodaw`.`tb_paquetes` (`id_paquetes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_paquetes_has_tb_paquetesActividades_tb_paquetesActivida1`
    FOREIGN KEY (`tb_paquetesActividades_id_actividades` )
    REFERENCES `grupodaw`.`tb_paquetesactividades` (`id_actividades` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_paquetes_has_tb_paquetesActividades_tb_paquetes1` ON `grupodaw`.`tb_paquetes_has_tb_paquetesactividades` (`tb_paquetes_id_paquetes` ASC) ;

CREATE INDEX `fk_tb_paquetes_has_tb_paquetesActividades_tb_paquetesActivida1` ON `grupodaw`.`tb_paquetes_has_tb_paquetesactividades` (`tb_paquetesActividades_id_actividades` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetesherramientas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetesherramientas` (
  `id_herramientas` INT(11) NOT NULL ,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL ,
  `precio` DOUBLE NOT NULL ,
  PRIMARY KEY (`id_herramientas`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetes_has_tb_paquetesherramientas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetes_has_tb_paquetesherramientas` (
  `tb_paquetes_id_paquetes` INT(11) NOT NULL ,
  `tb_paquetesHerramientas_id_herramientas` INT(11) NOT NULL ,
  `precio` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_paquetes_id_paquetes`, `tb_paquetesHerramientas_id_herramientas`) ,
  CONSTRAINT `fk_tb_paquetes_has_tb_paquetesHerramientas_tb_paquetes1`
    FOREIGN KEY (`tb_paquetes_id_paquetes` )
    REFERENCES `grupodaw`.`tb_paquetes` (`id_paquetes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_paquetes_has_tb_paquetesHerramientas_tb_paquetesHerrami1`
    FOREIGN KEY (`tb_paquetesHerramientas_id_herramientas` )
    REFERENCES `grupodaw`.`tb_paquetesherramientas` (`id_herramientas` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_paquetes_has_tb_paquetesHerramientas_tb_paquetes1` ON `grupodaw`.`tb_paquetes_has_tb_paquetesherramientas` (`tb_paquetes_id_paquetes` ASC) ;

CREATE INDEX `fk_tb_paquetes_has_tb_paquetesHerramientas_tb_paquetesHerrami1` ON `grupodaw`.`tb_paquetes_has_tb_paquetesherramientas` (`tb_paquetesHerramientas_id_herramientas` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetesmateriales`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetesmateriales` (
  `id_materiales` INT(11) NOT NULL ,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL ,
  `precio` DOUBLE NOT NULL ,
  PRIMARY KEY (`id_materiales`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_paquetes_has_tb_paquetesmateriales`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_paquetes_has_tb_paquetesmateriales` (
  `tb_paquetes_id_paquetes` INT(11) NOT NULL ,
  `tb_paquetesMateriales_id_materiales` INT(11) NOT NULL ,
  `precio` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_paquetes_id_paquetes`, `tb_paquetesMateriales_id_materiales`) ,
  CONSTRAINT `fk_tb_paquetes_has_tb_paquetesMateriales_tb_paquetes1`
    FOREIGN KEY (`tb_paquetes_id_paquetes` )
    REFERENCES `grupodaw`.`tb_paquetes` (`id_paquetes` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_paquetes_has_tb_paquetesMateriales_tb_paquetesMateriales1`
    FOREIGN KEY (`tb_paquetesMateriales_id_materiales` )
    REFERENCES `grupodaw`.`tb_paquetesmateriales` (`id_materiales` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_paquetes_has_tb_paquetesMateriales_tb_paquetes1` ON `grupodaw`.`tb_paquetes_has_tb_paquetesmateriales` (`tb_paquetes_id_paquetes` ASC) ;

CREATE INDEX `fk_tb_paquetes_has_tb_paquetesMateriales_tb_paquetesMateriales1` ON `grupodaw`.`tb_paquetes_has_tb_paquetesmateriales` (`tb_paquetesMateriales_id_materiales` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_solicitud_has_tb_estado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_solicitud_has_tb_estado` (
  `tb_solicitud_id_solicitud` INT(11) NOT NULL ,
  `tb_estado_idtb_estado` INT(11) NOT NULL ,
  `fecha` DATETIME NULL DEFAULT NULL ,
  `usuario` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`tb_solicitud_id_solicitud`, `tb_estado_idtb_estado`) ,
  CONSTRAINT `fk_tb_solicitud_has_tb_estado_tb_estado1`
    FOREIGN KEY (`tb_estado_idtb_estado` )
    REFERENCES `grupodaw`.`tb_estado` (`idtb_estado` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_solicitud_has_tb_estado_tb_solicitud1`
    FOREIGN KEY (`tb_solicitud_id_solicitud` )
    REFERENCES `grupodaw`.`tb_solicitud` (`id_solicitud` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_solicitud_has_tb_estado_tb_solicitud1` ON `grupodaw`.`tb_solicitud_has_tb_estado` (`tb_solicitud_id_solicitud` ASC) ;

CREATE INDEX `fk_tb_solicitud_has_tb_estado_tb_estado1` ON `grupodaw`.`tb_solicitud_has_tb_estado` (`tb_estado_idtb_estado` ASC) ;


-- -----------------------------------------------------
-- Table `grupodaw`.`tb_usuarios`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `grupodaw`.`tb_usuarios` (
  `id_usuario` INT(11) NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(45) NULL DEFAULT NULL ,
  `ape_pat` VARCHAR(45) NULL DEFAULT NULL ,
  `ape_mat` VARCHAR(45) NULL DEFAULT NULL ,
  `fecha_ingreso` DATE NULL DEFAULT NULL ,
  `login` VARCHAR(20) NULL DEFAULT NULL ,
  `password` VARCHAR(20) NULL DEFAULT NULL ,
  `tb_area_id_area` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id_usuario`) ,
  CONSTRAINT `fk_tb_usuarios_tb_area`
    FOREIGN KEY (`tb_area_id_area` )
    REFERENCES `grupodaw`.`tb_area` (`id_area` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;

CREATE INDEX `fk_tb_usuarios_tb_area` ON `grupodaw`.`tb_usuarios` (`tb_area_id_area` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
