/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.extract;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.io.OutputStream;
import java.io.IOException;

public class TextAreaOutputStreamSmtp extends OutputStream {
    private JTextArea textArea;

    public TextAreaOutputStreamSmtp(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // Redirects data to the text area (convert byte to character)
        textArea.append(String.valueOf((char) b));
        // Scrolls to the end of the document to show the latest output
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        // Convert bytes to String and append to the text area
        textArea.append(new String(b, off, len));
        // Scroll to the end of the document
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
