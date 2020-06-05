/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.musicaview.emtity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Javi
 */
@Entity
@Table(name = "CANCION")
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c"),
    @NamedQuery(name = "Cancion.findById", query = "SELECT c FROM Cancion c WHERE c.id = :id"),
    @NamedQuery(name = "Cancion.findByCantante", query = "SELECT c FROM Cancion c WHERE c.cantante = :cantante"),
    @NamedQuery(name = "Cancion.findByFavorito", query = "SELECT c FROM Cancion c WHERE c.favorito = :favorito"),
    @NamedQuery(name = "Cancion.findByFechaLanzamiento", query = "SELECT c FROM Cancion c WHERE c.fechaLanzamiento = :fechaLanzamiento"),
    @NamedQuery(name = "Cancion.findByFoto", query = "SELECT c FROM Cancion c WHERE c.foto = :foto"),
    @NamedQuery(name = "Cancion.findByPrecio", query = "SELECT c FROM Cancion c WHERE c.precio = :precio"),
    @NamedQuery(name = "Cancion.findByTitulo", query = "SELECT c FROM Cancion c WHERE c.titulo = :titulo")})
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CANTANTE")
    private String cantante;
    @Column(name = "FAVORITO")
    private Short favorito;
    @Column(name = "FECHA_LANZAMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;
    @Column(name = "FOTO")
    private String foto;
    @Column(name = "PRECIO")
    private Long precio;
    @Column(name = "TITULO")
    private String titulo;
    @JoinColumn(name = "ALBUM", referencedColumnName = "ID")
    @ManyToOne
    private Album album;
    @JoinColumn(name = "GENERO", referencedColumnName = "ID")
    @ManyToOne
    private Genero genero;

    public Cancion() {
    }

    public Cancion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public Short getFavorito() {
        return favorito;
    }

    public void setFavorito(Short favorito) {
        this.favorito = favorito;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.musicaview.emtity.Cancion[ id=" + id + " ]";
    }
    
}
