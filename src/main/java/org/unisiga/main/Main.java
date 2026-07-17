package org.unisiga.main;

import org.unisiga.controller.InscripcionController;
import org.unisiga.model.*;
import org.unisiga.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView vista = new ConsoleView();
        InscripcionController controller = new InscripcionController();

        vista.desplegarMenu();
        
        System.out.println("[SISTEMA] El esqueleto está compilado. Comienza a implementar la lógica de negocio.");
    }
}
