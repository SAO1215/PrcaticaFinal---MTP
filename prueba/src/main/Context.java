package main;

public class Context {
    // este es el contexto
    private Descuento descuento;

    public Context(Descuento descuento){
        this.descuento = descuento;
    }

    public double aplicarDescuento(double precio){
        return descuento.aplicarDescuento(precio);
    }
}
