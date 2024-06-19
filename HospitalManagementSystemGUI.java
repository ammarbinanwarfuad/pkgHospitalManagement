package pkgHospitalManagement;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HospitalManagementSystemGUI extends JFrame {
    private JLabel titleLabel;
    private JButton addPatientButton, deletePatientButton, showPatientsButton,
            searchByDiseaseButton, addDoctorButton, deleteDoctorButton,
            showDoctorsButton, searchBySpecializationButton, editPatientButton, editDoctorButton;
    private JTextArea outputTextArea;

    private Patient headPatient = null;
    private Doctor headDoctor = null;

    public HospitalManagementSystemGUI() {
        setTitle("Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        titleLabel = new JLabel("Hospital Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 2, 5, 5));

        addPatientButton = new JButton("Add a Patient");
        deletePatientButton = new JButton("Delete Patient");
        showPatientsButton = new JButton("Show all Patients");
        searchByDiseaseButton = new JButton("Search Patients by Disease");
        addDoctorButton = new JButton("Add a Doctor");
        deleteDoctorButton = new JButton("Delete Doctor");
        showDoctorsButton = new JButton("Show all Doctors");
        searchBySpecializationButton = new JButton("Search Doctors by Specialization");
        editPatientButton = new JButton("Edit Patient Info");
        editDoctorButton = new JButton("Edit Doctor Info");

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        buttonPanel.add(addPatientButton);
        buttonPanel.add(deletePatientButton);
        buttonPanel.add(showPatientsButton);
        buttonPanel.add(searchByDiseaseButton);
        buttonPanel.add(addDoctorButton);
        buttonPanel.add(deleteDoctorButton);
        buttonPanel.add(showDoctorsButton);
        buttonPanel.add(searchBySpecializationButton);
        buttonPanel.add(editPatientButton);
        buttonPanel.add(editDoctorButton);

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputTextArea), BorderLayout.SOUTH);

        addPatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addPatient();
                } catch (Exception ex) {
                    outputTextArea.setText("Error adding patient: " + ex.getMessage());
                }
            }
        });

        deletePatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deletePatient();
                } catch (Exception ex) {
                    outputTextArea.setText("Error deleting patient: " + ex.getMessage());
                }
            }
        });

        showPatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showPatients();
                } catch (Exception ex) {
                    outputTextArea.setText("Error showing patients: " + ex.getMessage());
                }
            }
        });

        searchByDiseaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    searchPatientByDisease();
                } catch (Exception ex) {
                    outputTextArea.setText("Error searching patients by disease: " + ex.getMessage());
                }
            }
        });

        addDoctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addDoctor();
                } catch (Exception ex) {
                    outputTextArea.setText("Error adding doctor: " + ex.getMessage());
                }
            }
        });

        deleteDoctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteDoctor();
                } catch (Exception ex) {
                    outputTextArea.setText("Error deleting doctor: " + ex.getMessage());
                }
            }
        });

        showDoctorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showDoctors();
                } catch (Exception ex) {
                    outputTextArea.setText("Error showing doctors: " + ex.getMessage());
                }
            }
        });

        searchBySpecializationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    searchDoctorBySpecialization();
                } catch (Exception ex) {
                    outputTextArea.setText("Error searching doctors by specialization: " + ex.getMessage());
                }
            }
        });

        editPatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    editPatient();
                } catch (Exception ex) {
                    outputTextArea.setText("Error editing patient: " + ex.getMessage());
                }
            }
        });

        editDoctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    editDoctor();
                } catch (Exception ex) {
                    outputTextArea.setText("Error editing doctor: " + ex.getMessage());
                }
            }
        });
    }

    // Add Patient
    private void addPatient() {
        JFrame frame = new JFrame("Add a Patient");
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField diseaseField = new JTextField();
        JTextField mobileField = new JTextField();
        JTextField admissionDateField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Disease:"));
        panel.add(diseaseField);
        panel.add(new JLabel("Mobile:"));
        panel.add(mobileField);
        panel.add(new JLabel("Admission Date (YYYY-MM-DD):"));
        panel.add(admissionDateField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Enter Patient Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String address = addressField.getText();
                String disease = diseaseField.getText();
                String mobile = mobileField.getText();
                String admissionDate = admissionDateField.getText();

                Patient newPatient = new Patient(name, address, disease, mobile, admissionDate);
                newPatient.next = headPatient;
                headPatient = newPatient;
                outputTextArea.setText("Patient added successfully.");
            } catch (Exception e) {
                outputTextArea.setText("Error adding patient: " + e.getMessage());
            }
        }
    }

    // Delete Patient
    private int deletePatient() {
        JFrame frame = new JFrame("Delete a Patient");
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the patient to delete:");

        if (headPatient == null) {
            outputTextArea.setText("No patients available.");
            return 0;
        }

        Patient current = headPatient;
        Patient previous = null;

        while (current != null) {
            if (current.name.equals(name)) {
                if (previous == null)
                    headPatient = current.next;
                else
                    previous.next = current.next;

                outputTextArea.setText("Patient deleted successfully.");
                return 1;
            }

            previous = current;
            current = current.next;
        }

        outputTextArea.setText("Patient not found.");
        return 0;
    }

    // Show Patients
    private void showPatients() {
        if (headPatient == null) {
            outputTextArea.setText("No patients available.");
            return;
        }

        Patient current = headPatient;
        StringBuilder patientsList = new StringBuilder("\nList of patients:\n");

        while (current != null) {
            patientsList.append("Name: ").append(current.name).append("\n");
            patientsList.append("Address: ").append(current.address).append("\n");
            patientsList.append("Disease: ").append(current.disease).append("\n");
            patientsList.append("Mobile: ").append(current.mobile).append("\n");
            patientsList.append("Admission Date: ").append(current.admissionDate).append("\n\n");

            current = current.next;
        }

        outputTextArea.setText(patientsList.toString());
    }

    // Search Patient by Disease
    private void searchPatientByDisease() {
        if (headPatient == null) {
            outputTextArea.setText("No patients available.");
            return;
        }

        JFrame frame = new JFrame("Search Patients by Disease");
        String disease = JOptionPane.showInputDialog(frame, "Enter the disease to search:");

        Patient current = headPatient;
        int count = 0;
        StringBuilder result = new StringBuilder("\nPatients with disease ").append(disease).append(":\n");

        while (current != null) {
            if (current.disease.equals(disease)) {
                result.append("Name: ").append(current.name).append("\n");
                result.append("Address: ").append(current.address).append("\n");
                result.append("Mobile: ").append(current.mobile).append("\n\n");
                count++;
            }

            current = current.next;
        }

        if (count == 0)
            result.append("No patients found with disease ").append(disease).append(".");
        else
            result.append("Total patients with disease ").append(disease).append(": ").append(count);

        outputTextArea.setText(result.toString());
    }

    // Add Doctor
    private void addDoctor() {
        JFrame frame = new JFrame("Add a Doctor");
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        JTextField nameField = new JTextField();
        JTextField specializationField = new JTextField();
        JTextField mobileField = new JTextField();
        JTextField availabilityField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specializationField);
        panel.add(new JLabel("Mobile:"));
        panel.add(mobileField);
        panel.add(new JLabel("Availability:"));
        panel.add(availabilityField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Enter Doctor Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String specialization = specializationField.getText();
                String mobile = mobileField.getText();
                String availability = availabilityField.getText();

                Doctor newDoctor = new Doctor(name, specialization, mobile, availability);
                newDoctor.next = headDoctor;
                headDoctor = newDoctor;
                outputTextArea.setText("Doctor added successfully.");
            } catch (Exception e) {
                outputTextArea.setText("Error adding doctor: " + e.getMessage());
            }
        }
    }

    // Delete Doctor
    private int deleteDoctor() {
        JFrame frame = new JFrame("Delete a Doctor");
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the doctor to delete:");

        if (headDoctor == null) {
            outputTextArea.setText("No doctors available.");
            return 0;
        }

        Doctor current = headDoctor;
        Doctor previous = null;

        while (current != null) {
            if (current.name.equals(name)) {
                if (previous == null)
                    headDoctor = current.next;
                else
                    previous.next = current.next;

                outputTextArea.setText("Doctor deleted successfully.");
                return 1;
            }

            previous = current;
            current = current.next;
        }

        outputTextArea.setText("Doctor not found.");
        return 0;
    }

    // Show Doctors
    private void showDoctors() {
        if (headDoctor == null) {
            outputTextArea.setText("No doctors available.");
            return;
        }

        Doctor current = headDoctor;
        StringBuilder doctorsList = new StringBuilder("\nList of doctors:\n");

        while (current != null) {
            doctorsList.append("Name: ").append(current.name).append("\n");
            doctorsList.append("Specialization: ").append(current.specialization).append("\n");
            doctorsList.append("Mobile: ").append(current.mobile).append("\n");
            doctorsList.append("Availability: ").append(current.availability).append("\n\n");

            current = current.next;
        }

        outputTextArea.setText(doctorsList.toString());
    }

    // Search Doctor by Specialization
    private void searchDoctorBySpecialization() {
        if (headDoctor == null) {
            outputTextArea.setText("No doctors available.");
            return;
        }

        JFrame frame = new JFrame("Search Doctors by Specialization");
        String specialization = JOptionPane.showInputDialog(frame, "Enter the specialization to search:");

        Doctor current = headDoctor;
        int count = 0;
        StringBuilder result = new StringBuilder("\nDoctors with specialization ").append(specialization).append(":\n");

        while (current != null) {
            if (current.specialization.equals(specialization)) {
                result.append("Name: ").append(current.name).append("\n");
                result.append("Mobile: ").append(current.mobile).append("\n");
                result.append("Availability: ").append(current.availability).append("\n\n");
                count++;
            }

            current = current.next;
        }

        if (count == 0)
            result.append("No doctors found with specialization ").append(specialization).append(".");
        else
            result.append("Total doctors with specialization ").append(specialization).append(": ").append(count);

        outputTextArea.setText(result.toString());
    }

    // Edit Patient Info
    private void editPatient() {
        if (headPatient == null) {
            outputTextArea.setText("No patients available.");
            return;
        }

        JFrame frame = new JFrame("Edit Patient Info");
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the patient to edit:");

        Patient current = headPatient;
        boolean found = false;

        while (current != null) {
            if (current.name.equals(name)) {
                found = true;

                JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

                JTextField nameField = new JTextField(current.name);
                JTextField addressField = new JTextField(current.address);
                JTextField diseaseField = new JTextField(current.disease);
                JTextField mobileField = new JTextField(current.mobile);
                JTextField admissionDateField = new JTextField(current.admissionDate);

                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Address:"));
                panel.add(addressField);
                panel.add(new JLabel("Disease:"));
                panel.add(diseaseField);
                panel.add(new JLabel("Mobile:"));
                panel.add(mobileField);
                panel.add(new JLabel("Admission Date (YYYY-MM-DD):"));
                panel.add(admissionDateField);

                int result = JOptionPane.showConfirmDialog(frame, panel, "Edit Patient Details", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        current.name = nameField.getText();
                        current.address = addressField.getText();
                        current.disease = diseaseField.getText();
                        current.mobile = mobileField.getText();
                        current.admissionDate = admissionDateField.getText();

                        outputTextArea.setText("Patient details updated successfully.");
                    } catch (Exception e) {
                        outputTextArea.setText("Error updating patient details: " + e.getMessage());
                    }
                }
                return;
            }

            current = current.next;
        }

        if (!found)
            outputTextArea.setText("Patient not found.");
    }

    // Edit Doctor Info
    private void editDoctor() {
        if (headDoctor == null) {
            outputTextArea.setText("No doctors available.");
            return;
        }

        JFrame frame = new JFrame("Edit Doctor Info");
        String name = JOptionPane.showInputDialog(frame, "Enter the name of the doctor to edit:");

        Doctor current = headDoctor;
        boolean found = false;

        while (current != null) {
            if (current.name.equals(name)) {
                found = true;

                JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

                JTextField nameField = new JTextField(current.name);
                JTextField specializationField = new JTextField(current.specialization);
                JTextField mobileField = new JTextField(current.mobile);
                JTextField availabilityField = new JTextField(current.availability);

                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Specialization:"));
                panel.add(specializationField);
                panel.add(new JLabel("Mobile:"));
                panel.add(mobileField);
                panel.add(new JLabel("Availability:"));
                panel.add(availabilityField);

                int result = JOptionPane.showConfirmDialog(frame, panel, "Edit Doctor Details", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        current.name = nameField.getText();
                        current.specialization = specializationField.getText();
                        current.mobile = mobileField.getText();
                        current.availability = availabilityField.getText();

                        outputTextArea.setText("Doctor details updated successfully.");
                    } catch (Exception e) {
                        outputTextArea.setText("Error updating doctor details: " + e.getMessage());
                    }
                }
                return;
            }

            current = current.next;
        }

        if (!found)
            outputTextArea.setText("Doctor not found.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HospitalManagementSystemGUI gui = new HospitalManagementSystemGUI();
                gui.setVisible(true);
            }
        });
    }
}

