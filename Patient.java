package pkgHospitalManagement;

class Patient {
    String name;
    String address;
    String disease;
    String mobile;
    String admissionDate;
    Patient next;

    Patient(String name, String address, String disease, String mobile, String admissionDate) {
        this.name = name;
        this.address = address;
        this.disease = disease;
        this.mobile = mobile;
        this.admissionDate = admissionDate;
        this.next = null;
    }
}
