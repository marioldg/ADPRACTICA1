package ControlFicheros;

import Entidades.Carnet;
import Entidades.Entrenador;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class escribirFicheros {
    public static void insertarCredenciales(String nombre, String password, String tipo, int id) {
        String linea = nombre + "  " + password + "  " + tipo + "  " + id;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("src/main/java/files/Credenciales.txt", true));
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *  Metodo para poder escribir archivos con formato XML,
     * comprobamos si la ruta que nos pasan esta vacia, de ser asi, ponemos directamente src/main/Files/
     */

    public static void escribirXML(String extension, String nombre, String ruta, String contenido) {
        // Crear la ruta completa del archivo
        if (ruta.isEmpty()) {
            ruta = "src/main/Files/";
        }
        String rutaCompleta = ruta + "/" + nombre + extension;
        File f = new File(rutaCompleta);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            writer.write(contenido);
            System.out.println("Archivo XML creado en: " + rutaCompleta);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static void exportarCarnetXML(Entrenador entrenador, Carnet c){
        try {
            // Crear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Crear nodo raíz <carnet>
            Element root = document.createElement("carnet");
            document.appendChild(root);

            // Crear nodo <id>
            Element idElement = document.createElement("id");
            idElement.appendChild(document.createTextNode(String.valueOf(c.getIdEntrenador())));
            root.appendChild(idElement);

            // Crear nodo <fechaexp>
            Element fechaExpElement = document.createElement("fechaexp");
            fechaExpElement.appendChild(document.createTextNode(c.getFechaExpedicion().toString()));
            root.appendChild(fechaExpElement);

            // Crear nodo <entrenador>
            Element entrenadorElement = document.createElement("entrenador");
            root.appendChild(entrenadorElement);

            // Crear nodo <nombre> dentro de <entrenador>
            Element nombreElement = document.createElement("nombre");
            nombreElement.appendChild(document.createTextNode(entrenador.getNombre()));
            entrenadorElement.appendChild(nombreElement);

            // Crear nodo <nacionalidad> dentro de <entrenador>
            Element nacionalidadElement = document.createElement("nacionalidad");
            nacionalidadElement.appendChild(document.createTextNode(entrenador.getNacionalidad()));
            entrenadorElement.appendChild(nacionalidadElement);

            // Crear nodo <puntos>
            Element puntosElement = document.createElement("puntos");
            puntosElement.appendChild(document.createTextNode(String.format("%.2f", c.getPuntos())));
            root.appendChild(puntosElement);

            // Configurar el Transformer para guardar el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formatear con sangría
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Guardar en archivo
            String ruta = "src/main/java/Files/";
            String nombreArchivo = "carnet_" + entrenador.getNombre() + ".xml";
            File file = new File(ruta + nombreArchivo);

            // Asegurar que la carpeta exista
            new File(ruta).mkdirs();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Archivo XML guardado en: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

