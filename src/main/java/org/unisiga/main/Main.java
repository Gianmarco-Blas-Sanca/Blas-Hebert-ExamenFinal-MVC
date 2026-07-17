package org.unisiga.main;

import javax.swing.SwingUtilities;
import org.unisiga.view.FrmLogin;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmLogin().setVisible(true));
    }
}
