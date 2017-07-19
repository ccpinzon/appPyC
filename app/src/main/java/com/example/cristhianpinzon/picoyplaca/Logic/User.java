package com.example.cristhianpinzon.picoyplaca.Logic;

/**
 * Created by cristhianpinzon on 18/07/17.
 */

public class User {
    private int num_placa;

    private String tipo_carro;

    public User(int num_placa, String tipo_carro) {
        this.num_placa = num_placa;
        this.tipo_carro = tipo_carro;
    }

    public int getNum_placa() {
        return num_placa;
    }

    public void setNum_placa(int num_placa) {
        this.num_placa = num_placa;
    }

    public String getTipo_carro() {
        return tipo_carro;
    }

    public void setTipo_carro(String tipo_carro) {
        this.tipo_carro = tipo_carro;
    }

    @Override
    public String toString() {
        return "User{" +
                "num_placa=" + num_placa +
                ", tipo_carro='" + tipo_carro + '\'' +
                '}';
    }
}



