/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.musicaview.emtity;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Cancion.findByTitulo", query = "SELECT c FROM Cancion c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "Cancion.findByCantante", query = "SELECT c FROM Cancion c WHERE c.cantante = :cantante"),
    @NamedQuery(name = "Cancion.findByFechaLanzamiento", query = "SELECT c FROM Cancion c WHERE c.fechaLanzamiento = :fechaLanzamiento"),
    @NamedQuery(name = "Cancion.findByPrecio", query = "SELECT c FROM Cancion c WHERE c.precio = :precio"),
    @NamedQuery(name = "Cancion.findByFavorito", query = "SELECT c FROM Cancion c WHERE c.favorito = :favorito"),
    @NamedQuery(name = "Cancion.findByFoto", query = "SELECT c FROM Cancion c WHERE c.foto = :foto")})
public class Cancion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "CANTANTE")
    private String cantante;
    @Column(name = "FECHA_LANZAMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Column(name = "FAVORITO")
    private Boolean favorito;
    @Column(name = "FOTO")
    private String foto;
    @JoinColumn(name = "ALBUM", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Album album;
    @JoinColumn(name = "GENERO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
