import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Keagan on 04/11/2015.
 */

public class Main extends JFrame {
    public JButton uploadButton;
    public JTextField imageName;
    public JLabel Pic;
    public JPanel JPanel1;
    private JSlider slider;
    private JTextField imageCount;
    private JTextField cellCount;
    private JTextArea Notes;
    private JButton Previous;
    private JButton Next;
    private JButton DisplayTree;
    private JButton radialTree;
    private JButton cellCountButton;
    private JButton saveNotesButton;
    private JTree tree;
    int ArrayCounter = 0;
    private int count = 1;
    int SecondCounter = 0;
    int labelStore = 0;


    final static ArrayList<String> originalCells = new ArrayList<String>();
    final static ArrayList<String> newCells = new ArrayList<String>();
    final static ArrayList<String> newCellsCopy = new ArrayList<>();
    final static ArrayList<String> CellCounting = new ArrayList<>();
    final static ArrayList<Integer> link = new ArrayList<Integer>();

    final HashMap<Integer, ArrayList<String>> sections = new HashMap<Integer, ArrayList<String>>();

    public Main() {

        setContentPane(JPanel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //removes 1 form the slider
        Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (slider.getValue() > 0) {
                    slider.setValue(slider.getValue() - 1);
                } else {
                    slider.setValue(0);
                }

            }
        });

        /*adds 1 to slider
         *pairs cells with their created slides via hash map
         */
        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelStore++;

                slider.setValue(slider.getValue() + 1);
                if (newCells.size() != 0) {

                    newCellsCopy.add(".");

                }
                ArrayList<String> elements = new ArrayList<String>();
                for (int i = 0; i < newCellsCopy.size(); i++) {
                    String element = newCellsCopy.get(i);
                    if (!element.equals(".")) {
                        elements.add(element);
                    }
                    if (element.equals(".")) {
                        sections.put(slider.getValue() - 1, elements);
                        elements = new ArrayList<String>();

                    }

                }
            }

        });

        cellCountButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                for (int m = 0; m < originalCells.size(); m++) {
                    if (!CellCounting.contains(originalCells.get(m))) {
                        CellCounting.add(originalCells.get(m));

                    }
                }
                Collections.sort(CellCounting);



                int k = 0;
                int j = 0;
                int removeCells = 0;
                //gets cell count by using the original and new cells
                for (int i = 0; i < CellCounting.size(); i++) {


                    if (newCells.size() > j && CellCounting.get(k).equals(newCells.get(j).split("\\.", 2)[0])) {

                        if (newCells.size() > j && i < CellCounting.size() && (newCells.get(j).split("\\.", 2)[0]).equals(newCells.get(j + 1).split("\\.", 2)[0])) {
                            j++;
                        }
                        j++;
                        removeCells++;
                    }

                    k++;
                }

                int CellCount = (CellCounting.size() + newCells.size()) - removeCells;

                cellCount.setText(String.valueOf("Cell Count: " + CellCount));
            }
        });



        //saves the user notes, name depends on what slide theyre on
        saveNotesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldVal = Notes.getText();
                try {
                    int sliderValue = slider.getValue();


                    PrintWriter writer = new PrintWriter("Notes for slide " + sliderValue + ".txt", "UTF-8");
                    writer.println(textFieldVal);
                    writer.close();
                } catch (IOException j) {
                    j.printStackTrace();
                }
            }
        });

        //launches radial tree
        radialTree.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                Container content = frame1.getContentPane();
                content.add(new radialTree());
                frame1.pack();
                frame1.setVisible(true);

            }

        });


        //button to upload images
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //gets file of images and adds to array
                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);


                File folder = f.getSelectedFile();
                final File[] listOfFiles = folder.listFiles();
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {



                        //sets parameters for the slider
                        slider.setMaximum(listOfFiles.length - 1);

                        slider.setMajorTickSpacing(10);
                        slider.setMinorTickSpacing(1);
                        slider.setPaintLabels(true);
                        slider.setPaintTicks(true);


                        //displays no. of images
                        int n = listOfFiles.length;
                        imageCount.setText("Number of Images: " + n);

                        //links slider to Jlabel Pic to display the images depending on the value of the slider
                        slider.addChangeListener(new ChangeListener() {

                            public void stateChanged(ChangeEvent event) {


                                BufferedImage img = null;
                                try {
                                    img = ImageIO.read(new File(listOfFiles[slider.getValue()].getAbsolutePath()));
                                } catch (IOException u) {
                                }


                                BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                                Graphics g = img2.getGraphics();
                                g.drawImage(img, 0, 0, null);
                                g.dispose();


                                imageName.setText(listOfFiles[slider.getValue()].getName());


                                Pic.setIcon(new ImageIcon(img2));
                                Pic.validate();
                                Pic.repaint();


                            }


                        });

                    }


                }
                slider.setValue(0);

            }


        });




        Pic.addMouseListener(new MouseAdapter() {

            Map<Integer, JTextField> map = new HashMap<Integer, JTextField>();

            @Override
            public void mousePressed(MouseEvent e) {

                String strI = String.valueOf(count);
                final JTextField text = new JTextField(strI);

                map.put(count, text);

                //set label features
                text.setBorder(null);

                text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                text.setForeground(Color.red);

                text.setBounds(e.getX() - 2, e.getY() - 10, 20, 20);//so the label appears at end of pointer
                Pic.add(text);


                Pic.revalidate();
                Pic.repaint();

                pack();//so our frame resizes to compensate for new components

                DragListener drag = new DragListener();

                text.addMouseListener(drag);
                text.addMouseMotionListener(drag);
                count++;


                text.setEditable(false);
                text.setOpaque(false);

                //clicking label makes text editable
                text.addMouseListener(new MouseAdapter() {
                    public int status = 0;

                    @Override

                    public void mousePressed(MouseEvent e) {
                        if (status != 1) {

                            status++;
                            text.setEditable(true);

                        }

                        else if (status == 1) {

                            status = 0;
                            text.setEditable(false);

                        }

                    }


                });

                //makes text green if editable
                final FocusListener highlighter = new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {
                        text.setOpaque(true);
                        e.getComponent().setBackground(Color.GREEN);

                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        e.getComponent().setBackground(UIManager.getColor("TextField.background"));
                        text.setOpaque(false);
                    }
                };
                text.addFocusListener(highlighter);


                Next.addActionListener(new ActionListener() {


                    @Override
                    public void actionPerformed(ActionEvent e) {

                        /*parameters for when to only start
                        *adding new cells when the user has
                        * added original cells
                         */
                        if (labelStore != 0) {
                            if (!newCells.contains(text.getText())) {
                                newCells.add(text.getText());

                                link.add(slider.getValue());


                            }
                            for (String s : originalCells) {

                                newCells.remove(s);

                            }

                        }



                        if (labelStore == 0) {
                            originalCells.add(text.getText());

                            ArrayCounter++;
                        }

                        //makes a copy of the new cells
                        for (int m = 0; m < newCells.size(); m++) {
                            if (!newCellsCopy.contains(newCells.get(m))) {
                                newCellsCopy.add(newCells.get(m));
                                link.add(slider.getValue() - 1);
                            }
                        }

                        //sorts the lists for comparisons
                        Collections.sort(originalCells);
                        Collections.sort(newCells);
                    }

                });

                //stops multiple windows opening
                if (SecondCounter < 1) {
                    SecondCounter++;
                    //opens new window sand runs the JTree visualization
                    DisplayTree.addActionListener(new ActionListener() {


                        @Override
                        public void actionPerformed(ActionEvent e) {


                            JFrame frameG2 = new JFrame("Cell Tree");
                            frameG2.setSize(400, 900);
                            frameG2.setVisible(true);

                            frameG2.setBackground(Color.gray);

                            DefaultMutableTreeNode root = new DefaultMutableTreeNode("cells");
                            tree = new JTree(root);
                            frameG2.add(tree);
                            int i = 0;
                            //goes through orignal cells list and adds it to root
                            for (int n = 0; n < originalCells.size(); n++) {
                                DefaultMutableTreeNode cells = new DefaultMutableTreeNode(originalCells.get(n));

                                root.add(cells);

                                //go through jtree elements


                                Enumeration search = root.postorderEnumeration();


                                while (search.hasMoreElements() && (i < newCells.size())) {

                                    //should compare each element to a new cells
                                    //new cells consists of double numbers like 1.1,1.2,2.1 etc
                                    //so i split it before the "." so 1.1 is 1
                                    //orignalcells consists of whole numbers like 1, 2, 3
                                    //want to make 1.1 child of 1 etc.

                                    if (cells.toString().equals(newCells.get(i).toString().split("\\.", 2)[0])) {
                                        DefaultMutableTreeNode NewCells = new DefaultMutableTreeNode(newCells.get(i));

                                        cells.add(NewCells);

                                        i++;

                                    }
                                    search.nextElement();

                                }

                            }

                            tree = new JTree(root);
                            frameG2.add(tree);

                            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                            //Listen for when the selection changes.
                            tree.addMouseListener(new MouseAdapter() {


                                @Override
                                public void mousePressed(MouseEvent e) {

                                    //Returns the last path element of the selection.
                                    //This method is useful only when the selection model allows a single selection.
                                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                                    if (node == null)
                                        //Nothing is selected.
                                        return;

                                    Object nodeInfo = node.getUserObject();

                                    if (node.isLeaf()) {
                                        for (int key : sections.keySet()) {
                                            if (sections.get(key).contains(node.getUserObject().toString())) {

                                                slider.setValue(key);
                                            }
                                        }

                                    }

                                }

                            });

                        }

                    });


                }

            }


        });




        JPanel1.setPreferredSize(new Dimension(1200, 800));
        setVisible(true);
        pack();


    }

    public ArrayList<String> getList() {
        return originalCells;

    }

    public ArrayList<String> getList2() {
        return newCells;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}




