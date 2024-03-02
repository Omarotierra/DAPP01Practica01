package Hibernate;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "empleadosuv")
public class Venta {
    @Id
    @Column(name = "idventa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "venta_idventa_seq")
    @SequenceGenerator(name = "venta_idventa_seq", 
            sequenceName ="venta_idventa_seq",
            initialValue =1,
            allocationSize= 1)
    private long id;
    
     @Column
    private Date fecha;
      @Column
    private String cliente;
       @Column
    private Double total;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
