package main;

import java.sql.*;
import java.util.Scanner;

public class main {

    public static Connection ConectarBD(){
        Connection connection;
        String host = "jdbc:mysql://localhost:8889/";
        String user = "root";
        String pass = "root";
        String bd = "prueba";

        System.out.println("Conectando…");

        try {
            connection = DriverManager.getConnection(host+bd, user,pass);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return  connection;

    }

    public static void Desconeccion(Connection cb){
        try {
            cb.close();
            System.out.println("Desconectando... !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void CrearTablas(Connection con){
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("CREATE TABLE producto (nombre VARCHAR(25), precio INT)");
            stmt.executeUpdate("CREATE TABLE descuento (promocion VARCHAR(25), porcentaje FLOAT)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void InsetarDatos(Connection con){
        Statement stmt;
        try {
            stmt = con.createStatement();

            stmt.executeUpdate("INSERT INTO producto VALUES('Microhornito', 13)");
            stmt.executeUpdate("INSERT INTO producto VALUES('Terreneitor',  666)");
            stmt.executeUpdate("INSERT INTO producto VALUES('Paketaxo',  420)");
            stmt.executeUpdate("INSERT INTO producto VALUES('Belanova', 69)");
            stmt.executeUpdate("INSERT INTO producto VALUES('Champagne', 27)");

            stmt.executeUpdate("INSERT INTO descuento VALUES('BlackFriday', .5)");
            stmt.executeUpdate("INSERT INTO descuento VALUES('Navidad', .2)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static int ConsultaPrecio(Connection con, String nombre) {
        String sql = "";
        sql = "SELECT precio FROM producto WHERE nombre ='"+nombre+"'";
        Statement stmt;
        ResultSet rs;
        int precio = 0;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                precio = rs.getInt("precio");
            }

            return precio;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args) {
        Connection bd = ConectarBD();
        //CrearTablas(bd);
        //InsetarDatos(bd);
        //System.out.println("========== CONSULTAS TERMINADAS ===========");
        //Desconeccion(bd);

        int precio = ConsultaPrecio(bd, "Microhornito" );

        System.out.print("El festivo actual es BlackFriday/Navidad : ");
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();

        if(in.equals("BlackFriday")){
            Context context = new Context(new DescuentoBlackFriday());
            System.out.println("Precio con descuento: " + context.aplicarDescuento(precio));
        }else if(in.equals("Navidad")){
            Context context = new Context(new DescuentoNavidad());
            System.out.println("Precio con descuento: " + context.aplicarDescuento(precio));
        }
    }


}
