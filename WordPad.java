import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.*;


public class WordPad implements ActionListener {
    JFrame frame;
    JTextArea textarea;
    JMenuBar jMenuBar;


    WordPad(){
        frame = new JFrame("Word Pad");


        frame.setVisible(true);


        textarea = new JTextArea();
        jMenuBar = new JMenuBar();


        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");


        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");
        JMenuItem printFile = new JMenuItem("Print File");
        JMenuItem newFile = new JMenuItem("New File");

        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);



        JMenuItem Cut = new JMenuItem("Cut");
        JMenuItem Copy = new JMenuItem("Copy");
        JMenuItem Paste = new JMenuItem("Paste");
        JMenuItem Close = new JMenuItem("Close");

        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        edit.add(Close);

        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        Close.addActionListener(this);



        jMenuBar.add(file);
        jMenuBar.add(edit);


        frame.setJMenuBar(jMenuBar);


        frame.add(textarea);


        frame.setSize(800,800);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        WordPad object = new WordPad();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call = e.getActionCommand();
        if(call == "New File"){
            textarea.setText("");
        }else if(call == "Cut"){
            textarea.cut();
        }else if(call == "Copy" ){
            textarea.copy();
        }else if(call == "Paste"){
            textarea.paste();
        }else if(call == "Close"){
            frame.setVisible(false);
        }else if(call == "Save File"){
            JFileChooser jFileChooser = new JFileChooser("C");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writter = null;
                try {
                    writter = new BufferedWriter(new FileWriter(file,false));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.write(textarea.getText());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.flush();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    writter.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else if(call == "Open File"){
            JFileChooser jFileChooser1 = new JFileChooser("C:");
            int ans = jFileChooser1.showOpenDialog(null);
            if(ans == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser1.getSelectedFile().getAbsolutePath());

                    String s1 = "",s2="";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while(s1 == String.valueOf(bufferedReader.readLine() != null)){
                        s2 += s1+"\n";
                    }
                    textarea.setText(s2);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }else if(call == "Print File"){
            try {
                textarea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
