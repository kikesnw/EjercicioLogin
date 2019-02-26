package com.example.ejerciciologin.Utilidades;

public class Utilidades {
    public static String nombre_tabla="usuarios";
    public static final String tabla_usuario="usuario";
    public static final String tabla_id="id";
    public static final String tabla_contraseña="contraseña";
    public static final String tabla_sexo="sexo";
    public static final String tabla_nacionalidad="nacionalidad";
    public static final String crear_tabla_usuario="CREATE TABLE "+nombre_tabla+"("+tabla_id+" INTEGER primary key autoincrement,"+
            tabla_usuario+" TEXT,"+tabla_contraseña+"" +
            " text,"+tabla_nacionalidad+" TEXT,"+tabla_sexo+" TEXT)";

}
