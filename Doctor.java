package pkgHospitalManagement;

class Doctor {
    String name;
    String specialization;
    String mobile;
    String availability;
    Doctor next;

    Doctor(String name, String specialization, String mobile, String availability) {
        this.name = name;
        this.specialization = specialization;
        this.mobile = mobile;
        this.availability = availability;
        this.next = null;
    }
}