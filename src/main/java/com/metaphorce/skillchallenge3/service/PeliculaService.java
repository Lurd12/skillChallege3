/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.metaphorce.skillchallenge3.service;

import com.metaphorce.skillchallenge3.exception.PeliculaNotFoundException;
import com.metaphorce.skillchallenge3.model.Pelicula;
import com.metaphorce.skillchallenge3.repository.PeliculaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lur
 */
@Service
public class PeliculaService {
	@Autowired
	PeliculaRepository peliculaRepository;
	
	public Pelicula agregarPelicula(Pelicula pelicula){
		return peliculaRepository.save(pelicula);
	}

	public void eliminarPelicula(int id){
		peliculaRepository.deleteById(id);
	}
	
	public List<Pelicula> obtenerPeliculas(){
		return peliculaRepository.findAll() ;
	}
	
	public Pelicula obtenerPeliculaPorId(int id) throws Exception{
		return peliculaRepository.findById(id).orElseThrow(PeliculaNotFoundException::new);
	}
	
	public List<Pelicula> obtenerPeliculasPorDisponibilidad(boolean disponibilidad){
		return peliculaRepository.findAll().stream().filter(p -> p.isDisponible() == disponibilidad).toList();
	}
	
	
	public Pelicula marcarPeliculaComoDisponible(int id) throws Exception{
		Pelicula p = peliculaRepository.findById(id).orElseThrow(PeliculaNotFoundException::new);
		return peliculaRepository.save(p);
	}
	
	
	public Pelicula modificarPelicula(Pelicula pelicula) throws PeliculaNotFoundException {
		peliculaRepository.findById(pelicula.getId()).orElseThrow(PeliculaNotFoundException::new);
		return peliculaRepository.save(pelicula);
		
	}
	
	
	
	
	
}
