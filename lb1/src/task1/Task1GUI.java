package task1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
public class Task1GUI extends JFrame {
    private JTextField classNameField;
    private JButton submitButton;

    public Task1GUI() {
        setTitle("Class Details");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1)); // Change the layout to GridLayout with variable rows

        JLabel instructionLabel = new JLabel("Enter the class name:");
        classNameField = new JTextField();
        submitButton = new JButton("Submit");

        mainPanel.add(instructionLabel);
        mainPanel.add(classNameField);
        mainPanel.add(submitButton);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Ensure vertical scrollbar is always visible
        add(scrollPane);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String className = classNameField.getText().trim();
                if (!className.isEmpty()) {
                    try {
                        Class<?> myClass = Class.forName(className);
                        showClassDetails(myClass);
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(Task1GUI.this, "Class was not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(Task1GUI.this, "Please enter a class name", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add window listener to handle closing the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                // Perform any cleanup or actions needed before closing the window
                dispose(); // Release system resources associated with the window
            }
        });
    }

    public void showClassDetails(Class<?> myClass) {
        StringBuilder details = new StringBuilder();

        int modifiers = myClass.getModifiers();
        String className = myClass.getName();
        Package pkg = myClass.getPackage();
        String packageName = pkg.getName();
        String modifiersStr = Modifier.toString(modifiers);

        Class<?> superclass = myClass.getSuperclass();
        String superclassStr = (superclass != null) ? superclass.getSimpleName() : "None";

        Class<?>[] interfaces = myClass.getInterfaces();
        StringBuilder interfacesStr = new StringBuilder();
        for (Class<?> intf : interfaces) {
            interfacesStr.append(intf.getSimpleName()).append(", ");
        }
        if (interfacesStr.length() > 0) {
            interfacesStr.setLength(interfacesStr.length() - 2);
        }

        details.append("Package: ").append(packageName).append("\n");
        details.append("Modifiers: ").append(modifiersStr).append("\n");
        details.append("Class Name: ").append(className).append("\n");
        details.append("Superclass: ").append(superclassStr).append("\n");
        details.append("Implemented Interfaces: ").append(interfacesStr).append("\n");

        details.append("*** Constructors ***\n");
        Constructor<?>[] constructors = myClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            details.append(constructor).append("\n");
        }

        details.append("*** Fields ***\n");
        Field[] declaredFields = myClass.getDeclaredFields();
        for (Field field : declaredFields) {
            details.append(field).append("\n");
        }

        details.append("*** Methods ***\n");
        Method[] declaredMethods = myClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            details.append(method).append("\n");
        }

        JTextArea textArea = new JTextArea(details.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set preferred size for the scroll pane

        JOptionPane.showMessageDialog(this, scrollPane, "Class Details", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task1GUI().setVisible(true);
            }
        });
    }
}