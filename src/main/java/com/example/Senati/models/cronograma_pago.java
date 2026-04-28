package com.example.Senati.models;

import com.example.Senati.models.cronograma_pago;
import java.sql.Date;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "cronograma_pago")
public class cronograma_pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mes_pago;
    private Double monto;
    private Date fecha_vencimiento;
    private Integer id_estudiante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_pago() {
        return fecha_vencimiento;
    }

    public void setFecha_pago(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public String getMes_pago() {
        return mes_pago;
    }

    public void setMes_pago(String mes_pago) {
        this.mes_pago = mes_pago;
    }
}