import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    private String fio;
    private Date date;
    private int summ;
    private int summOther;

    public Data() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSumm() {
        return "" + summ;
    }

    public void setSumm(int summ) {
        this.summ = summ;
    }

    public int getSummOther() {
        return summOther;
    }

    public void setSummOther(int summOther) {
        this.summOther = summOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (summ != data.summ) return false;
        if (summOther != data.summOther) return false;
        if (fio != null ? !fio.equals(data.fio) : data.fio != null) return false;
        return date != null ? date.equals(data.date) : data.date == null;
    }

    @Override
    public int hashCode() {
        int result = fio != null ? fio.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + summ;
        result = 31 * result + summOther;
        return result;
    }

    @Override
    public String toString() {
        return fio + ", " + new SimpleDateFormat("dd-MM-yyyy").format(date) + ", " + summ + (summOther != 0 ? ", " + summOther : "");
    }
}
