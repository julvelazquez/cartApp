package org.generation.cartapp.Model;

import com.orm.SugarRecord;

/**
 * Created by RigoBono on 10/11/18.
 */

public class Persona extends SugarRecord<Persona> {

    private Integer idPersona;
    private String nombre;
    private String paterno;

    private String materno;

    private String correo;

    private String qr;

    public Persona(Integer idPersona, String nombre, String paterno, String materno, String correo, String qr) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.correo = correo;
        this.qr = qr;
    }

    public Persona() {
    }


    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}
