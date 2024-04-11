import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; // Не забываем про 50 пикселей для текстовой панели сверху
    JFrame window = new JFrame("Tic-Tac-Toe"); // Создаём объект окна
    JLabel textAbove = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;

    TicTacToe() {
        window.setVisible(true); // Окно является видимым!
        window.setSize(boardWidth, boardHeight); // Устанавливаем размер окна
        window.setResizable(false); // Запрещаем пользователю менять размер окна
        window.setLocationRelativeTo(null); // Устанавливаем окно по центру экрана
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Подключаем корректный выход из приложения
        window.setLayout(new BorderLayout()); // Установка параметров разметки

        textAbove.setBackground(Color.darkGray); // Установка фона
        textAbove.setForeground(Color.white); // Установка цвета шрифтов
        textAbove.setFont(new Font("Segoe UI", Font.BOLD, 50)); // Установка шрифта
        textAbove.setHorizontalAlignment(JLabel.CENTER); // Выравниваение строки
        textAbove.setText("Tic-Tac-Toe"); // Установка содержимого строки
        textAbove.setOpaque(true); // Установка непрозрачности для объекта

        textPanel.setLayout(new BorderLayout()); // Установка разметки для текстовой панели
        textPanel.add(textAbove); // Установка текста в текстовую панель
        window.add(textPanel, BorderLayout.NORTH); // Установка текстовой панели в окно

        boardPanel.setLayout(new GridLayout(3, 3)); // Установка разметки для игрового поля
        boardPanel.setBackground(Color.darkGray);
        window.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Segoe UI", Font.BOLD, 100));
                tile.addActionListener(e -> {
                    if (gameOver) return;
                    JButton tile1 = (JButton) e.getSource();
                    if (Objects.equals(tile1.getText(), "")) {
                        tile1.setText(currentPlayer);
                        checkWinner();
                        if (!gameOver) {
                            currentPlayer = Objects.equals(currentPlayer, playerX) ? playerO : playerX;
                            textAbove.setText("Turn: " + currentPlayer);
                        }

                    }
                });
                tile.setFocusable(false);
                boardPanel.add(tile);
            }
        }
    }

    private void checkWinner() {
        // Горизонтальная проверка
        for (int r = 0; r < 3; r++) {
            if (Objects.equals(board[r][0].getText(), "")) continue;
            if (Objects.equals(board[r][0].getText(), board[r][1].getText()) && Objects.equals(board[r][1].getText(), board[r][2].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        // Вертикальная проверка
        for (int c = 0; c < 3; c++) {
            if (Objects.equals(board[0][c].getText(), "")) continue;
            if (Objects.equals(board[0][c].getText(), board[1][c].getText()) && Objects.equals(board[1][c].getText(), board[2][c].getText())) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        // Диагональная проверка
        if (Objects.equals(board[0][0].getText(), board[1][1].getText()) && Objects.equals(board[1][1].getText(), board[2][2].getText()) && !Objects.equals(board[0][0].getText(), "")) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
        }
    }

    private void setWinner(JButton tile) {
        tile.setForeground(Color.GREEN);
        textAbove.setText(currentPlayer + " has won!");
    }
}
