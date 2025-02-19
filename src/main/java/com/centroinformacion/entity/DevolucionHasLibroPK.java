package com.centroinformacion.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class DevolucionHasLibroPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idDevolucion;
	private int idLibro;
}