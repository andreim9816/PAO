package Model.tratament;

import java.util.Objects;

public class Medication
{
    private String nameMedication;
    private int idPrescription;
    private int quantity;

    public Medication()
    {
        idPrescription = 0;
        quantity = 0;
    }

    public Medication(String nameMedication, int quantity)
    {
        this.nameMedication = new String(nameMedication);
        this.quantity = quantity;
    }

    public int getIdPrescription()
    {
        return idPrescription;
    }

    public void setIdPrescription(int idPrescription)
    {
        this.idPrescription = idPrescription;
    }

    public String getNameMedication()
    {
        return nameMedication;
    }

    public void setNameMedication(String nameMedication)
    {
        this.nameMedication = nameMedication;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return idPrescription == that.idPrescription &&
                getQuantity() == that.getQuantity() &&
                Objects.equals(getNameMedication(), that.getNameMedication());
    }

    @Override
    public String toString() {
        return "Medication{" +
                "idPrescription=" + idPrescription +
                ", nameMedication='" + nameMedication + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
