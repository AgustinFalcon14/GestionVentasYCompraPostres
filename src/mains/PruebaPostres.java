package mains;

import capaLogica.Postre;
import capaLogica.Postres;
public class PruebaPostres {

	public static void main(String[] args)
	{
		System.out.println("=== PRUEBA DEL DICCIONARIO DE POSTRES ===\n");

        // Crear el diccionario
        Postres diccionario = new Postres();

        // --- AGREGAR ---
        System.out.println("--- Agregando postres ---");
        Postre p1 = new Postre("101L", "Tiramisu", 850.0, true);
        Postre p2 = new Postre("102L", "Cheesecake", 750.0, true);
        Postre p3 = new Postre("103L", "Mousse de chocolate", 600.0, false);
        Postre p4 = new Postre("104L", "Flan", 500.0, true);

        diccionario.agregar(p1.getCodigo(), p1);
        diccionario.agregar(p2.getCodigo(), p2);
        diccionario.agregar(p3.getCodigo(), p3);
        diccionario.agregar(p4.getCodigo(), p4);

        System.out.println("4 postres agregados correctamente.");

        // --- MOSTRAR TODOS ---
        System.out.println("\n--- Todos los postres en el diccionario ---");
        diccionario.mostrarTodos();

        // --- MEMBER ---
        System.out.println("\n--- Verificando existencia (member) ---");
        String codigoExistente   = "102L";
        String codigoInexistente = "999L";
        System.out.println("¿Existe postre con código " + codigoExistente   + "? " + diccionario.member(codigoExistente));
        System.out.println("¿Existe postre con código " + codigoInexistente + "? " + diccionario.member(codigoInexistente));

        // --- OBTENER ---
        System.out.println("\n--- Obteniendo un postre por código ---");
        Postre obtenido = diccionario.obtener(codigoExistente);
        if (obtenido != null) {
            System.out.println("Postre obtenido (código " + codigoExistente + "):" + obtenido);
        }

        Postre noEncontrado = diccionario.obtener(codigoInexistente);
        System.out.println("Postre obtenido (código " + codigoInexistente + "): " + noEncontrado);

        // --- ELIMINAR ---
        System.out.println("\n--- Eliminando postre con código " + p4.getCodigo() + " ---");
        diccionario.eliminar(p4.getCodigo());
        System.out.println("¿Sigue existiendo código " + p4.getCodigo() + " tras eliminar? " + diccionario.member(p4.getCodigo()));

        // --- ESTADO FINAL ---
        System.out.println("\n--- Estado final del diccionario ---");
        diccionario.mostrarTodos();

        System.out.println("\n=== FIN DE LA PRUEBA ===");

	}

}
