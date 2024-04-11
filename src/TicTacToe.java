import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600, boardHeight = 650; // Не забываем про 50 пикселей для текстовой панели сверху
    JFrame window = new JFrame(); // Создаём объект окна
    TicTacToe() {
        window.setVisible(true); // Окно является видимым!
        window.setSize(boardWidth, boardHeight); // Устанавливаем размер окна
        window.setResizable(false); // Запрещаем пользователю менять размер окна
    }
}
