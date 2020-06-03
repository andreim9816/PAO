package Gui;
import Main.Main;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;
import Repository.DBConnectionUtil;
import Thread.ThreadAddHospital;
import Thread.ThreadUpdateHospital;
import Model.tratament.Prescription;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public  class MainFrame extends JFrame
{
    public static MainFrame instance = null;

    private static JScrollPane scrollPaneHospital;
    private static JScrollPane scrollPaneDepartment;
    private static JScrollPane scrollPanePerson;
    private static JScrollPane scrollPanePrescription;

    private static DefaultTableModel tableModelHospital;
    private static DefaultTableModel tableModelDepartment;
    private static DefaultTableModel tableModelPerson;
    private static DefaultTableModel tableModelPrescription;

    final int frameHeight  = 740;
    final int frameWidth = 680;
    final int marginLeftLabel = 20;
    final int marginUp = 10;
    final int height = 30;
    final int spaceBetweenButtons = 40;
    final int marginLeftStartButton = 135;
    final int miniFrameHeight = 165;
    final Font font = new Font("Arial", Font.PLAIN, 14);
    final int widthButton = 75;
    final int heightButton = 20;

    public static MainFrame getInstance(String title) throws SQLException
    {
        if(instance == null)
            instance = new MainFrame(title);
        return instance;
    }

    private MainFrame(String title)
    {
        super(title);

        JLabel hospitalLabel = new JLabel("Hospitals");
        JLabel departmentLabel = new JLabel("Deprtments");
        JLabel personLabel = new JLabel("Persons");
        JLabel prescriptionLabel = new JLabel("Prescriptions");

        hospitalLabel.setFont(font);
        departmentLabel.setFont(font);
        personLabel.setFont(font);
        prescriptionLabel.setFont(font);

        add(hospitalLabel);
        add(departmentLabel);
        add(personLabel);
        add(prescriptionLabel);

        hospitalLabel.setBounds(marginLeftLabel, 5, 100, height);
        departmentLabel.setBounds(marginLeftLabel, marginUp +  miniFrameHeight - 5, 100, height);
        personLabel.setBounds(marginLeftLabel, marginUp + 2 * miniFrameHeight - 5, 100, height);
        prescriptionLabel.setBounds(marginLeftLabel, marginUp + 3 * miniFrameHeight - 5, 100, height);


        ArrayList<JButton> buttons = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
                if(j % 4 == 0)
                {
                    JButton button = new JButton("Add");
                    button.setBounds(marginLeftStartButton + spaceBetweenButtons + j * (spaceBetweenButtons +  widthButton),  marginUp + i * miniFrameHeight, widthButton, heightButton);
                    add(button);
                    buttons.add(button);
                }
                else if(j % 4 == 1)
                {
                    JButton button = new JButton("Update");
                    button.setBounds(marginLeftStartButton + spaceBetweenButtons + j * (spaceBetweenButtons +  widthButton),  marginUp + i * miniFrameHeight, widthButton, heightButton);
                    add(button);
                    buttons.add(button);
                }
                else if (j % 4 == 2)
                {
                    JButton button = new JButton("Get");
                    button.setBounds(marginLeftStartButton + spaceBetweenButtons + j * (spaceBetweenButtons +  widthButton),  marginUp + i * miniFrameHeight, widthButton, heightButton);
                    add(button);
                    buttons.add(button);
                }
                else
                {
                    JButton button = new JButton("Delete");
                    button.setBounds(marginLeftStartButton + spaceBetweenButtons + j * (spaceBetweenButtons +  widthButton),  marginUp + i * miniFrameHeight, widthButton, heightButton);
                    add(button);
                    buttons.add(button);
                }


        String[] hospitalColumns = {"Id", "Name"};
        String[] departmentColumns = {"Id hospital", "Department name", "Beds"};
        String []prescriptionColumns = {"Id", "CNP doctor", "CNP patient", "Recommendation"};
        String []personColumns = {"CNP", "Name", "First name", "Department name", "Id hospital", "Tip", "Info"};

        tableModelHospital = new DefaultTableModel(hospitalColumns, 0);
        tableModelDepartment = new DefaultTableModel(departmentColumns, 0);
        tableModelPerson = new DefaultTableModel(personColumns, 0);
        tableModelPrescription = new DefaultTableModel(prescriptionColumns, 0);

        setScrollHospitals();
        setScrollDepartments();
        setScrollPersons();
        setScrollPrescriptions();

        scrollPaneHospital = new JScrollPane(new JTable(tableModelHospital));
        scrollPaneHospital.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneHospital.setBounds(marginLeftStartButton + spaceBetweenButtons, 50, 420, 100);

        scrollPaneDepartment = new JScrollPane(new JTable(tableModelDepartment));
        scrollPaneDepartment.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneDepartment.setBounds(marginLeftStartButton + spaceBetweenButtons, 50 + miniFrameHeight, 420, 100);

        scrollPanePerson = new JScrollPane(new JTable(tableModelPerson));
        scrollPanePerson.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePerson.setBounds(marginLeftStartButton + spaceBetweenButtons, 50 + 2 * miniFrameHeight , 420, 100);

        scrollPanePrescription = new JScrollPane(new JTable(tableModelPrescription));
        scrollPanePrescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanePrescription.setBounds(marginLeftStartButton + spaceBetweenButtons, 50 + 3 * miniFrameHeight , 420, 100);


        add(scrollPaneHospital);
        add(scrollPaneDepartment);
        add(scrollPanePerson);
        add(scrollPanePrescription);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                DBConnectionUtil.closeDBConnection(Main.connectionUtil);
            }
        });

        buttons.get(0).addActionListener(event -> addHospitalFrame());
        buttons.get(1).addActionListener(event -> updateHospitalFrame());
        buttons.get(2).addActionListener(event -> getHospitalFrame());
        buttons.get(3).addActionListener(event -> removeHospitalFrame());

        buttons.get(4).addActionListener(event -> addDepartmentFrame());
        buttons.get(5).addActionListener(event -> updateDepartmentFrame());
        buttons.get(6).addActionListener(event -> getDepartmentFrame());
        buttons.get(7).addActionListener(event -> removeDepartmentFrame());

        buttons.get(8).addActionListener(event -> addPersonFrame());
        buttons.get(9).addActionListener(event -> updatePersonFrame());
        buttons.get(10).addActionListener(event -> getPersonFrame());
        buttons.get(11).addActionListener(event -> removePersonFrame());

        buttons.get(12).addActionListener(event -> addPrescriptionFrame());
        buttons.get(13).addActionListener(event -> updatePrescriptionFrame());
        buttons.get(14).addActionListener(event -> getPrescriptionFrame());
        buttons.get(15).addActionListener(event -> removePrescriptionFrame());

        setLayout(null);
        setVisible(true);
        setSize(frameWidth, frameHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addHospitalFrame()
    {
        JFrame addHospitalFrame = new JFrame("Add an hospital");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel nameLabel = new JLabel("Hospital name: " );
        JTextField name = new JTextField();
        nameLabel.setBounds(100, 50, 200, 30);
        name.setBounds(40, 80, 200, 30);


        JButton button = new JButton("Add hospital");
        button.setBounds(70, 120, 150, 30);
        button.addActionListener(e -> {
            try {
                String hospitalName = name.getText();
                if (!hospitalName.equals(""))
                {
//                    Main.hospitalService.addHospital(hospitalName);
                    ThreadAddHospital threadAddHospital = new ThreadAddHospital(hospitalName);
                    threadAddHospital.start();
                    try
                    {
                        threadAddHospital.join();
                    }
                    catch (InterruptedException ex)
                    {
                        System.out.println("Eroare fire de executare!");
                        ex.printStackTrace();
                    }

                    ArrayList<Hospital> hospitals = Main.hospitalService.getAllHospital();
                    Hospital lastHospital = hospitals.get(hospitals.size() - 1);
                    JOptionPane.showMessageDialog(addHospitalFrame, "Hospital was succesfully added!");
                    tableModelHospital.addRow(new Object[]{lastHospital.getIdHospital(), lastHospital.getName()});
                    addHospitalFrame.dispose();
                }
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(addHospitalFrame, "Could not add hospital to database!");
            }
            catch(RuntimeException ex)
            {
                JOptionPane.showMessageDialog(addHospitalFrame, "Could not add hospital to database!");
            }
        });

        addHospitalFrame.add(name);
        addHospitalFrame.add(nameLabel);
        addHospitalFrame.add(button);
        addHospitalFrame.setLayout(null);
        addHospitalFrame.setVisible(true);
        addHospitalFrame.setSize(frameWidth, frameHeight);
        addHospitalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void updateHospitalFrame()
    {
        JFrame frame = new JFrame("Update hospital");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel nameLabel = new JLabel("Hospital's id: " );
        nameLabel.setBounds(10, 50, 120, 30);
        JTextField id = new JTextField();
        id.setBounds(90, 50, 150, 30);


        JLabel nameLabel2 = new JLabel("New name: " );
        JTextField name = new JTextField();
        nameLabel2.setBounds(10, 100, 120, 30);
        name.setBounds(90, 100, 150, 30);

        JButton button = new JButton("Update hospital");
        button.setBounds(70, 150, 150, 30);
        button.addActionListener(e -> {
                String hospitalName = name.getText();

            try {
                if(Main.hospitalService.getHospitalById(Integer.parseInt(id.getText())) != null)
                {
                    if (!hospitalName.equals("") && !id.getText().equals(""))
                    {
                        ThreadUpdateHospital thread = new ThreadUpdateHospital(hospitalName, Integer.parseInt(id.getText()));
                        try {
                            thread.start();
                            thread.join();
                            setScrollHospitals();
                            JOptionPane.showMessageDialog(frame, "Hospital updated successfully!!");
                            frame.dispose();
                        } catch (InterruptedException ex) {
                            System.out.println("Eroare fire de executare!");
                            ex.printStackTrace();
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(frame, "Hospital does not exist!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        frame.add(nameLabel);
        frame.add(nameLabel2);
        frame.add(name);
        frame.add(id);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void getHospitalFrame()
    {
        JFrame frame = new JFrame("Get an hospital");
        DefaultTableModel tableModel;

        final int frameWidth = 300;
        final int frameHeight = 400;

        String []columns = {"Id", "Name"};
        tableModel = new DefaultTableModel(columns, 0);
        JScrollPane scrollPane = new JScrollPane(new JTable(tableModel));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(30, 200, 200, 38);

        JLabel nameLabel = new JLabel("Hospital id: " );
        JTextField idField = new JTextField();
        nameLabel.setBounds(100, 50, 200, 30);
        idField.setBounds(40, 80, 200, 30);


        JButton button = new JButton("Get hospital");
        button.setBounds(70, 120, 150, 30);
        button.addActionListener(e -> {
            try {
                if (!idField.getText().equals(""))
                {
                    int id = Integer.parseInt(idField.getText());
                    Hospital h = Main.hospitalService.getHospitalById(id);
                    tableModel.setRowCount(0);
                    System.out.println(h);
                    if(h != null)
                    {
                        Object []data = {h.getIdHospital(), h.getName()};
                        tableModel.addRow(data);
                        tableModel.fireTableDataChanged();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Hospital does not exist!");
                    }
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get hospital from database!");
            }
        });

        frame.add(scrollPane);
        frame.add(idField);
        frame.add(nameLabel);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void removeHospitalFrame()
    {
        JFrame removeHospitalFrame = new JFrame("Remove an hospital");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel nameLabel = new JLabel("Hospital id: " );
        JTextField id = new JTextField();
        nameLabel.setBounds(100, 50, 200, 30);
        id.setBounds(40, 80, 200, 30);


        JButton button = new JButton("Remove hospital");
        button.setBounds(70, 120, 150, 30);
        button.addActionListener(e -> {
            try {

                if (!id.getText().equals(""))
                {
                    if(Main.hospitalService.remove(Integer.parseInt(id.getText())))
                    {
                        JOptionPane.showMessageDialog(removeHospitalFrame, "Hospital was removed!");
                        setScrollHospitals();
                        setScrollPrescriptions();
                        setScrollPersons();
                        setScrollDepartments();
                        removeHospitalFrame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(removeHospitalFrame, "Hospital does not exist!");
                }
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(removeHospitalFrame, "Could not delete hospital from database!");
            }
        });

        removeHospitalFrame.add(id);
        removeHospitalFrame.add(nameLabel);
        removeHospitalFrame.add(button);
        removeHospitalFrame.setLayout(null);
        removeHospitalFrame.setVisible(true);
        removeHospitalFrame.setSize(frameWidth, frameHeight);
        removeHospitalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addDepartmentFrame()
    {
        JFrame frame = new JFrame("Add department");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel nameLabel = new JLabel("Hospital id: " );
        nameLabel.setBounds(10, 50, 120, 30);
        JTextField id = new JTextField();
        id.setBounds(110, 50, 150, 30);


        JLabel nameLabel2 = new JLabel("Depart. name: " );
        nameLabel2.setBounds(10, 100, 120, 30);
        JTextField name = new JTextField();
        name.setBounds(110, 100, 150, 30);

        JLabel nameLabel3 = new JLabel("Number of beds: " );
        nameLabel3.setBounds(10, 150, 120, 30);
        JTextField beds = new JTextField();
        beds.setBounds(110, 150, 150, 30);

        JButton button = new JButton("Add department");
        button.setBounds(110, 200, 150, 30);
        button.addActionListener(e -> {
            try {
                    int hospitalId = Integer.parseInt(id.getText());
                    String departmentName = name.getText();
                    int nrBeds = Integer.parseInt(beds.getText());

                    Department d = new Department(hospitalId, departmentName, nrBeds);
                    Main.departmentService.addDepartment(d);

                    JOptionPane.showMessageDialog(frame, "Department was succesfully added!");
                    tableModelDepartment.addRow(new Object[]{hospitalId, departmentName, nrBeds});
                    frame.dispose();
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not add the department!");
            }
        });

        frame.add(nameLabel);
        frame.add(nameLabel2);
        frame.add(nameLabel3);
        frame.add(name);
        frame.add(id);
        frame.add(beds);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void updateDepartmentFrame()
    {
        JFrame frame = new JFrame("Update department");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel nameLabel = new JLabel("Hospital id: " );
        nameLabel.setBounds(10, 50, 120, 30);
        JTextField id = new JTextField();
        id.setBounds(110, 50, 150, 30);

        JLabel nameLabel2 = new JLabel("Depart. name: " );
        nameLabel2.setBounds(10, 100, 120, 30);
        JTextField name = new JTextField();
        name.setBounds(110, 100, 150, 30);

        JLabel nameLabel3 = new JLabel("Number of beds: " );
        nameLabel3.setBounds(10, 150, 120, 30);
        JTextField beds = new JTextField();
        beds.setBounds(110, 150, 150, 30);

        JButton button = new JButton("Update department");
        button.setBounds(110, 200, 150, 30);
        button.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(id.getText());
                String departmentName = name.getText();
                int nrBeds = Integer.parseInt(beds.getText());

                boolean count = Main.departmentService.changeNumberOfBeds(nrBeds, hospitalId, departmentName);
                if(count)
                {
                    JOptionPane.showMessageDialog(frame, "Department was succesfully updated!");
                    setScrollDepartments();
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(frame, "Department does not exist!");


            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not update the department!");
            }
        });

        frame.add(nameLabel);
        frame.add(nameLabel2);
        frame.add(nameLabel3);
        frame.add(name);
        frame.add(id);
        frame.add(beds);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void getDepartmentFrame()
    {
        JFrame frame = new JFrame("Get a department");
        DefaultTableModel tableModel;

        final int frameWidth = 300;
        final int frameHeight = 400;

        String []columns = {"Id", "Name", "Beds"};
        tableModel = new DefaultTableModel(columns, 0);
        JScrollPane scrollPane = new JScrollPane(new JTable(tableModel));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(30, 200, 200, 38);

        JLabel nameLabel = new JLabel("Hospital id: " );
        nameLabel.setBounds(10, 50, 120, 30);
        JTextField id = new JTextField();
        id.setBounds(110, 50, 150, 30);


        JLabel nameLabel2 = new JLabel("Depart. name: " );
        nameLabel2.setBounds(10, 100, 120, 30);
        JTextField name = new JTextField();
        name.setBounds(110, 100, 150, 30);


        JButton button = new JButton("Get Department");
        button.setBounds(110, 140, 150, 30);

        button.addActionListener(e -> {
            try {
                if (!id.getText().equals("") && !name.getText().equals(""))
                {
                    int idH = Integer.parseInt(id.getText());
                    Department d = Main.departmentService.getDepartment(idH, name.getText());
                    tableModel.setRowCount(0);
                    if(d != null)
                    {
                        Object []data = {d.getIdHospital(), d.getNameDepartment(), d.getNoOfBeds()};
                        tableModel.addRow(data);
                        tableModel.fireTableDataChanged();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Department does not exist!");
                    }
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get hospital from database!");
            }
        });

        frame.add(id);
        frame.add(name);
        frame.add(nameLabel);
        frame.add(nameLabel2);
        frame.add(scrollPane);
        frame.add(nameLabel);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void removeDepartmentFrame()
    {
        JFrame frame = new JFrame("Remove department");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel nameLabel = new JLabel("Hospital id: " );
        nameLabel.setBounds(10, 50, 120, 30);
        JTextField id = new JTextField();
        id.setBounds(110, 50, 150, 30);

        JLabel nameLabel2 = new JLabel("Depart. name: " );
        nameLabel2.setBounds(10, 100, 120, 30);
        JTextField name = new JTextField();
        name.setBounds(110, 100, 150, 30);

        JButton button = new JButton("Delete department");
        button.setBounds(110, 150, 150, 30);
        button.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(id.getText());
                String departmentName = name.getText();

                boolean count = Main.departmentService.remove(hospitalId, departmentName);
                if(count)
                {
                    JOptionPane.showMessageDialog(frame, "Department was succesfully deleted!");
                    setScrollDepartments();
                    setScrollHospitals();
                    setScrollPersons();
                    setScrollPrescriptions();
                    frame.dispose();
                }
                else
                    JOptionPane.showMessageDialog(frame, "Department does not exist!");
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not delete the department!");
            }
        });

        frame.add(nameLabel);
        frame.add(nameLabel2);
        frame.add(name);
        frame.add(id);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addPersonFrame()
    {
        JFrame addPersonFrame = new JFrame("Add a person");
        final int frameWidth = 300;
        final int frameHeight = 400;
        final int fieldWidth = 100;

        JLabel cnpLabel = new JLabel("CNP: " );
        JLabel nameLabel = new JLabel("Last name: ");
        JLabel firstNameLabel = new JLabel("First name: ");
        JLabel idHospitalLabel = new JLabel("Hospital id: ");
        JLabel departmentNameLabel = new JLabel("Depart. name: ");
        JLabel infoLabel = new JLabel("Info: ");

        cnpLabel.setBounds(10, 10, 30, 20);
        nameLabel.setBounds(10, 50, 100, 20);
        firstNameLabel.setBounds(10, 90, 100, 20);
        idHospitalLabel.setBounds(10, 130, 100, 20);
        departmentNameLabel.setBounds(10, 170, 100, 20);
        infoLabel.setBounds(10, 210, 100, 20);

        JTextField cnpField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField idHospitalField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField infoField = new JTextField();

        cnpField.setBounds(120, 10, fieldWidth, 25);
        nameField.setBounds(120, 50, fieldWidth, 25);
        firstNameField.setBounds(120, 90, fieldWidth, 25);
        idHospitalField.setBounds(120, 130, fieldWidth, 25);
        departmentField.setBounds(120, 170, fieldWidth, 25);
        infoField.setBounds(120, 210, fieldWidth, 25);

        JRadioButton doctorButton = new JRadioButton("Doctor");
        JRadioButton nurseButton = new JRadioButton("Asistenta");
        JRadioButton patientButton = new JRadioButton("Patient");

        doctorButton.setBounds(10, 250, 80, 20);
        nurseButton.setBounds(100, 250, 90, 20);
        patientButton.setBounds(190, 250, 100, 20);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(doctorButton);
        buttonGroup.add(nurseButton);
        buttonGroup.add(patientButton);

        JButton button = new JButton("Add Person");
        button.setBounds(60, 280, 150, 30);
        button.addActionListener(e -> {
            try {
                int idHospital = Integer.parseInt(idHospitalField.getText());
                String departmentName = departmentField.getText();
                String name = nameField.getText();
                String firstName = firstNameField.getText();
                String cnp = cnpField.getText();
                String info = infoField.getText();

                if (!departmentName.equals("") && !name.equals("") && !firstName.equals("") && !cnp.equals("") && !idHospitalField.getText().equals(""))
                {
                    Person p;
                    if(doctorButton.isSelected() && !info.equals(""))
                    {
                        p = new Doctor(name, firstName, cnp, info);
                        Main.personService.add(p, idHospital, departmentName);
                        JOptionPane.showMessageDialog(addPersonFrame, "Doctor was succesfully added!");
                        tableModelPerson.addRow(new Object[]{p.getCNP(), p.getLastName(), p.getFirstName(), p.getNameDepartment(), p.getIdHospital(), "doctor", info});
                        addPersonFrame.dispose();
                    }
                    else if(nurseButton.isSelected())
                    {
                        System.out.println("A ajuns aici");
                        p = new Nurse(name, firstName, cnp);
                        Main.personService.add(p, idHospital, departmentName);
                        JOptionPane.showMessageDialog(addPersonFrame, "Nurse was succesfully added!");
                        tableModelPerson.addRow(new Object[]{p.getCNP(), p.getLastName(), p.getFirstName(), p.getNameDepartment(), p.getIdHospital(), "asistenta", info});
                        addPersonFrame.dispose();
                    }
                    else if (!info.equals(""))
                    {
                        p = new Patient(name, firstName, cnp, Boolean.parseBoolean(info));
                        Main.personService.add(p, idHospital, departmentName);
                        JOptionPane.showMessageDialog(addPersonFrame, "Patient was succesfully added!");
                        tableModelPerson.addRow(new Object[]{p.getCNP(), p.getLastName(), p.getFirstName(), p.getNameDepartment(), p.getIdHospital(), "patient", info});
                        addPersonFrame.dispose();
                    }
                }
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(addPersonFrame, "Could not add person to database!");
            }
        });

        addPersonFrame.add(cnpLabel);
        addPersonFrame.add(nameLabel);
        addPersonFrame.add(firstNameLabel);
        addPersonFrame.add(departmentNameLabel);
        addPersonFrame.add(idHospitalLabel);
        addPersonFrame.add(infoLabel);

        addPersonFrame.add(cnpField);
        addPersonFrame.add(nameField);
        addPersonFrame.add(firstNameField);
        addPersonFrame.add(departmentField);
        addPersonFrame.add(idHospitalField);
        addPersonFrame.add(infoField);

        addPersonFrame.add(doctorButton);
        addPersonFrame.add(nurseButton);
        addPersonFrame.add(patientButton);
        addPersonFrame.add(button);

        addPersonFrame.setLayout(null);
        addPersonFrame.setVisible(true);
        addPersonFrame.setSize(frameWidth, frameHeight);
        addPersonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void updatePersonFrame()
    {
        JFrame frame = new JFrame("Update person");
        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel cnpLabel = new JLabel("CNP: ");
        JLabel firstNameLabel = new JLabel("First name: ");
        JLabel lastNameLabel = new JLabel("Last name: ");

        cnpLabel.setBounds(10, 10, 80, 20);
        firstNameLabel.setBounds(10, 50, 80, 20);
        lastNameLabel.setBounds(10, 90, 80, 20);

        JTextField cnpField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();

        cnpField.setBounds(100, 10, 140, 25);
        firstNameField.setBounds(100, 50, 140, 25);
        lastNameField.setBounds(100, 90, 140, 25);

        JButton buttonFirstName = new JButton("Update first name");
        JButton buttonLastName = new JButton("Update last name");

        buttonFirstName.setBounds(100, 130, 140, 30);
        buttonLastName.setBounds(100, 170, 140, 30);

        buttonFirstName.addActionListener(e -> {
            try {
                if (!cnpField.getText().equals("") && !firstNameField.getText().equals(""))
                {
                    if(Main.personService.changeFirstName(cnpField.getText(), firstNameField.getText()))
                    {
                        JOptionPane.showMessageDialog(frame, "Person was succesfully updated!");
                        setScrollPersons();
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Person does not exist!");
                }
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not update the database!");
            }
        });
        buttonLastName.addActionListener(e -> {
            try {
                if (!cnpField.getText().equals("") && !lastNameField.getText().equals(""))
                {
                    if(Main.personService.changeLastName(cnpField.getText(), lastNameField.getText()))
                    {
                        JOptionPane.showMessageDialog(frame, "Person was succesfully updated!");
                        setScrollPersons();
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Person does not exist!");
                }
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not update the database!");
            }
        });
        frame.add(firstNameLabel);
        frame.add(lastNameLabel);
        frame.add(cnpLabel);

        frame.add(cnpField);
        frame.add(firstNameField);
        frame.add(lastNameField);

        frame.add(buttonFirstName);
        frame.add(buttonLastName);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void getPersonFrame()
    {
        JFrame frame = new JFrame("Get a person");
        DefaultTableModel tableModel;

        final int frameWidth = 420;
        final int frameHeight = 400;
        frame.setSize(frameWidth, frameHeight);

        String []columns = {"CNP", "Name", "First name", "Id hospital", "Dept name", "Tip", "Info"};
        tableModel = new DefaultTableModel(columns, 0);
        JScrollPane scrollPane = new JScrollPane(new JTable(tableModel));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 150, 380, 150);

        JLabel cnpLabel = new JLabel("CNP: " );
        JLabel idHospitalLabel = new JLabel("Hospital id: ");
        JLabel departmentNameLabel = new JLabel("Depart name: ");

        JTextField cnpField = new JTextField();
        JTextField idHospitalField = new JTextField();
        JTextField departmentNameField = new JTextField();

        cnpLabel.setBounds(10, 10, 80, 20);
        idHospitalLabel.setBounds(10, 40, 120, 20);
        departmentNameLabel.setBounds(10, 70, 120, 20);

        cnpField.setBounds(90, 10, 160, 25);
        idHospitalField.setBounds(90, 40, 160, 25);
        departmentNameField.setBounds(90, 70, 160, 25);


        JButton buttonGetPerson = new JButton("Get person");
        JButton buttonGetPatients = new JButton("Get patients");
        JButton buttonGetDoctors = new JButton("Get doctors");
        JButton buttonGetNurses = new JButton("Get nurses");

        buttonGetPerson.setBounds(270, 10, 120, 25);
        buttonGetDoctors.setBounds(270, 40, 120, 25);
        buttonGetNurses.setBounds(270, 70, 120, 25);
        buttonGetPatients.setBounds(270, 100, 120, 25);

        buttonGetDoctors.addActionListener(e ->
        {
            try
            {
                if(!idHospitalField.getText().equals("") && !departmentNameField.getText().equals(""))
                {
                    ArrayList<Person> doctors = Main.personService.getAllDoctorsFromDepartment(Integer.parseInt(idHospitalField.getText()), departmentNameField.getText());
                    tableModel.setRowCount(0);
                    for(Person p : doctors)
                    {
                        Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getIdHospital(), p.getNameDepartment(), "doctor", ((Doctor) p).getGrade()};
                        tableModel.addRow(data);
                    }
                    tableModel.fireTableDataChanged();
                }
            }catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get doctors from database!");
            }
        });

        buttonGetNurses.addActionListener(e ->
        {
            try
            {
                if(!idHospitalField.getText().equals("") && !departmentNameField.getText().equals(""))
                {
                    ArrayList<Person> nurses = Main.personService.getAllNursesFromDepartment(Integer.parseInt(idHospitalField.getText()), departmentNameField.getText());
                    tableModel.setRowCount(0);
                    for(Person p : nurses)
                    {
                        System.out.println(p);
                        Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getIdHospital(), p.getNameDepartment(), "asistenta", ""};
                        tableModel.addRow(data);
                    }
                    tableModel.fireTableDataChanged();
                }
            }catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get nurses from database!");
            }
        });

        buttonGetPatients.addActionListener(e ->
        {
            try
            {
                if(!idHospitalField.getText().equals("") && !departmentNameField.getText().equals(""))
                {
                    ArrayList<Person> patients = Main.personService.getAllPatientsFromDepartment(Integer.parseInt(idHospitalField.getText()), departmentNameField.getText());
                    tableModel.setRowCount(0);
                    for(Person p : patients)
                    {
                        Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getIdHospital(), p.getNameDepartment(), "pacient",  ((Patient) p).getInsured()};
                        tableModel.addRow(data);
                    }
                    tableModel.fireTableDataChanged();
                }
            }catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get patients from database!");
            }
        });

        buttonGetPerson.addActionListener(e -> {
            try {
                if (!cnpField.getText().equals(""))
                {
                    Person p = Main.personService.getPersonByCNP(cnpField.getText());
                    tableModel.setRowCount(0);
                    if(p != null)
                    {
                        if(p instanceof Doctor)
                        {
                            Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getIdHospital(), p.getNameDepartment(), "doctor", ((Doctor) p).getGrade()};
                            tableModel.addRow(data);
                        }
                        else if(p instanceof Nurse)
                        {
                            Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getIdHospital(), p.getNameDepartment(), "asistenta", ""};
                            tableModel.addRow(data);
                        }
                        else
                        {
                            Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getIdHospital(), p.getNameDepartment(), "pacient",  ((Patient) p).getInsured()};
                            tableModel.addRow(data);
                        }
                        tableModel.fireTableDataChanged();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Person does not exist!");
                    }
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get person from database!");
            }
        });

        frame.add(departmentNameField);
        frame.add(cnpField);
        frame.add(idHospitalField);

        frame.add(cnpLabel);
        frame.add(departmentNameLabel);
        frame.add(idHospitalLabel);

        frame.add(buttonGetDoctors);
        frame.add(buttonGetNurses);
        frame.add(buttonGetPatients);

        frame.add(scrollPane);
        frame.add(buttonGetPerson);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void removePersonFrame()
    {
        JFrame frame = new JFrame("Remove a person");
        final int frameWidth = 300;
        final int frameHeight = 200;

        JLabel cnpLabel = new JLabel("CNP: ");
        JTextField cnpField = new JTextField();

        cnpLabel.setBounds(10, 10, 80, 20);
        cnpField.setBounds(70, 10, 140, 25);


        JButton button = new JButton("Remove person");
        button.setBounds(70, 50, 140, 30);
        button.addActionListener(e -> {
            try {

                if (!cnpField.getText().equals(""))
                {
                    if(Main.personService.remove(cnpField.getText()))
                    {
                        JOptionPane.showMessageDialog(frame, "Person was removed!");
                        setScrollPersons();
                        setScrollPrescriptions();
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Person does not exist!");
                }
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not delete person from database!");
            }
        });

        frame.add(cnpLabel);
        frame.add(cnpField);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void addPrescriptionFrame()
    {
        JFrame frame = new JFrame("Add a prescription");
        final int frameWidth = 300;
        final int frameHeight = 400;
        final int fieldWidth = 140;

        JLabel cnpDoctorLabel = new JLabel("CNP Doctor: " );
        JLabel cnpPatientLabel = new JLabel("CNP Patient: ");
        JLabel recommendationLabel = new JLabel("Recommendation: ");

        cnpDoctorLabel.setBounds(10, 10, 100, 20);
        cnpPatientLabel.setBounds(10, 50, 100, 20);
        recommendationLabel.setBounds(10, 90, 120, 20);


        JTextField cnpDoctorField = new JTextField();
        JTextField cnpPatientField = new JTextField();
        JTextField recommendationField = new JTextField();

        cnpDoctorField.setBounds(120, 10, fieldWidth, 25);
        cnpPatientField.setBounds(120, 50, fieldWidth, 25);
        recommendationField.setBounds(120, 90, fieldWidth, 25);

        JButton button = new JButton("Add prescription");
        button.setBounds(120, 130, fieldWidth, 25);
        button.addActionListener(e -> {
            try
            {
                if(!cnpDoctorField.getText().equals("") && !cnpPatientField.getText().equals("") && !recommendationField.getText().equals(""))
                {

                    Prescription p = new Prescription(cnpPatientField.getText(), cnpDoctorField.getText(), recommendationField.getText());
                    Person doctor = Main.personService.getPersonByCNP(cnpDoctorField.getText());
                    Person patient = Main.personService.getPersonByCNP((cnpPatientField.getText()));
                    if(doctor instanceof Doctor && patient instanceof Patient)
                    {
                        Main.prescriptionService.add(p);
                        ArrayList<Prescription> prescriptions = Main.prescriptionService.getPrescriptionAll();
                        int id = prescriptions.get(prescriptions.size() - 1).getIdPrescription();
                        JOptionPane.showMessageDialog(frame, "Prescription was succesfully added!");
                        tableModelPrescription.addRow(new Object[]{id, cnpDoctorField.getText(), cnpPatientField.getText(), recommendationField.getText()});
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Prescription was not added. CNPs were not correct!");
                }
            }catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not add prescription to database!");
            }

        });

        frame.add(cnpDoctorLabel);
        frame.add(cnpPatientLabel);
        frame.add(recommendationLabel);

        frame.add(cnpDoctorField);
        frame.add(cnpPatientField);
        frame.add(recommendationField);

        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void updatePrescriptionFrame()
    {
        JFrame frame = new JFrame("Update prescription");
        final int frameWidth = 300;
        final int frameHeight = 400;
        final int fieldWidth = 100;

        JLabel idLabel = new JLabel("Prescription Id: " );
        JLabel recommendationLabel = new JLabel("Recommendation: ");

        idLabel.setBounds(10, 40, 150, 20);
        recommendationLabel.setBounds(10, 70, 140, 20);

        JTextField recommendationField = new JTextField();
        JTextField idField = new JTextField();

        idField.setBounds(120, 40, fieldWidth + 50, 25);
        recommendationField.setBounds(120, 70, fieldWidth + 50, 25);

        JButton button = new JButton("Update prescription");
        button.setBounds(120, 110, fieldWidth + 50, 25);
        button.addActionListener(e -> {
            try
            {
                if(!idField.getText().equals("") && !recommendationField.getText().equals(""))
                {
                    Main.prescriptionService.changeRecommendation(recommendationField.getText(), Integer.parseInt(idField.getText()));
                    JOptionPane.showMessageDialog(frame, "Prescription was succesfully updated!");
                    setScrollPrescriptions();
                    frame.dispose();
                }
            }catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not add prescription to database!");
            }

        });

        frame.add(idLabel);
        frame.add(recommendationLabel);

        frame.add(recommendationField);
        frame.add(idField);

        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void getPrescriptionFrame()
    {
        JFrame frame = new JFrame("Get a prescription");
        DefaultTableModel tableModel;

        final int frameWidth = 440;
        final int frameHeight = 400;

        String []columns = {"Id", "CNP Doctor", "CNP Patient", "Recommendation"};
        tableModel = new DefaultTableModel(columns, 0);
        JScrollPane scrollPane = new JScrollPane(new JTable(tableModel));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 120, 410, 150);

        JLabel idLabel = new JLabel("Prescription id: " );
        JLabel cnpLabel = new JLabel("CNP: ");

        idLabel.setBounds(10, 10, 120, 20);
        cnpLabel.setBounds(10, 40, 120, 20);

        JTextField idField = new JTextField();
        JTextField cnpField = new JTextField();

        idField.setBounds(110, 10, 150, 25);
        cnpField.setBounds(110, 40, 150, 25);

        JButton buttonGetPrescription = new JButton("Get prescription");
        JButton buttonGetPrescriptionBy = new JButton("Get prescrption by");
        JButton buttonGetPrescriptionFor = new JButton("Get prescription for");

        buttonGetPrescription.setBounds(270, 10, 150, 25);
        buttonGetPrescriptionBy.setBounds(270, 40, 150, 25);
        buttonGetPrescriptionFor.setBounds(270, 70, 150, 25);

        buttonGetPrescriptionBy.addActionListener(e ->
        {
            try {
                if (!cnpField.getText().equals(""))
                {
                    ArrayList<Prescription> prescriptions = Main.prescriptionService.getPrescriptionBy(cnpField.getText());
                    tableModel.setRowCount(0);
                    for(Prescription p : prescriptions)
                    {
                        Object []data = {p.getIdPrescription(), p.getCNPDoctor(), p.getCNPPatient(), p.getRecommendation()};
                        tableModel.addRow(data);
                        tableModel.fireTableDataChanged();
                    }
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get prescriptions from database!");
            }
        });

        buttonGetPrescriptionFor.addActionListener(e ->
        {
            try {
                if (!cnpField.getText().equals(""))
                {
                    ArrayList<Prescription> prescriptions = Main.prescriptionService.getPrescriptionFor(cnpField.getText());
                    tableModel.setRowCount(0);
                    for(Prescription p : prescriptions)
                    {
                        Object []data = {p.getIdPrescription(), p.getCNPDoctor(), p.getCNPPatient(), p.getRecommendation()};
                        tableModel.addRow(data);
                        tableModel.fireTableDataChanged();
                    }
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get prescriptions from database!");
            }
        });

        buttonGetPrescription.addActionListener(e -> {
            try {
                if (!idField.getText().equals(""))
                {
                    Prescription p = Main.prescriptionService.getPrescriptionById(Integer.parseInt(idField.getText()));
                    tableModel.setRowCount(0);
                    if(p != null)
                    {
                        Object []data = {p.getIdPrescription(), p.getCNPDoctor(), p.getCNPPatient(), p.getRecommendation()};
                        tableModel.addRow(data);
                        tableModel.fireTableDataChanged();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Prescription does not exist!");
                    }
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get prescription from database!");
            }
        });

        frame.add(idField);
        frame.add(cnpField);

        frame.add(idLabel);
        frame.add(cnpLabel);

        frame.add(scrollPane);

        frame.add(buttonGetPrescription);
        frame.add(buttonGetPrescriptionFor);
        frame.add(buttonGetPrescriptionBy);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void removePrescriptionFrame()
    {
        JFrame frame = new JFrame("Remove prescription");
        DefaultTableModel tableModel;

        final int frameWidth = 300;
        final int frameHeight = 400;

        JLabel idLabel = new JLabel("Prescription id: " );
        idLabel.setBounds(10, 10, 120, 30);

        JTextField idField = new JTextField();
        idField.setBounds(110, 15, 160, 25);

        JButton button = new JButton("Remove prescription");
        button.setBounds(110, 50, 160, 25);

        button.addActionListener(e -> {
            try {
                if (!idField.getText().equals(""))
                {
                    if(Main.prescriptionService.remove(Integer.parseInt(idField.getText())))
                    {
                        JOptionPane.showMessageDialog(frame, "Person was removed!");
                        setScrollPrescriptions();
                        frame.dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(frame, "Prescription does not exist!");
                }
            }
            catch(SQLException exc)
            {
                exc.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Could not get prescription from database!");
            }
        });

        frame.add(idField);
        frame.add(idLabel);
        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setScrollDepartments()
    {
        try {

            ArrayList<Department> departments = Main.departmentService.getAllDepartment();
            tableModelDepartment.setRowCount(0);
            for (Department d : departments) {
                Object[] data = {d.getIdHospital(), d.getNameDepartment(), d.getNoOfBeds()};
                tableModelDepartment.addRow(data);
            }
            tableModelDepartment.fireTableDataChanged();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setScrollHospitals()
    {
        try {

            ArrayList<Hospital> hospitals = Main.hospitalService.getAllHospital();
            tableModelHospital.setRowCount(0);
            for (Hospital hospital : hospitals)
            {
                Object[] data = {hospital.getIdHospital(), hospital.getName()};
                tableModelHospital.addRow(data);
            }
            tableModelHospital.fireTableDataChanged();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setScrollPersons()
    {
        try {

            ArrayList<Person> persons = Main.personService.getAllPerson();
            tableModelPerson.setRowCount(0);
            for(Person p : persons)
            {
                if(p instanceof Doctor)
                {
                    Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getNameDepartment(), p.getIdHospital(), "doctor", ((Doctor) p).getGrade()};
                    tableModelPerson.addRow(data);
                }
                else if(p instanceof Patient)
                {
                    Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getNameDepartment(), p.getIdHospital(), "pacient", ((Patient) p).getInsured()};
                    tableModelPerson.addRow(data);
                }
                else
                {
                    Object []data = {p.getCNP(), p.getLastName(), p.getFirstName(), p.getNameDepartment(), p.getIdHospital(), "asistenta", ""};
                    tableModelPerson.addRow(data);
                }
            }
            tableModelPerson.fireTableDataChanged();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setScrollPrescriptions()
    {
        try {

            ArrayList<Prescription> prescriptions = Main.prescriptionService.getPrescriptionAll();
            tableModelPrescription.setRowCount(0);
            for(Prescription p : prescriptions)
            {
                Object []data = {p.getIdPrescription(), p.getCNPDoctor(), p.getCNPPatient(), p.getRecommendation()};
                tableModelPrescription.addRow(data);
            }
            tableModelPrescription.fireTableDataChanged();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
