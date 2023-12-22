package main;

public class DescuentoBlackFriday implements Descuento {
    @Override
    public double aplicarDescuento(double precio) {
        return precio * 0.5;
    }
}
