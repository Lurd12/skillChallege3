/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metaphorce.skillchallenge3.controller;

import com.metaphorce.skillchallenge3.exception.PeliculaNotFoundException;
import com.metaphorce.skillchallenge3.model.Pelicula;
import com.metaphorce.skillchallenge3.service.PeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author lur
 */
@RestController
public class PeliculaController {
	@Autowired
	PeliculaService peliculaService;
	
	/**
	 * Agrega una pelicula a la base de datos
	 */
	@PostMapping("/peliculas")
	public ResponseEntity<?> agregarPelicula(@RequestBody Pelicula pelicula){
		try{
			return new ResponseEntity<>(peliculaService.agregarPelicula(pelicula), HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	/**
	 * Elimina una pelicula por su id
	 * 
	 */
	@DeleteMapping("/pelicula/{id}")
	public ResponseEntity<?>  eliminarPelicula(@PathVariable int id){
		peliculaService.eliminarPelicula(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las peliculas
	 * 
	 */	
	@GetMapping("/peliculas")
	public ResponseEntity<List<Pelicula>> obtenerPeliculas(){
		return new ResponseEntity<>(peliculaService.obtenerPeliculas(), HttpStatus.OK);
	}
	
		
	/**
	 * Obtiene todas la pelicula disponibles
	 * 
	 */
	@GetMapping("/peliculas/disponible")
	public ResponseEntity<List<Pelicula>> obtenerPeliculasDisponibles(){
		return new ResponseEntity<>(peliculaService.obtenerPeliculasPorDisponibilidad(true), HttpStatus.OK);
	}
	
	
	/**
	 * Obtiene todas la pelicula  no disponibles
	 * 
	 */
	@GetMapping("/peliculas/nodisponible")
	public ResponseEntity<List<Pelicula>> obtenerPeliculasNoDisponibles(){
		return new ResponseEntity<>(peliculaService.obtenerPeliculasPorDisponibilidad(false), HttpStatus.OK);
	}
	
	
	
	/**
	 * Obtiene una pelicula por su id
	 * 
	 */
	@GetMapping("/pelicula/{id}")
	public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable
		int id){
		try {
			return new ResponseEntity<>(peliculaService.obtenerPeliculaPorId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * Marca una pelicula como disponible
	 * 
	 */
	@PutMapping("/pelicula/{id}")
	public ResponseEntity<?> marcharComoDisponible(@PathVariable int id){
		try {
			peliculaService.marcarPeliculaComoDisponible(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * Modifica una pelicula
	 * 
	 */
	@PutMapping("pelicula")
	public ResponseEntity<?> modificarPelicula(@RequestBody Pelicula pelicula){
		try {
			return new ResponseEntity<>(peliculaService.modificarPelicula(pelicula), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	
}
