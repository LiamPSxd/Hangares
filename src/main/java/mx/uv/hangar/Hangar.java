package mx.uv.hangar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hangar{   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer capacidad;
    private String ubicacion;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getCapacidad(){
        return capacidad;
    }

    public void setCapacidad(Integer capacidad){
        this.capacidad = capacidad;
    }

    public String getUbicacion(){
        return ubicacion;
    }

    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }

    public String toString(){
        return "{\"id\": \"" + this.getId() + "\",\"capacidad\": \"" + this.getCapacidad() + "\",\"ubicacion\": \"" + this.getUbicacion() + "\"},";
    }
}