package pages;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Product {
    private String nombre;
    private String url;
    private String precio;

    public Product(String nombre, String url, String precio) {
        this.nombre = nombre;
        this.url = url;
        this.precio = precio;
    }

    public static void writeProductsToFile(List<Product> productos, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product producto : productos) {
                writer.write(producto.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Nombre de Articulo='" + nombre + '\'' +
                ", Precio del Articulo=" + precio +
                ", Link='" + url + '\'';
    }
}

