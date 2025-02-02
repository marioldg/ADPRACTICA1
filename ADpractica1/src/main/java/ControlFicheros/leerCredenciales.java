package ControlFicheros;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;


public class leerCredenciales {

    /**
     * metodo para comprobar que no haya usuarios repetidos en Credenciales.txt
     * @param usuario
     * @return
     */

    public static boolean comprobarNuevo(String usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/Credenciales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  ");
                String idUsuario = parts[0]; // primer elemento es el nombre
                if (idUsuario.equals(usuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * metodo devuelve array con el nombre de los paises del XML
     * @return
     */
    public static ArrayList<String> nombresPaises() {
        ArrayList<String> paisesNombres = new ArrayList<>(); // Crear una lista para almacenar los paisesNombres

        try {
            // Cargar el archivo XML
            File archivo = new File("src/main/java/files/paises.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);

            // Normalizar el documento
            doc.getDocumentElement().normalize();

            // Obtener la lista de todos los elementos <nombre> dentro de <pais>
            NodeList listaNombres = doc.getElementsByTagName("nombre");

            for (int i = 0; i < listaNombres.getLength(); i++) {
                // Obtener el contenido de cada nodo <nombre>
                String nombrePais = listaNombres.item(i).getTextContent();
                paisesNombres.add(nombrePais); // Añadir el nombre al ArrayList
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paisesNombres; // Retornar la lista de paisesNombres
    }



    /**
     * metodo devuelve el rol en caso de que las credenciales sean correctas
     * @param username
     * @param password
     * @return
     */
    public static String controlLogIn(String username, String password) {
        boolean validar = false;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/Credenciales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  "); //divide la linea con comas
                if (parts[0].equalsIgnoreCase(username) && parts[1].equals(password)) {
                    //si las credenciales coinciden
                    String rol = parts[2];
                    System.out.println("Login exitoso. \nHola " + username);
                    validar = true;
                    return rol;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "nulo";
    }


    /**
     * metodo para comprobar si ya existe un
     * @return
     */

    public static String nombreAdminTorneo() {
        boolean validar = false;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/Credenciales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  "); //divide la linea con comas
                    String nomAdminT = parts[0];
                    return nomAdminT;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * metodo para incrementar el id de cada entrenador
     * @return
     */

    public static int idEntrenador(){
        int cont = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/Credenciales.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  ");
                String rol = parts[2]; // primer elemento es el nombre
                if (rol.equalsIgnoreCase("Entrenador")) {
                    cont++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cont;
    }


    public static int idAdminTorneo(){
        int cont = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/files/Credenciales.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("  ");
                String rol = parts[2]; // primer elemento es el nombre
                if (rol.equalsIgnoreCase("AdminTorneo")) {
                    cont++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cont;
    }


}

