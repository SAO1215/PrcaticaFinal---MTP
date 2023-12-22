package main;

public class DescuentoNavidad implements Descuento{
    @Override
    public double aplicarDescuento(double precio) {
        return precio * 0.3;
    }
}