package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.entity.Libro;


public interface LibroRepository extends JpaRepository<Libro, Integer> {

	Libro findBySerie(String serie);
	public List<Libro> findByTituloIgnoreCase(String titulo);
	
	@Query("Select p from Libro p where p.titulo like ?1")
	public List<Libro> listPorTituloLike(String filtro);
	
	
	//Consulta
	@Query("select p from Libro p where "
		    + "( p.estado = ?1)  and "
		    + "( ?2 = -1  or p.categoriaLibro.idDataCatalogo = ?2 ) and "
		    + "( ?3 = -1  or p.tipoLibro.idDataCatalogo = ?3 ) and "
		    + "( p.titulo like ?4 ) and"
		    + "( p.serie like ?5 )and"
		    + "( ?6 = -1 or p.anio = ?6 ) "
		)
	public abstract List<Libro> listaConsultaLibro(int estado, int idCategoria, int idTipo,  String titulo, String serie, int anio);
	
	//PC4
	@Query("SELECT a FROM Autor a, LibroHasAutor lha WHERE a.idAutor = lha.autor.idAutor AND lha.libro.idLibro = ?1")
	public abstract List<Autor> traerAutorDeLibro(int idLibro);






	

	
	
	//PC4
	
}
